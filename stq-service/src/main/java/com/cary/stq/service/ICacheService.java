package com.cary.stq.service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public interface ICacheService {
    Cache getCache();

    void setCache(Cache cache);

    String getCacheByKey(String val);
}
