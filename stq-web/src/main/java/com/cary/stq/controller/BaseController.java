package com.cary.stq.controller;

import com.cary.stq.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 忽略字段绑定异常
        // binder.setIgnoreInvalidFields(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(DateUtil.default_dateformat), true));
    }

    /**
     * 异常控制
     *
     * @param runtimeException
     * @return  return josn string
     */
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody
    Map<String,Object> runtimeExceptionHandler(RuntimeException runtimeException) {
        logger.error(runtimeException.getLocalizedMessage());
        Map model = new HashMap();
        model.put("errorMsg",runtimeException.getLocalizedMessage());
        model.put("statusFlg", false);
        return model;
    }

}
