package com.gcsw.workbench.console.domain.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/21.
 */
public class Department implements Serializable {
    private final static long serialVersionUID = -93749385985L;

    private int deptId;
    private String name;
    private int parentId;
    private String notes;
    private int leaderId;
    private int corpId;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public int getCorpId() {
        return corpId;
    }

    public void setCorpId(int corpId) {
        this.corpId = corpId;
    }
}
