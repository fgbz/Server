package phalaenopsis.fgbz.common;

import com.sun.star.util.SortFieldType;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.fgbz.entity.LawstandardType;
import phalaenopsis.fgbz.entity.Slor;
import phalaenopsis.fgbz.service.LawstandardService;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
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

    public List<LawstandardType> ids = new ArrayList<>();

    private DateFormat format = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private LawstandardService lawstandardService;

    public  List<LawstandardType> getLawsTree(String id){

        List<LawstandardType> list = lawstandardService.getChildNode(id);

        while (list!=null&&list.size()>0){
            ids.addAll(list);
            for(LawstandardType lawstandardType:list){
                list=getLawsTree(lawstandardType.getId());
            }
        }
        return list;
    }

    /**
     * 创建当前文件目录的索引
     * @return 是否成功
     */
    public  boolean createIndex(Slor slor) throws IOException {

        synchronized(lock_wd){
//            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
            Analyzer  analyzer= new WhitespaceAnalyzer(Version.LUCENE_40);
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
                doc.add(new Field("solrText",slor.getSolrtext(),Field.Store.NO, Field.Index.ANALYZED));
                if(slor.getReleasedate()==null){

                    doc.add(new IntField("releasedate", Integer.parseInt("10000101"),Field.Store.YES));
                }else{

                    doc.add(new IntField("releasedate", Integer.parseInt(DateTools.dateToString(slor.getReleasedate(),  DateTools.Resolution.DAY)),Field.Store.YES));
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

                doc.add(new StringField("code", slor.getCode(), Field.Store.YES));
                doc.add(new StringField("englishname", slor.getEnglishname(), Field.Store.YES));
                doc.add(new StringField("status", slor.getLawstatus(), Field.Store.YES));


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
            Analyzer  analyzer= new WhitespaceAnalyzer(Version.LUCENE_40);
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
                        BooleanQuery querySolr = new BooleanQuery();
                        String[] solrList = condition.getValue().split(";|；");
                        //分号代表多个条件
                        for (String str : solrList
                                ) {

                            if(StrUtil.isNullOrEmpty(str)){
                                continue;
                            }

                            BooleanQuery queryWhitePlace = new BooleanQuery();
                            String[] solrWhitePlaceList = str.split(" ");

                            for(String strWhitePlace :solrWhitePlaceList){

                                if(StrUtil.isNullOrEmpty(strWhitePlace)){
                                    continue;
                                }

                                WildcardQuery termQuery = new WildcardQuery(new Term("solrText", "*" + strWhitePlace + "*"));
                                queryWhitePlace.add(termQuery, BooleanClause.Occur.MUST);
                            }

                            querySolr.add(queryWhitePlace, BooleanClause.Occur.SHOULD);
                        /*
                         * BooleanQuery连接多值查询s
                         * Occur.MUST表示必须出现
                         * Occur.SHOULD表示可以出现
                         * Occur.MUSE_NOT表示必须不能出现
                         */
                        }
                        query.add(querySolr, BooleanClause.Occur.MUST);
                    }else if(condition.getKey().equals("Number")){
                        WildcardQuery termQuery = new WildcardQuery(new Term("code", "*" + condition.getValue() + "*"));
                        query.add(termQuery, BooleanClause.Occur.MUST);
                    }else if(condition.getKey().equals("Title")){
                        WildcardQuery termQuery = new WildcardQuery(new Term("chinesename", "*" + condition.getValue() + "*"));
                        query.add(termQuery, BooleanClause.Occur.MUST);
                    }else if(condition.getKey().equals("EnglishTitle")){
                        WildcardQuery termQuery = new WildcardQuery(new Term("englishname", "*" + condition.getValue() + "*"));
                        query.add(termQuery, BooleanClause.Occur.MUST);
                    }else if(condition.getKey().equals("Summaryinfo")){
                        WildcardQuery termQuery = new WildcardQuery(new Term("summaryinfo", "*" + condition.getValue() + "*"));
                        query.add(termQuery, BooleanClause.Occur.MUST);
                    }else if(condition.getKey().equals("KeyWordsSingle")){
                        WildcardQuery termQuery = new WildcardQuery(new Term("keywords", "*" + condition.getValue() + "*"));
                        query.add(termQuery, BooleanClause.Occur.MUST);
                    }else if(condition.getKey().equals("State")){
                        TermQuery termQuery = new TermQuery(new Term("status", condition.getValue()));
                        query.add(termQuery, BooleanClause.Occur.MUST);
                    }else if(condition.getKey().equals("FiledTimeStart")){

                        Query termRangeQuery=NumericRangeQuery.newIntRange("releasedate", Integer.parseInt(DateTools.dateToString(strToDate(condition.getValue()),DateTools.Resolution.DAY)), Integer.parseInt("99991231"), true, true);
                        query.add(termRangeQuery, BooleanClause.Occur.MUST);
                    }else if(condition.getKey().equals("FiledTimeEnd")){

                        Query termRangeQuery=NumericRangeQuery.newIntRange("releasedate",Integer.parseInt("10000101"), Integer.parseInt(DateTools.dateToString(strToDate(condition.getValue()),DateTools.Resolution.DAY)),true, true);
                        query.add(termRangeQuery, BooleanClause.Occur.MUST);
                    }else if(condition.getKey().equals("TreeValue")){

                        BooleanQuery queryLawType = new BooleanQuery();
                        ids =new ArrayList<>();
                        LawstandardType lawSelf = new LawstandardType();
                        lawSelf.setId(condition.getValue());
                        ids.add(lawSelf);
                        getLawsTree(condition.getValue());
                        for (LawstandardType lawstandardType:ids
                             ) {
                            TermQuery termQuery = new TermQuery(new Term("lawtype", lawstandardType.getId()));
                            queryLawType.add(termQuery, BooleanClause.Occur.SHOULD);

                        }
                        query.add(queryLawType, BooleanClause.Occur.MUST);
                    }

                }
            }
            TopDocs tds = null;
            Sort sort = new Sort(new SortField[]{new SortField("releasedate", SortField.Type.INT, true)});

            //获取上一页的最后一个元素
            ScoreDoc lastSd = getLastScoreDoc(page.getPageNo(), page.getPageSize(), query, isearcher,sort);
            //通过最后一个元素去搜索下一页的元素
            tds = isearcher.searchAfter(lastSd, query,  page.getPageSize(),sort);

            int TotalCount = tds.totalHits;
            map.put("Total",TotalCount);

            for (ScoreDoc sd : tds.scoreDocs)
            {
                Slor slor = new Slor();
                Document doc = isearcher.doc(sd.doc);
                slor.setId(doc.get("id"));
                slor.setChinesename(doc.get("chinesename"));
                slor.setClickcount(doc.get("clickcount"));
//                if(!StrUtil.isNullOrEmpty(doc.get("releasedate"))){
//                    try {
//                        slor.setReleasedate(DateTools.stringToDate(doc.get("releasedate")));
//                    } catch (java.text.ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String time = lawstandardService.getReleasedateByLawId(slor.getId());
                if(!StrUtil.isNullOrEmpty(time)){
                    slor.setReleasedate( formatter.parse(time));
                }else{
                    slor.setReleasedate(DateTools.stringToDate("10000101"));
                }


                slor.setModifydate(DateTools.stringToDate(doc.get("modifydate")));
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
    private  ScoreDoc getLastScoreDoc(int pageIndex, int pageSize, Query query, IndexSearcher searcher, Sort sort) throws IOException {
        if (pageIndex <= 1) return null;//如果是第一页就返回空
        int num = pageSize * (pageIndex-1);//获取上一页的最后数量
        if (num > 0)
        {
            TopDocs tds = searcher.search(query, num,sort);
            if (tds.scoreDocs.length >= num)
            {
                return tds.scoreDocs[num-1];
            }
        }
        return null;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);

        Calendar c = Calendar.getInstance();
        c.setTime(strtodate);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        Date tomorrow = c.getTime();


        return tomorrow;
    }





}
