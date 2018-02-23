package com.chenshun.lucene.first;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * User: chenshun131 <p />
 * Time: 18/2/22 23:08  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class IndexSearch {

    private static final String FILE_PATH_TEST = "/Users/chenshun131/Desktop/AllMyFile/Datas/Lucene";

    private void doSearch(Query query) {
        // 创建IndexSearcher
        // 指定索引库的地址
        try {
            Directory directory = FSDirectory.open(new File(FILE_PATH_TEST));
            IndexReader reader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(reader);
            // 通过searcher来搜索索引库
            // 第二个参数 : 指定需要显示的顶部记录的N条
            TopDocs topDocs = searcher.search(query, 10);

            // 根据查询条件匹配出的记录总数
            long count = topDocs.totalHits;
            System.out.println("匹配出的记录总数:" + count);
            // 根据查询条件匹配出的记录
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            for (ScoreDoc scoreDoc : scoreDocs) {
                // 获取文档的ID
                int docId = scoreDoc.doc;

                // 通过ID获取文档
                Document doc = searcher.doc(docId);
                System.out.println("商品ID：" + doc.get("id"));
                System.out.println("商品名称：" + doc.get("name"));
                System.out.println("商品价格：" + doc.get("price"));
                System.out.println("商品图片地址：" + doc.get("pic"));
                System.out.println("==========================");
                System.out.println("商品描述：" + doc.get("description"));
            }
            // 关闭资源
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void indexSearch() throws Exception {
        // 创建query对象
        // 使用QueryParser搜索时，需要指定分词器，搜索时的分词器要和索引时的分词器一致
        // 第一个参数：默认搜索的域的名称
        QueryParser parser = new QueryParser("description", new StandardAnalyzer());

        // 通过queryparser来创建query对象
        // 参数：输入的lucene的查询语句(关键字一定要大写)
        Query query = parser.parse("description:java AND lucene");

        doSearch(query);

    }

    @Test
    public void termQuery() {
        // 创建TermQuery对象
        Query query = new TermQuery(new Term("description", "java"));

        doSearch(query);
    }

    @Test
    public void numericRangeQuery() {

        // 创建NumericRangeQuery对象
        // 参数：域的名称、最小值、最大值、是否包含最小值、是否包含最大值
        Query query = NumericRangeQuery.newFloatRange("price", 55f, 60f, true,
                false);

        doSearch(query);
    }

    @Test
    public void booleanQuery() {
        // 创建BooleanQuery
        BooleanQuery query = new BooleanQuery();
        // 创建TermQuery对象
        Query q1 = new TermQuery(new Term("description", "lucene"));
        // 创建NumericRangeQuery对象
        // 参数：域的名称、最小值、最大值、是否包含最小值、是否包含最大值
        Query q2 = NumericRangeQuery.newFloatRange("price", 55f, 60f, true,
                false);

        // 组合关系代表的意思如下:
        // 1、MUST和MUST表示“与”的关系，即“交集”。
        // 2、MUST和MUST_NOT前者包含后者不包含。
        // 3、MUST_NOT和MUST_NOT没意义
        // 4、SHOULD与MUST表示MUST，SHOULD失去意义；
        // 5、SHOUlD与MUST_NOT相当于MUST与MUST_NOT。
        // 6、SHOULD与SHOULD表示“或”的概念。

        query.add(q1, Occur.MUST_NOT);
        query.add(q2, Occur.MUST_NOT);

        doSearch(query);
    }

    @Test
    public void multiFieldQueryParser() throws Exception {
        // 创建7.3.2 MultiFieldQueryParser
        // 默认搜索的多个域的域名
        String[] fields = {"name", "description"};
        Analyzer analyzer = new StandardAnalyzer();
        Map<String, Float> boosts = new HashMap<String, Float>();
        boosts.put("name", 200f);
        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer, boosts);

        // Query query = parser.parse("name:lucene OR description:lucene");
        Query query = parser.parse("java");

        System.out.println(query);

        doSearch(query);
    }
}