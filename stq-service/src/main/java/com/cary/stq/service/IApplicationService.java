package com.cary.stq.service;

import com.cary.stq.to.Application;

import java.util.HashMap;
import java.util.List;

public interface IApplicationService {
    List<Application> search(Application application, int page, int limit);

    List<Application> search(Application application);

    Application getAppDetails(HashMap paramMap);

    Application getAppSingle(HashMap paramMap);

    Long getTotalCount(Application application);

    void insert(Application application);

    Integer updateByPKSelective(Application application);

    Integer updateAll(Application application);

    Integer delete(String key);
}
