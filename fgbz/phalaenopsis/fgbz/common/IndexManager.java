package phalaenopsis.fgbz.common;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import phalaenopsis.common.method.Basis;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.mongodb.util.MyAsserts.assertEquals;

/**
 * Created by 13260 on 2018/1/24.
 */
public class IndexManager {

    private static IndexManager indexManager;
    private static String content="";

    private static String INDEX_DIR = "\\luceneIndex";
    private static Analyzer analyzer = null;
    private static Directory directory = null;
    private static IndexWriter indexWriter = null;
    private static File file=null;

    /**
     * 创建索引管理器
     * @return 返回索引管理器对象
     */
    public IndexManager(){
        if(indexManager == null){
            this.indexManager = new IndexManager();
        }

    }
    /**
     * 创建当前文件目录的索引
     * @return 是否成功
     */
    public static boolean createIndex(String id,String solrText) throws IOException {
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
        Basis basis = new Basis();
        file = new File( basis.getServerPath()+INDEX_DIR);
        if (!file.exists())
            file.mkdir();
        Directory directory = FSDirectory.open(file);
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_CURRENT, analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);

        Document doc = new Document();
        doc.add(new Field("id", id, TextField.TYPE_STORED));
        doc.add(new Field("solrText", solrText, TextField.TYPE_STORED));
        iwriter.addDocument(doc);
        iwriter.close();

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


        //设置分词方式
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);

        //设置查询域
        QueryParser parser = new QueryParser(Version.LUCENE_CURRENT, "solrText", analyzer);
        // 查询字符串
        Query query = parser.parse(text);

        ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
//        assertEquals(1, hits.length);
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            System.out.println("id= " + hitDoc.get("id"));
            System.out.println("solrText= " + hitDoc.get("solrText"));
        }
        ireader.close();
        directory.close();

    }





}
