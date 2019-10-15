package com.hr222.enumeration;

import java.util.Date;

/**
 * @author: hr222
 * @create: 2019-10-15 22:23
 * @description: excel文件支持的类型
 **/
public enum SupportEnum {

    BOOLEAN(Boolean.class),
    BYTE(Byte.class),
    INTEGER(Integer.class),
    FLOAT(Float.class),
    DOUBLE(Double.class),
    CHAR(Character.class),
    STRING(String.class),
    DATE(Date.class);

    private Class supportClass;

    SupportEnum(Class supportClass){
        this.supportClass = supportClass;
    }

    public Class getSupportClass() {
        return supportClass;
    }

    /**
     * 获取枚举中有的枚举信息
     * @param clazz 支持的对象类
     * @return 返回枚举信息
     */
    public static SupportEnum getSupportEnum(Class clazz){
        SupportEnum[] enums = SupportEnum.values();
        for (SupportEnum c : enums){
            if (c.getSupportClass().equals(clazz)){
                return c;
            }
        }
        return null;
    }

}
