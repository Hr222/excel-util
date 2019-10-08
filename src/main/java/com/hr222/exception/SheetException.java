package com.hr222.exception;

/**
 * @author: hr222
 * @create: 2019-10-07 22:16
 * @description: 用于描述Excel文件无工作表的式错误
 **/
public class SheetException extends RuntimeException {

    private static final long serialVersionUID = -8764707562035185262L;

    public SheetException() {
        super("文件内无工作表数据");
    }

}
