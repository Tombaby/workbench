package com.gcsw.workbench.console.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcsw.workbench.console.backend.dao.DaoAccessException;
import com.gcsw.workbench.console.backend.dao.DepartmentDao;
import com.gcsw.workbench.console.backend.dao.UserDao;
import com.gcsw.workbench.console.domain.entity.Department;
import com.gcsw.workbench.console.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.JacksonObjectMapperFactoryBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/23.
 */
@Service("userService")
public class UserService {
    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private UserDao userDao;

    public String fetchAllDepartmentsAsJsonStr() throws ServiceException {
        try {
            List<Department> departments = departmentDao.fetchAllDepartments();
            List<User> users = userDao.fetchAllUsers();
            Map<Integer, Department> temp = new HashMap<Integer, Department>();
            Department root = new Department();
            root.setChildren(new ArrayList<Department>());
            root.setDeptId(0);
            root.setParentId(-1);
            root.setName("Thoughtful Organization");
            for(Department dpt : departments){
                dpt.setUsers(new ArrayList<User>());
                temp.put(dpt.getDeptId(), dpt);
                if(dpt.getChildren() == null) {
                    dpt.setChildren(new ArrayList<Department>());
                }
            }
            for(Department dpt : departments) {
                Department tdpt = temp.get(dpt.getParentId());
                if (tdpt == null) {
                    root.getChildren().add(dpt);
                } else {
                    tdpt.getChildren().add(dpt);
                }
            }
            for(User usr : users) {
                temp.get(usr.getDeptId()).getUsers().add(usr);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(root);
            } catch (JsonProcessingException e) {
                throw new ServiceException("UserService.fetchAllDepartments: Convert Object to Json error.", e);
            }
        } catch (DaoAccessException e) {
            throw new ServiceException(e);
        }
    }


}
