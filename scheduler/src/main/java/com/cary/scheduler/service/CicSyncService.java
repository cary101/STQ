package com.cary.scheduler.service;


import com.cary.scheduler.core.Task;
import com.cary.scheduler.core.TaskBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Cary
 * Date: 15/5/14
 * Time: 3:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("cicMhmSyncService")
@TaskBean(description = "cic市民卡精神病指标")
public class CicSyncService implements Task {

    Logger loggger = LoggerFactory.getLogger(this.getClass());

    public void run(Map<String, Object> data) {

        System.out.println("aaaaaaaaaaaaaaa");
    }

}
