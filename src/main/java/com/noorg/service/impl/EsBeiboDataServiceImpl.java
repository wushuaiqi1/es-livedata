package com.noorg.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.noorg.config.ElasticSearchClientConfig;
import com.noorg.service.IEsBeiboDataService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
@Slf4j
public class EsBeiboDataServiceImpl implements IEsBeiboDataService {
    @Resource
    private RestHighLevelClient restHighLevelClient;
    @Resource
    private ElasticSearchClientConfig elasticSearchClientConfig;


    @Override
    public int listByCIdAndCNum(String cId, String cNum, String interactionName) {
        int total = 0;
        // 创建查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("class_id.keyword", cId));
        boolQueryBuilder.must(QueryBuilders.termQuery("lesson_num.keyword", cNum));
        boolQueryBuilder.must(QueryBuilders.termQuery("event_name.keyword", interactionName));
        // 创建搜索构造器
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(boolQueryBuilder);
        // 创建查询请求
        SearchRequest searchRequest = new SearchRequest(elasticSearchClientConfig.getBeiboIndex());
        searchRequest.source(searchSourceBuilder);
        try {
            // 发送请求
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length == 0) {
                return 0;
            }
            for (SearchHit hit : hits) {
                log.info("{}", hit.getSourceAsString());
                JSONArray stuInfoList = JSON.parseObject(hit.getSourceAsString()).getJSONArray("stu_info_list");
                for (int i = 0; i < stuInfoList.size(); i++) {
                    JSONObject stuInfoArrayJSONObject = stuInfoList.getJSONObject(i);
                    log.info("学生信息：{}", stuInfoArrayJSONObject);
                    String stuId = stuInfoArrayJSONObject.getString("stuId");
                    if (stuId.equals("2")) {
                        total++;
                    }
                }
            }
        } catch (IOException e) {
            log.warn("[es]getStudentPraiseToWallTotal {},{},{},err:{}", cId, cNum, interactionName, e.getMessage());
        }
        return total;
    }
}
