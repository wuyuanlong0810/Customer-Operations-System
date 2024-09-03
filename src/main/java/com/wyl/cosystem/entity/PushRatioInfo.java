package com.wyl.cosystem.entity;

/**
 * @Author: 吴远龙
 * @Date: 2024-09-03 2:31
 */
public class PushRatioInfo {

    String custId;

    Double pushRatio;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Double getPushRatio() {
        return pushRatio;
    }

    public void setPushRatio(Double pushRatio) {
        this.pushRatio = pushRatio;
    }

    public PushRatioInfo(String custId, Double pushRatio) {
        this.custId = custId;
        this.pushRatio = pushRatio;
    }

    public PushRatioInfo() {
    }
}
