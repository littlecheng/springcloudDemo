package com.example.springcloud.pojo;

import java.util.Date;

/**
 * 日志信息
 */
public class LogMess {

    private Long id;
    private Date date;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LogMess( Date date, String content) {
        this.date = date;
        this.content = content;
    }

    public LogMess() {
    }
}
