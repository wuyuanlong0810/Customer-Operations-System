package com.example.demo.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private static final long SerialVersionUID = 1L;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    Date time;

    String custId;

    public Message(Date time, String custId){
        this.time = time;
        this.custId = custId;
    }

    public Message() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }
}
