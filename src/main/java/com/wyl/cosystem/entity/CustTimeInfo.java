package com.wyl.cosystem.entity;

/**
 * @Author: 吴远龙
 * @Date: 2024-09-03 2:31
 */
public class CustTimeInfo {

    String custId;

    Integer times;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public CustTimeInfo(String custId, Integer times) {
        this.custId = custId;
        this.times = times;
    }

    public CustTimeInfo() {
    }
}
