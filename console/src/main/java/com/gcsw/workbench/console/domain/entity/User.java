package com.gcsw.workbench.console.domain.entity;

import com.gcsw.workbench.console.domain.annotation.Column;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/21.
 */
public class User implements Serializable {
    private final static long serialVersionUID = -3148799L;

    @Column(name = "USER_ID", type = Column.DataType.Int, isPrimaryKey = true)
    private int userId;

    @Column(name = "USER_NAME", type = Column.DataType.String)
    private String userName;

    @Column(name = "PASSWORD", type = Column.DataType.String)
    private String password;

    @Column(name = "REAL_NAME", type = Column.DataType.String)
    private String realName;

    @Column(name = "EMAIL", type = Column.DataType.String)
    private String email;

    @Column(name = "DEPT_ID", type = Column.DataType.Int)
    private int deptId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
}
