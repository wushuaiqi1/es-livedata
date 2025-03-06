package com.noorg.service;

import com.noorg.consts.EsSearchInteractionType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ServiceTest {

    @Resource
    private ITeacherAreaService teacherAreaService;
    @Resource
    private IEsBeiboDataService esBeiboDataService;

    @Test
    public void test_isReadStatus() {
        boolean readStatus = teacherAreaService.findByTeacherId("11111");
        assert readStatus;
    }

    @Test
    public void test_listByCIdAndCNum() {
        int total = esBeiboDataService.listByCIdAndCNum("ark-1908539", "1", EsSearchInteractionType.TUTOR_PRAISE_TO_WALL_OPEN);
        assert total == 2;

    }
}
