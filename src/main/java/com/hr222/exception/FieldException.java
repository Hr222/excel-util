package com.hr222.exception;

/**
 * @author: hr222
 * @create: 2019-10-07 22:16
 * @description: 用于描述新建Excel文件IO对象时候文件格式错误
 **/
public class FieldException extends RuntimeException {

    static final long serialVersionUID = -4026425835878442617L;

    public FieldException() {
        super("该使用该注解属性错误,请核对使用该注解属性");
    }

}
