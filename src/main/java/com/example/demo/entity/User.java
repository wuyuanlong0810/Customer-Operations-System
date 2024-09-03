package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

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
    private double pushRatio;
    private double riskRatio;

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

    public void setSendNum(int sendNum) {
        this.sendNum = sendNum;
    }

    public double getPushRatio() {
        return pushRatio;
    }

    public void setPushRatio(double pushRatio) {
        this.pushRatio = pushRatio;
    }

    public double getRiskRatio() {
        return riskRatio;
    }

    public void setRiskRatio(double riskRatio) {
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