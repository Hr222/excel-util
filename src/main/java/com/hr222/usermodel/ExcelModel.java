package com.hr222.usermodel;

import com.hr222.enumeration.SupportEnum;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: hr222
 * @create: 2019-10-14 22:43
 * @description: 描述使用注解的对象类属性
 **/
public class ExcelModel {

    /**
     * 对应EXCEL文件中的单元格名称
     */
    private List<String> cellNames;

    /**
     * 对应EXCEL文件单元格名称的在该类的属性名称
     */
    private List<String> cellFields;

    /**
     * 该属性是否允许为空
     */
    private Set<String> allowNullFields;

    /**
     * boolean属性的field中当为true的判定值
     */
    private Map<String, String> booleanFields;

    /**
     * 日期属性的field的需要格式化的属性值
     */
    private Map<String, String> dateFormatFields;

    /**
     * 各个变量的属性
     */
    private List<SupportEnum> fieldParam;

    public ExcelModel(List<String> cellNames, List<String> cellFields, Set<String> allowNullFields,
                      Map<String, String> booleanFields, Map<String, String> dateFormatFields,List<SupportEnum> fieldParam) {
        this.cellNames = cellNames;
        this.cellFields = cellFields;
        this.allowNullFields = allowNullFields;
        this.booleanFields = booleanFields;
        this.dateFormatFields = dateFormatFields;
        this.fieldParam = fieldParam;
    }

    public ExcelModel() {
    }

    public List<String> getCellNames() {
        return cellNames;
    }

    public void setCellNames(List<String> cellNames) {
        this.cellNames = cellNames;
    }

    public List<String> getCellFields() {
        return cellFields;
    }

    public void setCellFields(List<String> cellFields) {
        this.cellFields = cellFields;
    }

    public Set<String> getAllowNullFields() {
        return allowNullFields;
    }

    public void setAllowNullFields(Set<String> allowNullFields) {
        this.allowNullFields = allowNullFields;
    }

    public Map<String, String> getBooleanFields() {
        return booleanFields;
    }

    public void setBooleanFields(Map<String, String> booleanFields) {
        this.booleanFields = booleanFields;
    }

    public Map<String, String> getDateFormatFields() {
        return dateFormatFields;
    }

    public void setDateFormatFields(Map<String, String> dateFormatFields) {
        this.dateFormatFields = dateFormatFields;
    }

    public List<SupportEnum> getFieldParam() {
        return fieldParam;
    }

    public void setFieldParam(List<SupportEnum> fieldParam) {
        this.fieldParam = fieldParam;
    }
}
