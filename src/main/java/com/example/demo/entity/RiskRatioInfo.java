package com.example.demo.entity;

/**
 * @Author: 吴远龙
 * @Date: 2024-09-03 5:23
 */
public class RiskRatioInfo {
    String custId;

    Double riskRatio;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Double getRiskRatio() {
        return riskRatio;
    }

    public void setRiskRatio(Double riskRatio) {
        this.riskRatio = riskRatio;
    }

    public RiskRatioInfo(String custId, Double riskRatio) {
        this.custId = custId;
        this.riskRatio = riskRatio;
    }

    public RiskRatioInfo() {
    }
}
