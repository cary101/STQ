package com.cary.stq.service;

import com.cary.stq.to.User;

import java.util.HashMap;
import java.util.List;

public interface IUserService {

    List<User> search(User user);

    User login(HashMap paramMap);
}
