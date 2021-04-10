package cn.crabapples;

import cn.crabapples.application.common.utils.elasticsearch.ElasticSearchClientFactory;
import cn.crabapples.application.common.utils.elasticsearch.ElasticSearchConfigure;
import cn.crabapples.application.common.utils.elasticsearch.ElasticSearchUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ElasticSearchTest {
    private final String INDEX_NAME = "papers";

    @Getter
    @Setter
    static class DemoIndexEntity {
        private String name = "张三";
        private int age = 20;
        private int sex = 0;
        private String[] likes = {"吉他", "画画"};
        private int delFlag = 0;

        @Override
        public String toString() {
            return JSONObject.toJSONString(this);
        }
    }


    @Autowired
    ElasticSearchClientFactory esClientFactory;
    @Autowired
    ElasticSearchUtils elasticSearchUtils;
    @Autowired
    ElasticSearchConfigure esConfig;
    @Autowired
    ApplicationContext context;
    RestHighLevelClient esClient = null;

    @Test
    public void esTest() throws IOException {
        esClient = esClientFactory.getEsClient();
//        createIndex();
        insertData();
//        SearchRequest request = new SearchRequest(INDEX_NAME);
//        SearchHits searchHits = esClient.search(request, RequestOptions.DEFAULT).getHits();
//        System.err.println(searchHits.getTotalHits().value);
//        searchHits.forEach(System.err::println);
        elasticSearchUtils.findAll(INDEX_NAME).forEach(e -> {
            System.err.println(e.getSourceAsString());
        });
    }

    void createIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest(INDEX_NAME);
        boolean exists = esClient.indices().exists(request, RequestOptions.DEFAULT);
        if (!exists) {
            CreateIndexRequest createRequest = new CreateIndexRequest(INDEX_NAME);
            CreateIndexResponse response = esClient.indices().create(createRequest, RequestOptions.DEFAULT);
        }
    }

    void insertData() throws IOException {
        Map<String, Object> demoMap = new HashMap<>();
        demoMap.put("name", "张三");
        demoMap.put("age", 20);
        demoMap.put("sex", 0);
        demoMap.put("likes", new String[]{"吉他", "画画"});
        IndexRequest request = new IndexRequest(INDEX_NAME).source(INDEX_NAME, demoMap);
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
        System.err.println(response);
    }

}
