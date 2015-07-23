package com.gcsw.workbench.console.backend.dao;

import com.gcsw.workbench.console.domain.entity.Department;

import java.util.List;

/**
 * Created by Administrator on 2015/7/23.
 */
public class DepartmentDao extends BasicDao<Department> {
    public List<Department> fetchAllDepartments() throws DaoAccessException {
        return this.queryList(Department.class, "SELECT DEPT_ID, NAME, PARENT_ID, NOTES, LEADER_ID, LOCATION, ADDRESS, TEL, FAX, POST_CODE FROM CMS_DEPTS", null);
    }
}
