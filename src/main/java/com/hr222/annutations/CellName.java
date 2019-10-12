package com.hr222.annutations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CellName {

    /**
     * 单元名称
     * @return
     */
    String value();

    /**
     * 是否允许为空
     * @return
     */
    boolean allowNull() default true;


}
