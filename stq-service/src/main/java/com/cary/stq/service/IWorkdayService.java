/**
 * Copyright (c) 2010-2020 Founder International Co., Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Founder. You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the agreements you entered into with Founder.
 */

package com.cary.stq.service;

import com.cary.stq.to.WorkDayTo;

import java.util.List;

public interface IWorkdayService {

    /**
     * 查询某月的工作日设定
     *
     * @param naturalDay
     * @return List<WorkDayTo>
     */
    public List<WorkDayTo> getWorkDays(String naturalDay);

    /**
     * 设定某月的工作日
     *
     * @param workDays
     * @return
     * @author chenhaibing
     */
    public int insertWorkDays(String isSetted, List<WorkDayTo> workDays);


//    /**
//     * 更新某天的工作日设定
//     *
//     * @param workDay
//     * @return
//     */
//    public int updateWorkDay(WorkDayTo workDay);
//
//	/**
//	 *
//	 * @return
//	 */
//	List<WorkDayTo> getWorkDays();
//
//	/**
//	 *
//	 * @return
//	 * @author Chen Maohua
//	 */
//    WorkDayTo getToDay();

}