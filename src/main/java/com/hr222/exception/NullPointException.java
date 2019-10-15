package com.hr222.exception;

/**
 * @author: hr222
 * @create: 2019-10-15 23:18
 * @description: 描述当excel文件中出现于目标设定中的允许为空冲突的异常
 **/
public class NullPointException extends NullPointerException {
    static final long serialVersionUID = -1369652217278691326L;

    public NullPointException() {
        super("excel文件中出现空属性,这和设置类中不允许为空冲突.");
    }

}
