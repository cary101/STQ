package com.cary.stq.dao.impl;

import com.cary.stq.dao.ICodeDAO;
import com.cary.stq.to.Code;
import com.cary.stq.utils.DateUtil;
import com.cary.stq.utils.StringUtils;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("codeDao")
public class CodeDAOImpl extends SqlMapClientDaoSupport implements ICodeDAO {

    public int insert(Code record) {
        return (Integer)getSqlMapClientTemplate().insert("code.insert", record);
    }

    public int updateByPK(Code record) {
        int rows = getSqlMapClientTemplate().update("code.updateByPK", record);
        return rows;
    }

    public int updateByPKSelective(Code record) {
        int rows = getSqlMapClientTemplate().update("code.updateByPKSelective", record);
        return rows;
    }

    public List<Code> search(Code code) {
        List<Code> records = (List<Code>) getSqlMapClientTemplate().queryForList("code.search", code);
        return records;
    }

    public int deleteByPK(Integer codeId) {
        Code key = new Code();
        key.setCodeId(codeId);
        int rows = getSqlMapClientTemplate().delete("code.deleteByPK", key);
        return rows;
    }

    public String getNextSeq() {
        String nextSeq = "";
        Code code = new Code();
        code.setDicCode("APPSEQ");
        code.setItemCode(DateUtil.format(new Date(), "yyyyMM"));
        List<Code> codes = this.search(code);
        if (null == codes || codes.size() == 0) {
            code.setItemValue("0001");
            this.insert(code);
            nextSeq = "0001";
        } else {
            code = codes.get(0);
            nextSeq = StringUtils.lPad(String.valueOf(Integer.valueOf(code.getItemValue()) + 1), 4, "0");
            code.setItemValue(nextSeq);
            this.updateByPKSelective(code);
        }
        return nextSeq;
    }

}