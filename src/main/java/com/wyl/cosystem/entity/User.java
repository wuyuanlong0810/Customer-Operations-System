package com.wyl.cosystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long SerialVersionUID = 1L;
    private int id;

    private String custId;
    private String mobile;
    private String realName;
    private String province;
    private String city;
    private String address;

    private String loginIp;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loginTime;
    private int enabled;
    private int vipLevel;
    private int sendNum;
    private Double pushRatio;
    private Double riskRatio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public int getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        if (sendNum == null) {
            this.sendNum = 0;
        }else{
            this.sendNum = sendNum;
        }

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

    @Override
    public User clone(){
        User cur = new User();
        cur.setSendNum(sendNum);
        cur.setAddress(address);
        cur.setId(id);
        cur.setCity(city);
        cur.setMobile(mobile);
        cur.setLoginIp(loginIp);
        cur.setLoginTime(loginTime);
        cur.setEnabled(enabled);
        cur.setCustId(custId);
        cur.setRiskRatio(riskRatio);
        cur.setVipLevel(vipLevel);
        cur.setRealName(realName);
        cur.setPushRatio(pushRatio);
        cur.setProvince(province);
        return cur;
    }
}