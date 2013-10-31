package com.cary.stq.service;

import com.cary.stq.to.Code;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chen_haibing
 * Date: 12-9-26
 * Time: 下午3:36
 * To change this template use File | Settings | File Templates.
 */
public interface ICodeService {
    List<Code> search(Code code);

    int insert(Code code);

    int updateByPKSelective(Code code);

    String getNextSeq();
}
