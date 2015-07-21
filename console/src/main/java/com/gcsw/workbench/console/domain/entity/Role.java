package com.gcsw.workbench.console.domain.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/21.
 */
public class Role implements Serializable {
    private final static long serialVersionUID = -19714851759L;

    private int roleId;
    private String name;
    private String notes;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
