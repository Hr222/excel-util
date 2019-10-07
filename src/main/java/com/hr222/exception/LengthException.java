package com.hr222.exception;

/**
 * @author: hr222
 * @create: 2019-10-07 22:16
 * @description: 用于描述新建Excel处理的时候两者长度不一致
 **/
public class LengthException extends RuntimeException {

    private static final long serialVersionUID = -8764707562035185262L;

    public LengthException() {
        super("文件单元格式两者长度不等");
    }

    public LengthException(String value) {
        super(value);
    }
}
