package com.gcsw.workbench.console.domain.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2015/7/23.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    public enum DataType { String, Int, DateTime, Number, Bool };

    public String name();
    public DataType type() default DataType.String;
    public boolean isPrimaryKey() default false;
}
