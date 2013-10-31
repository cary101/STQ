package com.cary.stq.dao;

import com.cary.stq.to.User;

import java.util.HashMap;
import java.util.List;

public interface IUserDao {

    void insert(User record);

    int updateByPK(User record);

    int updateByPKSelective(User record);

    List<User> search(User user);

    int deleteByPK(Integer userId);

    User login(HashMap paramMap);

}