package com.hr222.exception;

/**
 * @author: hr222
 * @create: 2019-10-07 22:16
 * @description: 用于描述新建Excel处理的时候两者长度不一致
 **/
public class LengthException extends RuntimeException {

    static final long serialVersionUID = 5785993916080147261L;

    public LengthException() {
        super("文件单元格式两者长度不等");
    }

    public LengthException(String value) {
        super(value);
    }
}
