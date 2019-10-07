package com.hr222.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: hr222
 * @create: 2019-10-06 11:54
 * @description: 用于将对象类进行转换成Map对象或是转换回去
 **/
public class POJOUtil {

    /**
     * 将class对象进行转换成为Map对象
     * 注:若对象中有含有非声明的基础字段或是接口的实例则会引发IllegalAccessException异常
     *
     * @param clazz class对象
     * @return 转换好的Map对象
     * @throws IllegalAccessException
     */
    public static Map<String, Object> parseToMap(Class clazz) throws IllegalAccessException {
        Map<String, Object> data = new HashMap<>(16);
        if (clazz == null) {
            return data;
        }
        //获取该类的属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //将该属性的private中的私有访问设置成可以访问
            field.setAccessible(true);
            //获取属性名称
            String name = field.getName();
            Object value = field.get(clazz);
            //将该类的属性填充到map对象中去
            data.put(name, value);
        }
        return data;
    }

    /**
     * 对Map对象进行转换成类对象,注意前提pojo对象必须要有对应的SET方法
     *
     * @param data  包含对象属性和值的Map对象
     * @param clazz 需要赋值的对象
     * @return 设置好属性的对象
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object parseToObject(Map<String, Object> data, Class clazz) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, InstantiationException {
        if (clazz == null) {
            return null;
        }
        Set<Map.Entry<String, Object>> keySet = data.entrySet();
        //构建一个该类的实例
        Object object = clazz.newInstance();
        for (Map.Entry<String, Object> key : keySet) {
            String K = key.getKey();
            Object V = key.getValue();
            //拼装获取该类的set方法.简单粗暴的赋值,这里使用了一个取巧的办法,因为ASCII码英文字母大小写问题
            String setMethod = "set" + (char) (K.charAt(0) - 32) + K.substring(1);
            //获取该类的set方法同时对该类进行赋值
            Method method = object.getClass().getMethod(setMethod, V.getClass());
            //判断该类是否有该方法,有则执行
            if (method != null) {
                method.invoke(object, V);
            }
        }
        return object;
    }

}
