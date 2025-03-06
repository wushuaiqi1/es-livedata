package com.noorg.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson2.JSONWriter;
import com.noorg.entity.mysql.TeacherArea;
import com.noorg.repository.mysql.ITeacherAreaRepository;
import com.noorg.service.ITeacherAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.domain.Example.*;


@Service
@Slf4j
public class TeacherAreaServiceImpl implements ITeacherAreaService {

    @Resource
    private ITeacherAreaRepository teacherAreaRepository;

    @Override
    public boolean findByTeacherId(String teacherId) {
        TeacherArea teacherArea = new TeacherArea();
        log.info("findByTeacherId {}", JSON.toJSONString(teacherArea, SerializerFeature.WriteMapNullValue));
        Optional<TeacherArea> teacherAreaRepositoryOne = teacherAreaRepository.findOne(of(teacherArea));
        return teacherAreaRepositoryOne.isPresent();
    }
}
