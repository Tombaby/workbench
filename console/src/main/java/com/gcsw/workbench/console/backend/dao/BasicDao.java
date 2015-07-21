package com.gcsw.workbench.console.backend.dao;

import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/21.
 */
public class BasicDao<T> {

    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;

    public List<T> queryForList(Class<T> clazz, String sql, Map<String, ?> params) throws DaoAccessException {
        List<Map<String, Object>> rowset = this.jdbcTemplate.queryForList(sql, params);
        Field[] fields = clazz.getDeclaredFields();
        List<T>  result = new ArrayList<T>();
        try {
            for(Map<String, Object> row : rowset) {
                T t = clazz.newInstance();
                for(Field field : fields) {
                    Object fieldValue = row.get(field.getName());
                    if (fieldValue == null)
                        continue;
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                    Method setter = pd.getWriteMethod();
                    setter.invoke(t, fieldValue);
                }
                result.add(t);
            }
        } catch (IntrospectionException e) {
            throw new DaoAccessException("BasicDao.queryForList: can't find the setter method!", e);
        } catch (InstantiationException e) {
            throw new DaoAccessException("BasicDao.queryForList: can't init the clazz!", e);
        } catch (IllegalAccessException e) {
            throw new DaoAccessException("BasicDao.queryForList: reflect error!", e);
        } catch (InvocationTargetException e) {
            throw new DaoAccessException("BasicDao.queryForList: setter invoke error!", e);
        }
        return result;
    }

    public T queryForObject(Class<T> clazz, String sql, Map<String, ?> params) throws DaoAccessException {
        SqlRowSet rowSet = this.jdbcTemplate.queryForRowSet(sql, params);
        Field[] fields = clazz.getDeclaredFields();
        try {
            T t = clazz.newInstance();
            for (Field field : fields) {
                if (null == rowSet.getObject(field.getName()))
                    continue;
                Class clz = field.getDeclaringClass();
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method setter = pd.getWriteMethod();
                setter.invoke(t, rowSet.getObject(field.getName()));
            }
            return t;
        } catch (InstantiationException e) {
            throw new DaoAccessException("BasicDao.queryForObject: can't init the clazz!", e);
        } catch (IllegalAccessException e) {
            throw new DaoAccessException("BasicDao.queryForObject: reflect error!", e);
        } catch (IntrospectionException e) {
            throw new DaoAccessException("BasicDao.queryForObject: can't find the setter method!", e);
        } catch (InvocationTargetException e) {
            throw new DaoAccessException("BasicDao.queryForObject: setter invoke error!", e);
        }
    }

    public void update(String sql, Map<String, ?> params) {
        int rows = this.jdbcTemplate.update(sql, params);
    }

}
