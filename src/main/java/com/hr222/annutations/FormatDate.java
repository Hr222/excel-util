package com.hr222.annutations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface FormatDate {

    String format() default "yyyy-MM-dd HH:mm:ss";

}
