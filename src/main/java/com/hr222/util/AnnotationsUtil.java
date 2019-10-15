package com.hr222.util;

import com.hr222.annutations.CellName;
import com.hr222.annutations.FormatDate;
import com.hr222.annutations.IsBoolean;
import com.hr222.enumeration.SupportEnum;
import com.hr222.exception.FieldException;
import com.hr222.exception.UnSupportException;
import com.hr222.usermodel.ExcelModel;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author: hr222
 * @date: 2019-10-07 22:37
 * @description: POJO注解处理器
 **/
public class AnnotationsUtil {

    /**
     * 获取EXCEL类中的注解属性
     *
     * @param clazz 试用注解的class对象
     * @return 注解信息
     */
    public static ExcelModel getExcelClassField(Class clazz) {
        //对应EXCEL文件中的单元格名称
        List<String> cellNames = new ArrayList<>();
        //对应EXCEL文件单元格名称的在该类的属性名称
        List<String> cellFields = new ArrayList<>();
        //该属性是否允许为空
        Set<String> allowNull = new HashSet<>();
        //Boolean属性的true判定值
        Map<String, String> booleanFields = new HashMap<>(3);
        //Date属性时所弄的格式化值
        Map<String, String> dateFormatFields = new HashMap<>(3);
        //属性值
        List<SupportEnum> ennums = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            SupportEnum supportEnum = SupportEnum.getSupportEnum(field.getType());
            if (supportEnum == null) {
                throw new UnSupportException();
            }
            IsBoolean isBoolean = field.getAnnotation(IsBoolean.class);
            CellName cellName = field.getAnnotation(CellName.class);
            FormatDate formatDate = field.getAnnotation(FormatDate.class);
            if (cellName != null) {
                cellNames.add(cellName.value());
                cellFields.add(field.getName());
                if (cellName.allowNull()) {
                    allowNull.add(field.getName());
                }
            }
            //这两个需要判断两个属性,假如是不是该属性又使用了这个直接抛异常
            if (isBoolean != null) {
                if (supportEnum.equals(SupportEnum.BOOLEAN)) {
                    booleanFields.put(field.getName(), isBoolean.value());
                } else {
                    throw new FieldException();
                }
            }
            if (formatDate != null) {
                if (supportEnum.equals(SupportEnum.DATE)) {
                    dateFormatFields.put(field.getName(), isBoolean.value());
                } else {
                    throw new FieldException();
                }
            }
            ennums.add(supportEnum);
        }
        ExcelModel excelModel = new ExcelModel(cellNames, cellFields, allowNull, booleanFields, dateFormatFields, ennums);
        return excelModel;
    }

}

