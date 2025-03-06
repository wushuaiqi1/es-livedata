package com.noorg.repository.mongo.impl;

import com.noorg.entity.mongo.BeiboRubricRecord;
import com.noorg.repository.mongo.IBeiboRubricRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class BeiboRubricRecordRepositoryImpl implements IBeiboRubricRecordRepository {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<BeiboRubricRecord> listByInteractionIdAndStuId(String interactionId, String stuId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("interactionId").is(interactionId));
        query.addCriteria(Criteria.where("stuid").is(stuId));
        List<BeiboRubricRecord> beiboRubricRecords = mongoTemplate.find(query, BeiboRubricRecord.class);
        if (beiboRubricRecords.isEmpty()) {
            log.warn("[dao]查询数据为空：interactionId:{},stuId:{}", interactionId, stuId);
        }
        return beiboRubricRecords;
    }

    @Override
    public BeiboRubricRecord oneByInteractionIdAndStuIdAndQuestionId(String interactionId, String stuId, String questionId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("interactionId").is(interactionId).and("stuid").is(stuId).and("itemContentId").is(questionId));
        return mongoTemplate.findOne(query, BeiboRubricRecord.class);
    }

    @Override
    public List<BeiboRubricRecord> listByCIdAndCNumAndStuId(String cId, String cNum, String stuId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("cid").is(cId).and("cnum").is(cNum).and("stuid").is(stuId));
        return mongoTemplate.find(query, BeiboRubricRecord.class);
    }
}
