package com.cary.stq.dao;

import com.cary.stq.to.Code;

import java.util.List;

public interface ICodeDAO {

    int insert(Code record);

    int updateByPK(Code record);

    int updateByPKSelective(Code record);

    List<Code> search(Code code);

    int deleteByPK(Integer codeId);

    public String getNextSeq();

}