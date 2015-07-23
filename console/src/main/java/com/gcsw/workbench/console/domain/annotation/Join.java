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
public @interface Join {
    public enum JoinType { One2One, One2Many, Many2One, Many2Many};

    public String foreignKey();
    public JoinType joinType() default JoinType.One2One;
    public String joinKey();
    public String joinTable();
}
