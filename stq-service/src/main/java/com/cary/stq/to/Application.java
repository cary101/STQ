package com.cary.stq.to;

import com.cary.stq.STQConstant;
import com.cary.stq.utils.DateUtil;

import java.util.Date;

public class Application {
    private Integer id;
    /**
     *
     */
    private String applyId;
    /**
     *
     */
    private String chargeId;
    /**
     *
     */
    private Integer applyCompanyId;
    /**
     *
     */
    private String applyCompanyName;

    private Customer customer;
    /**
     *
     */
    private String linkmanId;
    /**
     *
     */
    private String linkmanName;

    private Linkman linkman;
    /**
     *
     */
    private Integer sampleId;
    /**
     *
     */
    private String sampleName;

    private Sample sample;

    private String sampleReceived;

    private Date sampleReceivedDt;

    /**
     *
     */
    private String testItems;
    /**
     *
     */
    private String otherTestItems;
    /**
     *
     */
    private String serviceType;
    /**
     *
     */
    private String repordId;
    /**
     *
     */
    private String reportLanguage;
    /**
     *
     */
    private String reportType;
    /**
     *
     */
    private String reportCompanyName;
    /**
     *
     */
    private String reportCompanyAddress;
    /**
     *
     */
    private Date reportDt;

    /**
     *
     */
    private String invoiceCommany;
    /**
     *
     */
    private String invoiceCommanyRemarks;
    /**
     *
     */
    private String reportInvoiceTo;
    /**
     *
     */
    private String reportInvoiceToRemarks;
    /**
     *
     */
    private String canSubpackage;
    /**
     *
     */
    private Date customerSignDate;
    /**
     *
     */
    private Date stoSignDate;
    /**
     *  1:apply, 2:verified,3:testing,4:reporting,5:csComfirmed,6:printed,9:reject,99:csDisagree
     */
    private String status;
    /**
     *
     */
    private String sellerId;
    /**
     *
     */
    private String createBy;
    /**
     *
     */
    private Date createDt;
    /**
     *
     */
    private String createDtStr;
    /**
     *
     */
    private String modifiedBy;
    /**
     *
     */
    private Date modifiedDt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public Integer getApplyCompanyId() {
        return applyCompanyId;
    }

    public void setApplyCompanyId(Integer applyCompanyId) {
        this.applyCompanyId = applyCompanyId;
    }

    public String getApplyCompanyName() {
        return applyCompanyName;
    }

    public void setApplyCompanyName(String applyCompanyName) {
        this.applyCompanyName = applyCompanyName;
    }

    public String getLinkmanId() {
        return linkmanId;
    }

    public void setLinkmanId(String linkmanId) {
        this.linkmanId = linkmanId;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public Integer getSampleId() {
        return sampleId;
    }

    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getTestItems() {
        return testItems;
    }

    public void setTestItems(String testItems) {
        this.testItems = testItems;
    }

    public String getOtherTestItems() {
        return otherTestItems;
    }

    public void setOtherTestItems(String otherTestItems) {
        this.otherTestItems = otherTestItems;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getRepordId() {
        return repordId;
    }

    public void setRepordId(String repordId) {
        this.repordId = repordId;
    }

    public String getReportLanguage() {
        return reportLanguage;
    }

    public void setReportLanguage(String reportLanguage) {
        this.reportLanguage = reportLanguage;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportCompanyName() {
        return reportCompanyName;
    }

    public void setReportCompanyName(String reportCompanyName) {
        this.reportCompanyName = reportCompanyName;
    }

    public String getReportCompanyAddress() {
        return reportCompanyAddress;
    }

    public void setReportCompanyAddress(String reportCompanyAddress) {
        this.reportCompanyAddress = reportCompanyAddress;
    }

    public String getInvoiceCommany() {
        return invoiceCommany;
    }

    public void setInvoiceCommany(String invoiceCommany) {
        this.invoiceCommany = invoiceCommany;
    }

    public String getInvoiceCommanyRemarks() {
        return invoiceCommanyRemarks;
    }

    public void setInvoiceCommanyRemarks(String invoiceCommanyRemarks) {
        this.invoiceCommanyRemarks = invoiceCommanyRemarks;
    }

    public String getReportInvoiceTo() {
        return reportInvoiceTo;
    }

    public void setReportInvoiceTo(String reportInvoiceTo) {
        this.reportInvoiceTo = reportInvoiceTo;
    }

    public String getReportInvoiceToRemarks() {
        return reportInvoiceToRemarks;
    }

    public void setReportInvoiceToRemarks(String reportInvoiceToRemarks) {
        this.reportInvoiceToRemarks = reportInvoiceToRemarks;
    }

    public String getCanSubpackage() {
        return canSubpackage;
    }

    public void setCanSubpackage(String canSubpackage) {
        this.canSubpackage = canSubpackage;
    }

    public Date getCustomerSignDate() {
        return customerSignDate;
    }

    public void setCustomerSignDate(Date customerSignDate) {
        this.customerSignDate = customerSignDate;
    }

    public Date getStoSignDate() {
        return stoSignDate;
    }

    public void setStoSignDate(Date stoSignDate) {
        this.stoSignDate = stoSignDate;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusDesc() {
        return STQConstant.getStatusDesc(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDt() {
        return modifiedDt;
    }

    public void setModifiedDt(Date modifiedDt) {
        this.modifiedDt = modifiedDt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Linkman getLinkman() {
        return linkman;
    }

    public void setLinkman(Linkman linkman) {
        this.linkman = linkman;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public String getSampleReceived() {
        return sampleReceived;
    }

    public void setSampleReceived(String sampleReceived) {
        this.sampleReceived = sampleReceived;
    }

    public Date getSampleReceivedDt() {
        return sampleReceivedDt;
    }

    public String getSampleReceivedDtStr() {
        return DateUtil.format(sampleReceivedDt, DateUtil.default_dateformat);
    }

    public void setSampleReceivedDt(Date sampleReceivedDt) {
        this.sampleReceivedDt = sampleReceivedDt;
    }

    public Date getReportDt() {
        return reportDt;
    }

    public String getReportDtStr() {
        return DateUtil.format(reportDt, DateUtil.default_dateformat);
    }

    public void setReportDt(Date reportDt) {
        this.reportDt = reportDt;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getCreateDtStr() {
        return DateUtil.format(this.createDt, DateUtil.default_dateformat);
    }

    public void setCreateDtStr(String createDtStr) {
        this.createDtStr = createDtStr;
    }
}