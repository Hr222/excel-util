package com.hr222.exception;

/**
 * @author: hr222
 * @create: 2019-10-07 22:16
 * @description: 用于描述新建Excel文件IO对象时候文件格式错误
 **/
public class FileTypeException extends RuntimeException {

    private static final long serialVersionUID = -8764707562035185262L;

    public FileTypeException() {
        super("文件单元格式错误");
    }

}
