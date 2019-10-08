package com.hr222.util;

import com.hr222.annutations.CellName;
import com.hr222.exception.LengthException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hr222
 * @create: 2019-10-07 22:37
 * @description: POJO注解处理器
 **/
public class AnnutationsUtil {

    public static Map<String, Object> getExcel(Class clazz) {
        List<String> cellNames = new ArrayList<>();
        List<String> cellFields = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            CellName cellName = field.getAnnotation(CellName.class);
            if (cellName != null) {
                cellNames.add(cellName.value());
                cellFields.add(field.getName());
            }
        }
        Map<String, Object> data = new HashMap<>(3);
        data.put("cellNames", cellNames);
        data.put("cellFields", cellFields);
        return data;
    }
}

