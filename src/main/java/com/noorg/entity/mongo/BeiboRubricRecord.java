package com.noorg.entity.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(value = "beibo_rubric_record")
// 主观题记录表
// questionNum记录题目序号、interactionId标识1次互动，通过互动Id+题目Id来确认「唯一」
public class BeiboRubricRecord {
    @Id
    private String id;
    /**
     * 互动id
     */
    private String interactionId;
    /**
     * 学生id
     */
    private String stuid;
    /**
     * 学生姓名
     */
    private String stuname;
    /**
     * 题目序号
     */
    private String questionNum;
    /**
     * 虚拟题目id
     */
    private String itemContentId;
    /**
     * 虚拟题目名称
     */
    private String itemContentName;
    /**
     * 主讲老师id
     */
    private String tchid;
    /**
     * 辅导id
     */
    private String tutorId;
    /**
     * 辅导姓名
     */
    private String tutorName;
    /**
     * 直播编号
     */
    private String lnum;
    /**
     * 小班id
     */
    private String cid;
    /**
     * 课次号
     */
    private String cnum;
    /**
     * 课次结束时间
     */
    private String cEndTime;
    /**
     * 学生按键时间
     */
    private Date stuSubmitTime;
    /**
     * 学生作答耗时
     */
    private long stuSpendTime;
    /**
     * 批改结果
     */
    private String correctResult;
    /**
     * 学生作答图片
     */
    private String stuSubmitImages;
    /**
     * 批改时间
     */
    private Date correctTime;
    /**
     * 开启互动时间
     */
    private Date openTime;
    /**
     * 订正结果
     */
    private String reviseCorrectResult;
    /**
     * 订正时间
     */
    private Date reviseCorrectTime;
    /**
     * 备注
     */
    private String intro;
    /**
     * 是否为补题 0 否 1 是
     */
    private String supplement;
    /**
     * 佩奇uuid
     */
    private String uuid;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
}
