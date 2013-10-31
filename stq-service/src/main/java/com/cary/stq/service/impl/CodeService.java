package com.cary.stq.service.impl;

import com.cary.stq.dao.ICodeDAO;
import com.cary.stq.service.ICodeService;
import com.cary.stq.to.Code;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("codeService")
public class CodeService implements ICodeService {

    @Resource(name = "codeDao")
    private ICodeDAO codeDao;

    @Override
    public List<Code> search(Code code) {
        return codeDao.search(code);
    }

    @Override
    public int insert(Code code) {
        return codeDao.insert(code);
    }

    @Override
    public int updateByPKSelective(Code code) {
        return codeDao.updateByPKSelective(code);
    }

    @Override
    public String getNextSeq() {
        String nextSeq = "";
        codeDao.getNextSeq();
        return nextSeq;
    }
}
