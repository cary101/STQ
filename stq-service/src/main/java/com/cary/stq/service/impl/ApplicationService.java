package com.cary.stq.service.impl;

import com.cary.stq.dao.*;
import com.cary.stq.service.IApplicationService;
import com.cary.stq.to.Application;
import com.cary.stq.to.Customer;
import com.cary.stq.to.Linkman;
import com.cary.stq.to.Sample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("applicationService")
public class ApplicationService implements IApplicationService {

    @Resource
    private IApplicationDao applicationDao;

    @Resource
    private ICodeDAO codeDao;

    @Resource
    private ILinkmanDao linkmanDAO;

    @Resource
    private ICustomerDao customerDao;

    @Resource
    private ISampleDao sampleDao;

    @Override
    public List<Application> search(Application application, int page, int limit){
        return  applicationDao.search(application, page, limit);
    }

    @Override
    public List<Application> search(Application application){
        return  applicationDao.search(application);
    }

    @Override
    public Application getAppDetails(HashMap paramMap){
        Application result =  applicationDao.getAppSingle(paramMap);
        if(null != result){
            //联系人信息
            paramMap.put("linkmanId", result.getLinkmanId());
            Linkman linkman = linkmanDAO.getLinkmanByPK(paramMap);
            result.setLinkman(linkman);
            //申请公司信息
            paramMap.put("companyId",result.getApplyCompanyId());
            Customer customer = customerDao.getCompanyByPK(paramMap);
            paramMap.put("sampleId",result.getSampleId());
            //样品信息
            Sample sample = sampleDao.getSampleByPK(paramMap);
            result.setSample(sample);
            result.setCustomer(customer);
        }
        return result;
    }

    @Override
    public Application getAppSingle(HashMap paramMap){
        return applicationDao.getAppSingle(paramMap);
    }

    @Override
    public Long getTotalCount(Application application){
        return applicationDao.getTotalCount(application);
    }

    @Override
    @Transactional
    public void insert(Application application){
        int sId =  sampleDao.insert(application.getSample());
        application.setSampleId(sId);
        applicationDao.insert(application);

    }

    @Override
    public Integer updateByPKSelective(Application application){
        return applicationDao.updateByPKSelective(application);
    }

    @Override
    @Transactional
    public Integer updateAll(Application application){
        int rows = sampleDao.updateByPK(application.getSample());
        return applicationDao.updateSingle(application);
    }

    @Override
    public Integer delete(String key){
        return applicationDao.deleteByPK(key);
    }

}
