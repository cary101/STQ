package com.cary.scheduler.core;

import com.founder.fasf.util.DateUtil;
import com.founder.fasf.util.ObjectUtil;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

public class JaxbDateAdapter extends XmlAdapter<String, Date> {

	private String pattern = "yyyyMMdd hh:mm:ss";

	@Override
	public Date unmarshal(String dateStr) throws Exception {
		if (ObjectUtil.isNullOrEmpty(dateStr)) {
			return null;
		}
		return DateUtil.parseSimpleDate(dateStr, pattern);
	}

	@Override
	public String marshal(Date date) throws Exception {
		if (ObjectUtil.isNullOrEmpty(date)) {
			return null;
		}
		return DateUtil.toFormatString(pattern, date);
	}

}
