package com.cary.stq.dao;

import com.cary.stq.to.Application;

import java.util.HashMap;
import java.util.List;

public interface IApplicationDao {

    void insert(Application record);

    int updateSingle(Application record);

    int updateByPKSelective(Application record);

    List<Application> search(Application application, int page, int limit);

    List<Application> search(Application application);

    Application getAppSingle(HashMap paramMap);

    Long getTotalCount(Application application);

    int deleteByPK(String applyId);

}