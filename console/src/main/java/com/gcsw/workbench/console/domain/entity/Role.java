package com.gcsw.workbench.console.domain.entity;

import com.gcsw.workbench.console.domain.annotation.Column;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/21.
 */
public class Role implements Serializable {
    private final static long serialVersionUID = -19714851759L;

    @Column(name = "ROLE_ID", type = Column.DataType.Int, isPrimaryKey = true)
    private int roleId;

    @Column(name = "NAME", type = Column.DataType.String)
    private String name;

    @Column(name = "NOTES", type = Column.DataType.String)
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
