package cn.crabapples.application.common.utils.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:application-custom.properties"})
@ConfigurationProperties(prefix = "crabapples.elasticsearch")
@Getter
@Setter
public class ElasticSearchConfigure {
    private String host;
    private int port;
    private String clusterName;

    public RestHighLevelClient getEsClient() {
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(host, port, "http"));
        RestHighLevelClient esClient = new RestHighLevelClient(restClientBuilder);
        return esClient;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
