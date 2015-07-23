package com.gcsw.workbench.console.backend.dao;

import com.gcsw.workbench.console.domain.annotation.Column;
import com.gcsw.workbench.console.domain.annotation.Join;
import com.gcsw.workbench.console.domain.annotation.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by Administrator on 2015/7/21.
 */
public class BasicDao<T> {

    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;
    protected List<T> queryList(Class<T> clazz, String sql, Map<String, ?> params) throws DaoAccessException {
        Map<String, Method> methods = new HashMap<String, Method>();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for(Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                if (column == null)
                    continue;
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method setter = pd.getWriteMethod();
                methods.put(column.name(), setter);
            }
        } catch (IntrospectionException e) {
            throw new DaoAccessException("BasicDao.queryForList: can't get the setter!", e);
        }

        List<Map<String, Object>> rowset = this.jdbcTemplate.queryForList(sql, params);
        List<T>  result = new ArrayList<T>();
        try {
            for(Map<String, Object> row : rowset) {
                T t = clazz.newInstance();
                for(String key : methods.keySet()) {
                    Object value = row.get(key);
                    if (value == null)
                        continue;
                    methods.get(key).invoke(t, value);
                }
                result.add(t);
            }
        } catch (InstantiationException e) {
            throw new DaoAccessException("BasicDao.queryForList: can't init the clazz!", e);
        } catch (IllegalAccessException e) {
            throw new DaoAccessException("BasicDao.queryForList: reflect error!", e);
        } catch (InvocationTargetException e) {
            throw new DaoAccessException("BasicDao.queryForList: setter invoke error!", e);
        }
        return result;
    }

    protected T queryObject(Class<T> clazz, String sql, Map<String, ?> params) throws DaoAccessException {
        SqlRowSet row = this.jdbcTemplate.queryForRowSet(sql, params);
        Field[] fields = clazz.getDeclaredFields();
        try {
            T t = clazz.newInstance();
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                if (column == null)
                    continue;
                Object fieldValue = row.getObject(column.name());
                if (fieldValue == null)
                    continue;

                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method setter = pd.getWriteMethod();
                setter.invoke(t, fieldValue);
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

    protected void update(T obj) throws DaoAccessException {
        StringBuffer sb = new StringBuffer();
        Table table = obj.getClass().getAnnotation(Table.class);
        sb.append("UPDATE ").append(table.name()).append(" SET ");
        Field[] fields = obj.getClass().getDeclaredFields();
        Field keyField = null;
        String keyColumnName = null;
        Map<String, Object> params = new HashMap<String, Object>();
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if (column == null)
                continue;
            if (column.isPrimaryKey()) {
                keyField = field;
                keyColumnName = column.name();
                continue;
            }
            sb.append(column.name()).append("=:").append(field.getName()).append(", ");
            try {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), obj.getClass());
                params.put(field.getName(), pd.getReadMethod().invoke(obj, null));
            } catch (IntrospectionException e) {
                throw new DaoAccessException("BasicDao.update: get getter error!", e);
            } catch (InvocationTargetException e) {
                throw new DaoAccessException("BasicDao.update: reflect invoke method error!", e);
            } catch (IllegalAccessException e) {
                throw new DaoAccessException("BasicDao.update: reflect error!", e);
            }
        }
        if (keyField == null) {
            throw new DaoAccessException("Update Entity Without Primary Key!");
        }
        sb.deleteCharAt(sb.lastIndexOf(",")).append(" WHERE ").append(keyColumnName).append("=:").append(keyField.getName());
        this.jdbcTemplate.update(sb.toString(), params);
    }

    protected void insert(T obj) throws DaoAccessException {
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        StringBuffer sb3 = new StringBuffer();
        Table table = obj.getClass().getAnnotation(Table.class);
        sb.append("INSERT INTO ").append(table.name()).append(" ( ");
        sb2.append(" VALUES (");
        sb3.append(" ON DUPLICATE KEY UPDATE ");
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, Object> params = new HashMap<String, Object>();
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if (column == null)
                continue;
            sb.append(column.name()).append(", ");
            sb2.append(":").append(field.getName()).append(", ");
            sb3.append(column.name()).append("=:").append(field.getName()).append(", ");
            try {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), obj.getClass());
                params.put(field.getName(), pd.getReadMethod().invoke(obj, null));
            } catch (IntrospectionException e) {
                throw new DaoAccessException("BasicDao.update: get getter error!", e);
            } catch (InvocationTargetException e) {
                throw new DaoAccessException("BasicDao.update: reflect invoke method error!", e);
            } catch (IllegalAccessException e) {
                throw new DaoAccessException("BasicDao.update: reflect error!", e);
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(",")).append(" ) ");
        sb2.deleteCharAt(sb2.lastIndexOf(",")).append(" ) ").toString();
        sb3.deleteCharAt(sb3.lastIndexOf(","));
        this.jdbcTemplate.update(sb.toString() + sb2.toString() + sb3.toString(), params);
    }

}
