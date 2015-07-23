package com.gcsw.workbench.console.backend.dao;

import com.gcsw.workbench.console.domain.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2015/7/23.
 */
public class UserDao extends BasicDao<User> {

    public List<User> fetchAllUsers() throws DaoAccessException {
        return this.queryList(User.class, "SELECT USER_ID, USER_NAME, PASSWORD, REAL_NAME, EMAIL, DEPT_ID", null);
    }
}
