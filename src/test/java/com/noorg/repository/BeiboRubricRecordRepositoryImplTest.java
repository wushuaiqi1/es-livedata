package com.noorg.repository;

import com.noorg.entity.mongo.BeiboRubricRecord;
import com.noorg.repository.mongo.IBeiboRubricRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@Slf4j
public class BeiboRubricRecordRepositoryImplTest {
    @Resource
    private IBeiboRubricRecordRepository beiboRubricRecordRepository;


    @Test
    public void test_listByInteractionIdAndStuId(){
        String interactionId = "e6aff086-a949-4acb-afc3-a76643d908ae#be0c4c57-a756-4087-ade0-fe2001dc149d";
        String stuId = "503561";
        List<BeiboRubricRecord> beiboRubricRecords = beiboRubricRecordRepository.listByInteractionIdAndStuId(interactionId, stuId);
        assert !beiboRubricRecords.isEmpty();
    }

    @Test
    public void test_listByCIdAndCNumAndStuId(){
        List<BeiboRubricRecord> beiboRubricRecords = beiboRubricRecordRepository.listByCIdAndCNumAndStuId("1908605", "1", "503805");
        assert !beiboRubricRecords.isEmpty();
    }

    @Test
    public void test_oneByInteractionIdAndStuIdAndQuestionId(){
        String interactionId = "99990e44-c821-45d8-8f15-cee14555a53f#dac9c280-7304-4612-a8a9-d714df5bbd3e";
        String stuId = "514588";
        String itemContentId = "11101101100";
        BeiboRubricRecord beiboRubricRecord = beiboRubricRecordRepository.oneByInteractionIdAndStuIdAndQuestionId(interactionId, stuId, itemContentId);
        assert Objects.nonNull(beiboRubricRecord);
    }
}
