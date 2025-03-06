package com.noorg.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "rti_teacher_area")
@Entity
public class TeacherArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String teacherId;

    private String status;

    private String teacherName;

    private String areaCode;

    private Date createDate;
}
