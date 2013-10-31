package com.cary.stq.dao;

import com.cary.stq.to.WorkDayTo;

import java.util.List;

/**
 * DAO interface of WorkDay
 */
public interface IWorkDayDao {

    /**
     * 删除工作日设定
     *
     * @param yearAndMonth
     * @return
     * @author Chen HaiBing
     */
    int deleteWorkDays(String yearAndMonth);

//    /**
//     * @return
//     */
//    WorkDayTo getToDay();

    /**
     * @return
     */
    List<WorkDayTo> getWorkDays(String workMonth);

//    /**
//     * 获取非工作日天数
//     *
//     * @param from
//     * @param to
//     * @return int
//     * @author Chen HaiBing
//     */
//    int getNonworkDays(String from, String to);
//
//    /**
//     * 获取最小的工作日
//     *
//     * @param date
//     * @return String
//     * @author Chen HaiBing
//     */
//    String getMinworkDay(String date);
//
//    /**
//     * 获取非工作日
//     *
//     * @param from
//     * @param to
//     * @return int
//     * @author Chen HaiBing
//     */
//    List<WorkDayTo> getNonworkDay(String from, String to);

}