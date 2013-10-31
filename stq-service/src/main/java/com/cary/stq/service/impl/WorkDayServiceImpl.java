package com.cary.stq.service.impl;

import com.cary.stq.service.IWorkdayService;
import com.cary.stq.utils.ObjectUtil;
import com.cary.stq.to.WorkDayTo;
import com.cary.stq.dao.impl.WorkDayDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("workdayService")
public class WorkDayServiceImpl implements IWorkdayService {

    @Resource
    private WorkDayDaoImpl workDayDao;

    /**
     * 查询某月的工作日设定
     *
     * @param naturalDay
     * @return List<WorkDayTo>
     */
    @Override
    public List<WorkDayTo> getWorkDays(String naturalDay) {
        List<WorkDayTo> result = null;
        if (ObjectUtil.isNotEmpty(naturalDay)) {
            String workMonth = naturalDay.substring(0, naturalDay.length() - 3);
            result = workDayDao.getWorkDays(workMonth);
        }
        return result;
    }

    /**
     * 设定某月的工作日
     *
     * @param workDays
     * @return
     * @author chenhaibing
     */
//    @Transactional
    public int insertWorkDays(String isSetted, List<WorkDayTo> workDays) {
        int result = 0;
        if (ObjectUtil.isNotEmpty(workDays)) {
            try {
                if ("0".equals(isSetted)) {
                    workDayDao.batchInsert("workday.insert", workDays);
                } else if ("1".equals(isSetted)) {
                    String yearAndMonth = workDays.get(0).getNaturalDay().substring(0, 7);
                    workDayDao.deleteWorkDays(yearAndMonth);
                    workDayDao.batchInsert("workday.insert", workDays);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;

    }


//    /**
//     * 更新某天的工作日设定
//     *
//     * @param workDay
//     * @return
//     */
//    @Override
//    public int updateWorkDay(WorkDay workDay) {
//        int result = 0;
//        if (ObjectUtil.isNotEmpty(workDay)) {
//            result = genericDao.update(workDay);
//        }
//        return result;
//    }
//
//    @Override
//    public WorkDay getToDay() {
//        WorkDay today = workDayDao.getToDay();
//        if (today == null) {
//            today = new WorkDay();
//            today.setNaturalDay(DateUtil.toFormatString("yyy-MM-dd", new Date()));
//            today.setIsWorkDay("1");
//        }
//        return today;
//    }
//
//    @Override
//    public List<WorkDayTo> getWorkDays() {
//        List<WorkDayTo> workDayList = workDayDao.getWorkDays();
//        if (workDayList == null || workDayList.size() < 3) {
//            workDayList = new ArrayList<WorkDayTo>();
//            workDayList.add(createWorkDay(0));
//            workDayList.add(createWorkDay(-1));
//            workDayList.add(createWorkDay(-2));
//        }
//        return workDayList;
//    }
//
//
//    private WorkDay createWorkDay(int next) {
//        WorkDay workday = new WorkDay();
//        workday.setIsWorkDay("1");
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, next);
//        Date date = cal.getTime();
//        workday.setNaturalDay(DateUtil.toFormatString("yyy-MM-dd", date));
//        return workday;
//    }


}