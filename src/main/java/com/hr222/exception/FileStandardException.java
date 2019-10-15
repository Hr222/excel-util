package com.hr222.exception;

/**
 * @author: hr222
 * @create: 2019-10-07 22:16
 * @description: 用于描述新建Excel文件IO对象时候文件格式错误
 **/
public class FileStandardException extends RuntimeException {

    static final long serialVersionUID = 4497975428786798539L;

    public FileStandardException() {
        super("工作表头不合法");
    }

}
