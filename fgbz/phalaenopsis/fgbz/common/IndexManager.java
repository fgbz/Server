package phalaenopsis.fgbz.common;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import phalaenopsis.common.method.Basis;
import phalaenopsis.fgbz.entity.Slor;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.mongodb.util.MyAsserts.assertEquals;

/**
 * Created by 13260 on 2018/1/24.
 */
public class IndexManager {

    private static IndexManager indexManager;

    private static String INDEX_DIR = "\\luceneIndex";
    private static File file=null;

    /**
     * 创建索引管理器
     * @return 返回索引管理器对象
     */
//    public IndexManager(){
//        if(indexManager == null){
//            this.indexManager = new IndexManager();
//        }
//
//    }
    /**
     * 创建当前文件目录的索引
     * @return 是否成功
     */
    public static boolean createIndex(Slor slor) throws IOException {
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
        Basis basis = new Basis();
        file = new File( basis.getServerPath()+INDEX_DIR);
        if (!file.exists())
            file.mkdir();
        Directory directory = FSDirectory.open(file);
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        config.setRAMBufferSizeMB(256.0);
        IndexWriter iwriter = new IndexWriter(directory, config);

        IndexReader indexReader = IndexReader.open(directory);
        System.out.println("总共的索引文档:" + indexReader.maxDoc());

        Document doc = new Document();
        doc.add(new StringField("id", slor.getId(),Field.Store.YES));
        doc.add(new StringField("solrText", slor.getSolrtext(),Field.Store.YES));
//        doc.add(new Field("recordtime",
//                DateTools.dateToString(slor.getModifydate(),  DateTools.Resolution.SECOND),
//                Field.Store.YES,
//                Field.Index.NOT_ANALYZED));
//        doc.add(new Field("modifydate",
//                DateTools.dateToString(slor.getModifydate(),  DateTools.Resolution.SECOND),
//                Field.Store.YES,
//                Field.Index.NOT_ANALYZED));
        doc.add(new Field("chinesename", slor.getChinesename(), TextField.TYPE_STORED));
        doc.add(new Field("keywords", slor.getKeywords(), TextField.TYPE_STORED));
        doc.add(new Field("summaryinfo", slor.getSummaryinfo(), TextField.TYPE_STORED));
        doc.add(new Field("clickcount", slor.getClickcount(), TextField.TYPE_STORED));
        doc.add(new Field("lawtype", slor.getLawtype(), TextField.TYPE_STORED));

        iwriter.addDocument(doc);
        iwriter.commit();
        iwriter.close();
        System.out.println("总共的索引文档:" + indexReader.maxDoc());
        return true;
    }

    public static boolean deleteIndex(Slor slor) throws IOException{
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
        Basis basis = new Basis();
        file = new File( basis.getServerPath()+INDEX_DIR);
        if (!file.exists())
            return true;
        Directory directory = FSDirectory.open(file);
        IndexReader indexReader = IndexReader.open(directory);

        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);

        iwriter.deleteDocuments(new Term("id", slor.getId()));
        //索引删除后无法恢复
        iwriter.commit();
        iwriter.close();
        System.out.println("删掉的索引文档:" + indexReader.numDeletedDocs());
        return true;
    }

    /**
     * 查找索引，返回符合条件的文件
     * @param text 查找的字符串
     * @return 符合条件的文件List
     */
    public static void searchIndex(String text) throws IOException, ParseException {
        Basis basis = new Basis();
        file = new File( basis.getServerPath()+INDEX_DIR);
        if (!file.exists())
            file.mkdir();
        Directory directory = FSDirectory.open(file);
        DirectoryReader ireader = DirectoryReader.open(directory);

        IndexSearcher isearcher = new IndexSearcher(ireader);

        Slor slor = new Slor();

//        BooleanQuery query = new BooleanQuery();
//          Query query = new WildcardQuery(new Term("solrText","*"+text+"*"));
        /*
         * BooleanQuery连接多值查询
         * Occur.MUST表示必须出现
         * Occur.SHOULD表示可以出现
         * Occur.MUSE_NOT表示必须不能出现
         */
//        query.add(new TermQuery(new Term("solrText","*"+text+"*")), BooleanClause.Occur.MUST);
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
        QueryParser parser = new QueryParser(Version.LUCENE_40, "solrText", analyzer);
        Query query = parser.parse(text);
        ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;

        int pageSize=20;//每页显示记录数
        int curPage=1;//当前页
        //查询起始记录位置
        int begin = pageSize * (curPage - 1);
        //查询终止记录位置
        int end = Math.min(begin + pageSize, hits.length);
        //遍历结果集
        for(int i=begin;i<end;i++) {
            int docID = hits[i].doc;
            Document document = isearcher.doc(docID);
            slor.setId(document.get("id"));
            slor.setSolrtext(document.get("solrText"));
            slor.setChinesename(document.get("chinesename"));
        }

        ireader.close();
        directory.close();

    }





}
