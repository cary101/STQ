package com.cary.stq.dao;

import com.cary.stq.to.Linkman;

import java.util.HashMap;
import java.util.List;

public interface ILinkmanDao {

//    void insert(Linkman linkman);
//
//    int updateByPK(Linkman linkman);
//
//    int updateByPKSelective(Linkman linkman);

    List<Linkman> search(HashMap<String, String> paramMap);

    Linkman getLinkmanByPK(HashMap<String, String> paramMap);

//    int deleteByPK(Integer linkmanId);

}