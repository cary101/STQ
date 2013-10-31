package com.cary.stq.dao;

import com.cary.stq.to.Sample;

import java.util.HashMap;
import java.util.List;

public interface ISampleDao {

    int insert(Sample sample);

    int updateByPK(Sample sample);
//
//    int updateByPKSelective(Linkman linkman);

    List<Sample> search(HashMap<String, String> paramMap);

    Sample getSampleByPK(HashMap<String, String> paramMap);

//    int deleteByPK(Integer linkmanId);

}