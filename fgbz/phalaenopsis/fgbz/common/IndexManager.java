package phalaenopsis.fgbz.common;

import com.sun.star.util.SortFieldType;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.fgbz.entity.LawstandardType;
import phalaenopsis.fgbz.entity.Slor;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by 13260 on 2018/1/24.
 */
@Service("indexManager")
public class IndexManager {

    private static String INDEX_DIR =new AppSettings().getSolrpath();
    private static File file=null;

    //------lock 1
    private static Object lock_wd=new Object();

    /**
     * 创建当前文件目录的索引
     * @return 是否成功
     */
    public  boolean createIndex(Slor slor) throws IOException {

        synchronized(lock_wd){
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
            file = new File( INDEX_DIR);
            if (!file.exists())
                file.mkdir();
            Directory directory = FSDirectory.open(file);
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            IndexWriter iwriter = new IndexWriter(directory, config);

            try {
                Document doc = new Document();
                doc.add(new StringField("id", slor.getId(),Field.Store.YES));
                doc.add(new Field("solrText",slor.getSolrtext(),Field.Store.NO, Field.Index.NOT_ANALYZED));
                if(slor.getReleasedate()==null){
                    doc.add(new Field("recordtime",
                            DateTools.dateToString(new Date(),  DateTools.Resolution.SECOND),
                            Field.Store.YES,
                            Field.Index.NOT_ANALYZED));
                }else{
                    doc.add(new Field("recordtime",
                            DateTools.dateToString(slor.getReleasedate(),  DateTools.Resolution.SECOND),
                            Field.Store.YES,
                            Field.Index.NOT_ANALYZED));
                }

                doc.add(new Field("modifydate",
                        DateTools.dateToString(slor.getModifydate(),  DateTools.Resolution.SECOND),
                        Field.Store.YES, Field.Index.NOT_ANALYZED
                ));
                doc.add(new StringField("chinesename", slor.getChinesename(), Field.Store.YES));
                doc.add(new StringField("keywords", slor.getKeywords(), Field.Store.YES));
                doc.add(new StringField("summaryinfo", slor.getSummaryinfo(), Field.Store.YES));
                if(StrUtil.isNullOrEmpty(slor.getClickcount())){
                    slor.setClickcount("0");
                }
                doc.add(new StringField("clickcount", slor.getClickcount(),Field.Store.YES));
                doc.add(new StringField("lawtype", slor.getLawtype(), Field.Store.YES));

                iwriter.addDocument(doc);
                iwriter.commit();
                iwriter.close();
            }catch (Exception e){
                iwriter.close();
            }
        }



        return true;
    }

    public  boolean deleteIndex(Slor slor) throws IOException{

        synchronized(lock_wd){
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
            file = new File( INDEX_DIR);
            if (!file.exists())
                return true;
            Directory directory = FSDirectory.open(file);

            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);

            IndexWriter iwriter = new IndexWriter(directory, config);

            try {
                iwriter.deleteDocuments(new Term("id", slor.getId()));
                //索引删除后无法恢复
                iwriter.commit();
                iwriter.close();
            }catch (Exception e){
                iwriter.close();
            }
        }
        return true;
    }

    /**
     * 查找索引，返回符合条件的文件
     * @param
     * @return 符合条件的文件List
     */
    public  Map<String,Object> searchIndex(Page page) throws IOException, ParseException {

        Map<String ,Object> map =new HashMap<>();

        file = new File( INDEX_DIR);
        if (!file.exists())
            file.mkdir();
        Directory directory = FSDirectory.open(file);

        DirectoryReader ireader = DirectoryReader.open(directory);

        try {
            IndexSearcher isearcher = new IndexSearcher(ireader);

            List<Slor> list = new ArrayList<Slor>();

            BooleanQuery query = new BooleanQuery();

            if(page.getConditions()!=null) {
                //查询条件
                for (Condition condition : page.getConditions()) {
                    if (condition.getKey().equals("Solr")) {
                        String[] solrList = condition.getValue().split(" ");
                        for (String str : solrList
                                ) {
                            WildcardQuery termQuery = new WildcardQuery(new Term("solrText", "*" + str + "*"));
                        /*
                         * BooleanQuery连接多值查询
                         * Occur.MUST表示必须出现
                         * Occur.SHOULD表示可以出现
                         * Occur.MUSE_NOT表示必须不能出现
                         */
                            query.add(termQuery, BooleanClause.Occur.SHOULD);
                        }
                    }else if(condition.getKey().equals("recordtime")){
                        TermRangeQuery termRangeQuery = new TermRangeQuery("recordtime", new BytesRef(0), new BytesRef(condition.getKey()), true, true);
                        TermRangeQuery termRangeQuery1 = new TermRangeQuery("recordtime", new BytesRef(condition.getKey()), new BytesRef("9999-12-31"), true, true);
                        NumericRangeQuery.newLongRange("recordtime",
                                null,
                                new Date(condition.getKey()).getTime(),
                                true,
                                true);
                    }

                }
            }
            TopDocs tds = null;
            Sort sort = new Sort(new SortField[]{new SortField("modifydate", SortField.Type.LONG, true)});
            //获取上一页的最后一个元素
            ScoreDoc lastSd = getLastScoreDoc(page.getPageNo(), page.getPageSize(), query, isearcher);
            //通过最后一个元素去搜索下一页的元素
            tds = isearcher.searchAfter(lastSd, query,  page.getPageSize());

            int TotalCount = tds.totalHits;
            map.put("Total",TotalCount);

            for (ScoreDoc sd : tds.scoreDocs)
            {
                Slor slor = new Slor();
                Document doc = isearcher.doc(sd.doc);
                slor.setId(doc.get("id"));
                slor.setChinesename(doc.get("chinesename"));
                slor.setClickcount(doc.get("clickcount"));
                if(!StrUtil.isNullOrEmpty(doc.get("recordtime"))){
                    try {
                        slor.setReleasedate(DateTools.stringToDate(doc.get("recordtime")));
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                }
                slor.setKeywords(doc.get("keywords"));
                slor.setSummaryinfo(doc.get("summaryinfo"));
                list.add(slor);

            }
            map.put("data",list);
            ireader.close();
            directory.close();
        }catch (Exception e){
            ireader.close();
            directory.close();
        }





        return map;
    }
    private  ScoreDoc getLastScoreDoc(int pageIndex, int pageSize, Query query, IndexSearcher searcher) throws IOException {
        if (pageIndex <= 0) return null;//如果是第一页就返回空
        int num = pageSize * pageIndex;//获取上一页的最后数量
        if (num > 0)
        {
            TopDocs tds = searcher.search(query, num);
            if (tds.scoreDocs.length >= num)
            {
                return tds.scoreDocs[num-1];
            }
        }
        return null;
    }





}
