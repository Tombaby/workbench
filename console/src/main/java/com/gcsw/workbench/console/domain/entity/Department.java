package com.gcsw.workbench.console.domain.entity;

import com.gcsw.workbench.console.domain.annotation.Column;
import com.gcsw.workbench.console.domain.annotation.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/7/21.
 */
@Table(name = "CMS_DEPTS")
public class Department implements Serializable {
    private final static long serialVersionUID = -93749385985L;

    @Column(name = "DEPT_ID", type = Column.DataType.Int, isPrimaryKey = true)
    private int deptId;

    @Column(name = "NAME", type = Column.DataType.String)
    private String name;

    @Column(name = "PARENT_ID", type = Column.DataType.Int)
    private int parentId;

    @Column(name = "NOTES", type = Column.DataType.String)
    private String notes;

    @Column(name = "LEADER_ID", type = Column.DataType.Int)
    private int leaderId;

    @Column(name = "LOCATION", type = Column.DataType.String)
    private String location;

    @Column(name = "ADDRESS", type = Column.DataType.String)
    private String address;

    @Column(name = "TEL", type = Column.DataType.String)
    private String tel;

    @Column(name = "FAX", type = Column.DataType.String)
    private String fax;

    @Column(name = "POST_CODE", type = Column.DataType.String)
    private String postCode;

    private List<Department> children;

    private List<User> users;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }
}
