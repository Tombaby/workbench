package com.gcsw.workbench.console.domain.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/21.
 */
public class Resource implements Serializable {
    private final static long serialVersionUID = -9275897157L;

    private int resId;
    private String name;
    private String resURI;
    private String resCtx;
    private String notes;
    private String resType;
    private int status;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResURI() {
        return resURI;
    }

    public void setResURI(String resURI) {
        this.resURI = resURI;
    }

    public String getResCtx() {
        return resCtx;
    }

    public void setResCtx(String resCtx) {
        this.resCtx = resCtx;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
