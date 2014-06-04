package com.cary.scheduler.core;

import com.cary.stq.utils.DateUtil;
import com.cary.stq.utils.ObjectUtil;
import com.cary.stq.utils.StringUtils;


import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

public class JaxbDateAdapter extends XmlAdapter<String, Date> {

	private String pattern = "yyyyMMdd hh:mm:ss";

	@Override
	public Date unmarshal(String dateStr) throws Exception {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		return DateUtil.parse(dateStr, pattern);
	}

	@Override
	public String marshal(Date date) throws Exception {
		if (ObjectUtil.isNullOrEmpty(date)) {
			return null;
		}
		return DateUtil.format(date, pattern);
	}

}
