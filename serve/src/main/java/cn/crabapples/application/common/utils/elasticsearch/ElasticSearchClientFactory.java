package cn.crabapples.application.common.utils.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/10 15:41
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Component
public class ElasticSearchClientFactory {
    private final ElasticSearchConfigure esConfigure;

    public ElasticSearchClientFactory(ElasticSearchConfigure esConfigure) {
        this.esConfigure = esConfigure;
    }

    public RestHighLevelClient getEsClient() {
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(esConfigure.getHost(), esConfigure.getPort(), "http"));
        return new RestHighLevelClient(restClientBuilder);
    }
}
