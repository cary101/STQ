package com.cary.stq.service.impl;

import com.cary.stq.dao.ITestItemsDao;
import com.cary.stq.service.ITestItemsService;
import com.cary.stq.to.Testitems;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("testitemsService")
public class TestItemsService implements ITestItemsService {

    @Resource(name = "testitemsDao")
    private ITestItemsDao testItemsDao;

    @Override
    public List<Testitems> search(Testitems testitems) {
        return testItemsDao.search(testitems);
    }

    @Override
    public List<Testitems> getItems() {
        return testItemsDao.getItems();
    }
}
