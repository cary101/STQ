package com.cary.stq.dao;

import com.cary.stq.to.Testitems;

import java.util.List;

public interface ITestItemsDao {

    void insert(Testitems record);

    int updateByPK(Testitems record);

    int updateByPKSelective(Testitems record);

    List<Testitems> search(Testitems user);

    List<Testitems> getItems();

    int deleteByPK(Integer userId);

}