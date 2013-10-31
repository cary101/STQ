package com.cary.stq.dao.impl;

import com.cary.stq.dao.BaseDao;
import com.cary.stq.dao.IWorkDayDao;
import com.cary.stq.to.WorkDayTo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO implement of workDay
 * 
 */
@Repository("workDayDao")
public class WorkDayDaoImpl extends BaseDao implements IWorkDayDao {

	@Override
	public int deleteWorkDays(String yearAndMonth) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("workMonth",yearAndMonth);
        int result = delete("workday.deleteByPK",paramMap);
        return result;
	}

	@Override
	public List<WorkDayTo> getWorkDays(String workMonth){
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("workMonth", workMonth);
        List<WorkDayTo> records = (List<WorkDayTo>) getSqlMapClientTemplate().queryForList("workday.search", paramMap);
        return records;
	}
//
//	/**
//	 * 获取非工作日天数
//	 * @param       from
//	 * @param 		to
//	 * @return		int
//	 * @author 		lifangfang
//	 */
//	@Override
//	public int getNonworkDays(String from, String to) {
//		StringBuilder sql = new StringBuilder(
//				"select count(*) from workday t where t.isworkday = 0 ")
//				.append("and to_date(t.naturalday,'yyyy-mm-dd')>to_date('")
//				.append(from).append("','yyyy-mm-dd') ")
//				.append("and to_date(t.naturalday,'yyyy-mm-dd')<=to_date('")
//				.append(to).append("','yyyy-mm-dd') ");
//		return simpleJdbcTemplate.queryForInt(sql.toString());
//	}
//
//	/**
//	 * 获取最小的工作日
//	 * @param 		date
//	 * @return		String
//	 * @author 		lifangfang
//	 */
//	@Override
//	public String getMinworkDay(String date) {
//		StringBuilder sql = new StringBuilder(
//				"select min(t.naturalday) naturalday from workday t where t.isworkday = 1 ")
//				.append("and to_date(t.naturalday,'yyyy-mm-dd')>=to_date('")
//				.append(date).append("','yyyy-mm-dd') ");
//		return get(sql.toString(), new Criteria()).getNaturalDay();
//	}
//
//	@Override
//	public List<WorkDayTo> getNonworkDay(String from, String to) {
//		StringBuilder sql = new StringBuilder(
//				"select t.naturalday,t.isworkday from workday t where t.isworkday = 0 ")
//				.append("and to_date(t.naturalday,'yyyy-mm-dd')>to_date('")
//				.append(from).append("','yyyy-mm-dd') ")
//				.append("and to_date(t.naturalday,'yyyy-mm-dd')<=to_date('")
//				.append(to).append("','yyyy-mm-dd') ");
//		List<WorkDayTo> list = getList(sql.toString());
//		return list;
//	}

}