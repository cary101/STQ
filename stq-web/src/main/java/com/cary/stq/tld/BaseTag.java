package com.cary.stq.tld;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

public class BaseTag extends RequestContextAwareTag {

	private static final long serialVersionUID = -8040650733726241294L;

	protected void inject() {
		WebApplicationContext webApplicationContext = getRequestContext().getWebApplicationContext();
		AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
		autowireCapableBeanFactory.autowireBean(this);
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		return super.doStartTag();
	}
}
