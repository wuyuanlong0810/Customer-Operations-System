package com.wyl.cosystem.entity;

public class BatchQueryInfo {
    Integer sendLimit;
    Double pushRatio;
    Double riskRatio;
    String provFilterType;
    String province;
    String[] provinces;

    public String[] getProvinces() {
        return provinces;
    }

    public void setProvinces(String[] provinces) {
        this.provinces = provinces;
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

    public String getProvFilterType() {
        return provFilterType;
    }

    public void setProvFilterType(String provFilterType) {
        this.provFilterType = provFilterType;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
