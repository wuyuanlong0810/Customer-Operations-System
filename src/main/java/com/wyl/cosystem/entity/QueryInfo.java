package com.wyl.cosystem.entity;

public class QueryInfo {
    private String custId;
    private String mobile;
    private Integer sendLimit = null;
    private Double pushRatio = null;
    private Double riskRatio = null;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSendLimit() {
        return sendLimit;
    }

    public void setSendLimit(Integer sendLimit) {
        this.sendLimit = sendLimit;
    }

    public Double getPushRatio() {
        return pushRatio;
    }

    public void setPushRatio(Double pushRatio) {
        this.pushRatio = pushRatio;
    }

    public Double getRiskRatio() {
        return riskRatio;
    }

    public void setRiskRatio(Double riskRatio) {
        this.riskRatio = riskRatio;
    }

    public QueryInfo(String custId, String mobile, Integer sendLimit, Double pushRatio, Double riskRatio) {
        this.custId = custId;
        this.mobile = mobile;
        this.sendLimit = sendLimit;
        this.pushRatio = pushRatio;
        this.riskRatio = riskRatio;
    }
}
