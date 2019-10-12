package com.hr222.annutations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CellOrder {

    /**
     * 指令单元格属性的顺序集.
     * 注:必须类中有的属性
     */
    String[] value();
}
