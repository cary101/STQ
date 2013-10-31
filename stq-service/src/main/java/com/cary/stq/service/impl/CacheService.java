package com.cary.stq.service.impl;

import com.cary.stq.service.ICacheService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CacheService implements ICacheService {

    private static Map<String, String> config =new HashMap<String, String>(11);
    static{
        config.put("", "GB2261.1-2003");
        config.put("DICTIONARY_KEY_NATION_CODE", "GBT3304-91");
        config.put("DICTIONARY_KEY_MARRY_STATUS", "GBT2261.2-2003");
        config.put("DICTIONARY_KEY_ENDUCATION_BACKGROUND", "GBT4658-2006");

    }

    private Cache cache;

    @Override
    public Cache getCache() {
        return cache;
    }

    @Override
    public void setCache(Cache cache) {
        this.cache = cache;
    }

    private Object getCache(String key) {
        Element element = cache.get(key);
        if (element != null) {
            return element.getValue();
        }
        return null;
    }

    private void setCache(String key, Object t) {
        if (key != null && !key.equals("")) {
            cache.put(new Element(key, t));
        }
    }

    @Override
    public String getCacheByKey(String val) {
        Set<String> keys = config.keySet();
        for (String key : keys) {
            String value = config.get(key);
            if (value.equals(val)) {
                return key;
            }
        }
        return "";
    }
}
