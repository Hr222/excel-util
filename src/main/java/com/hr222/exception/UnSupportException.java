package com.hr222.exception;

/**
 * @author: hr222
 * @create: 2019-10-07 22:16
 * @description: 用于描述新建Excel文件IO对象时候文件格式错误
 **/
public class UnSupportException extends RuntimeException {

    static final long serialVersionUID = 3874955319708038376L;

    public UnSupportException() {
        super("该参数不是支持的类型,支持类型为基本类型和Date类型");
    }

}
