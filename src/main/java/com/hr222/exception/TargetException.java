package com.hr222.exception;

/**
 * @author: hr222
 * @create: 2019-10-15 23:24
 * @description: 用于当excel处理器中target对象为空时使用对象转换读取功能
 **/
public class TargetException extends RuntimeException {
    static final long serialVersionUID = 8546129165626719860L;

    public TargetException() {
        super("未获取目标对象Class属性,请指定Class属性");
    }

}
