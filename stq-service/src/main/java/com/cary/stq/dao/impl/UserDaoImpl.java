package com.cary.stq.dao.impl;

import com.cary.stq.dao.IUserDao;
import com.cary.stq.to.User;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends SqlMapClientDaoSupport implements IUserDao {

    public UserDaoImpl() {
        super();
    }

    public void insert(User record) {
        getSqlMapClientTemplate().insert("user.insert", record);
    }

    public int updateByPK(User record) {
        int rows = getSqlMapClientTemplate().update("user.updateByPK", record);
        return rows;
    }

    public int updateByPKSelective(User record) {
        int rows = getSqlMapClientTemplate().update("user.updateByPKSelective", record);
        return rows;
    }

    public List<User> search(User user) {
        List<User> records = (List<User>) getSqlMapClientTemplate().queryForList("user.select", user);
        return records;
    }

    public User login(HashMap paramMap) {
        User currentUser = (User) getSqlMapClientTemplate().queryForObject("user.login", paramMap);
        return currentUser;
    }

    public int deleteByPK(Integer userId) {
        User key = new User();
        key.setUserId(userId);
        int rows = getSqlMapClientTemplate().delete("user.deleteByPK", key);
        return rows;
    }

}