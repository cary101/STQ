package com.cary.stq.to;

import java.util.List;

public class Customer {

    private Integer companyId;

    private String companyName;

    private String shortName;

    private String companyNameE;

    private String companyAddress;

    private String companyAddressE;

    private String postCode;

    private String linkmanIds;

    private List<Linkman> linkmen;

    private String webAddress;

    private String help;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public String getCompanyNameE() {
        return companyNameE;
    }

    public void setCompanyNameE(String companyNameE) {
        this.companyNameE = companyNameE;
    }

    public String getCompanyAddressE() {
        return companyAddressE;
    }

    public void setCompanyAddressE(String companyAddressE) {
        this.companyAddressE = companyAddressE;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public List<Linkman> getLinkmen() {
        return linkmen;
    }

    public void setLinkmen(List<Linkman> linkmen) {
        this.linkmen = linkmen;
    }

    public String getLinkmanIds() {
        return linkmanIds;
    }

    public void setLinkmanIds(String linkmanIds) {
        this.linkmanIds = linkmanIds;
    }
}