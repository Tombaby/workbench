package com.gcsw.workbench.console.domain.entity;

import com.gcsw.workbench.console.domain.annotation.Column;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/21.
 */
public class Resource implements Serializable {
    private final static long serialVersionUID = -9275897157L;

    @Column(name = "RES_ID", type = Column.DataType.Int, isPrimaryKey = true)
    private int resId;

    @Column(name = "NAME", type = Column.DataType.String)
    private String name;

    @Column(name = "RES_URI", type = Column.DataType.String)
    private String resURI;

    @Column(name = "RES_CTX", type = Column.DataType.String)
    private String resCtx;

    @Column(name = "NOTES", type = Column.DataType.String)
    private String notes;

    @Column(name = "RES_TYPE", type = Column.DataType.String)
    private String resType;

    @Column(name = "STATUS", type = Column.DataType.Int)
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
