package com.noorg.repository.mongo;

import com.noorg.entity.mongo.BeiboRubricRecord;

import java.util.List;

public interface IBeiboRubricRecordRepository{

    /**
     * 获取一次主观题互动
     *
     * @param interactionId 互动Id
     * @param stuId         学生Id
     * @return 列表
     */
    List<BeiboRubricRecord> listByInteractionIdAndStuId(String interactionId, String stuId);

    /**
     * 获取一道主观题记录
     *
     * @param interactionId 互动id
     * @param stuId         学生id
     * @param questionId    题目id
     * @return 当前这一道题记录
     */
    BeiboRubricRecord oneByInteractionIdAndStuIdAndQuestionId(String interactionId, String stuId, String questionId);

    /**
     * 获取学生班讲主观题记录
     *
     * @param cId   班级id
     * @param cNum  讲次编号
     * @param stuId 学生id
     * @return 讲次学生主观题记录
     */
    List<BeiboRubricRecord> listByCIdAndCNumAndStuId(String cId, String cNum, String stuId);

}
