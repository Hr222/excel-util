package com.hr222.annutations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface IsBoolean {

    /**
     * 当属性为Boolean的时候,需要告知当为true的时候值是什么.
     * 例如当属性为性别时,需要告诉当为true的时候是女或男
     */
    String value();
}
