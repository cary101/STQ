package com.cary.stq.service.impl;

import com.cary.stq.dao.IUserDao;
import com.cary.stq.service.IUserService;
import com.cary.stq.to.User;
import java.util.HashMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserService implements IUserService {

    @Resource(name = "userDao")
    private IUserDao userDao;

    @Override
    public List<User> search(User user){
        return  userDao.search(user);
    }

    @Override
    public User login(HashMap paramMap){
        return  userDao.login(paramMap);
    }

}
