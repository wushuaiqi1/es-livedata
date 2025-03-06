package com.noorg.service;


import java.util.List;

public interface IEsBeiboDataService {
    /**
     * 获取班讲所有表扬上墙互动
     * @param cId 班级id
     * @param cNum 讲次序号
     * @return 列表
     */
    int listByCIdAndCNum(String cId,String cNum,String interactionName);
}
