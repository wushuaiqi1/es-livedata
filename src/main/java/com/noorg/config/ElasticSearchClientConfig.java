package com.noorg.config;

import lombok.Getter;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ElasticSearchClientConfig {
    @Value("${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private Integer port;
    @Value("${elasticsearch.username}")
    private String username;
    @Value("${elasticsearch.password}")
    private String password;
    @Value("${elasticsearch.data.index}")
    private String dataIndex; // 直播
    @Value("${elasticsearch.beibo.index}")
    private String beiboIndex; // 备播

    public String getBeiboEsUrl() {
        return "/" + this.beiboIndex + "/_search";
    }

    @Bean
    public RestClient restClient() {
        return this.restClientBuilder().build();
    }

    @Bean(name = "RestHighLevelClient")
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(this.restClientBuilder());
    }

    private RestClientBuilder restClientBuilder() {
        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost(host, port));

        //安全认证
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        clientBuilder.setHttpClientConfigCallback((httpClientBuilder) -> {
            //配置异步请求的线程数量，Apache Http Async Client默认启动一个调度程序线程，以及由连接管理器使用的许多工作线程
            //（与本地检测到的处理器数量一样多，取决于Runtime.getRuntime().availableProcessors()返回的数量）。线程数可以修改如下,
            //  这里是修改为1个线程，即默认情况
            httpClientBuilder.setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(8).build());
            //密码账号验证
            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
        });

        //配置请求超时，将连接超时（默认为1秒）和套接字超时（默认为30秒）增加，
        //这里配置完应该相应地调整最大重试超时（默认为30秒），即上面的setMaxRetryTimeoutMillis，一般于最大的那个值一致即60000
        clientBuilder.setRequestConfigCallback((requestConfigBuilder) -> {
            // 连接5秒超时，套接字连接60s超时
            return requestConfigBuilder.setConnectTimeout(5000).setSocketTimeout(60000);
        });
        return clientBuilder;
    }
}
