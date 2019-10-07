package com.hr222.handler;

import com.hr222.Test;
import com.hr222.annutations.RowLength;
import com.hr222.exception.LengthException;
import com.hr222.util.AnnutationsUtil;

import java.util.List;
import java.util.Map;

/**
 * @author: hr222
 * @create: 2019-10-06 23:56
 * @description: 处理Excel文件的数据
 **/
public class ExcelInputHandler {

    private Excel excel;

    private Class target;

    /**
     * 设置处理器需要处理的对象信息
     *
     * @param cellNames excel文件中的单元格名称
     * @param cellField excel文件中单元格名称对应POJO中的属性
     */
    ExcelInputHandler(List<String> cellNames, List<String> cellField) {
        if (cellNames.isEmpty() || cellField.isEmpty()) {
            throw new LengthException();
        }
        if (cellNames.size() != cellField.size()) {
            throw new LengthException();
        }
        excel = new Excel(cellNames.size(), cellNames, cellField);
    }

    ExcelInputHandler(Class targetClass) {
        target = targetClass;
        Map<String, Object> data = AnnutationsUtil.getExcel(targetClass);
        excel = new Excel((Integer) data.get("rowLength"), (List<String>) data.get("cellNames"),
                (List<String>) data.get("cellFields"));
    }


    class Excel {

        /**
         * 工作表数量
         */
        private Integer sheetLength;

        /**
         * 获取单元格长度
         */
        private Integer rowLength;

        /**
         * 首栏的名称
         */
        private List<String> rowNames;

        /**
         * 首栏名称对应的属性
         */
        private List<String> rowField;

        Excel() {

        }

        Excel(Integer rowLength, List<String> rowNames, List<String> rowField) {
            this.rowLength = rowLength;
            this.rowNames = rowNames;
            this.rowField = rowField;
        }

        public Integer getSheetLength() {
            return sheetLength;
        }

        public void setSheetLength(Integer sheetLength) {
            this.sheetLength = sheetLength;
        }

        public Integer getRowLength() {
            return rowLength;
        }

        public void setRowLength(Integer rowLength) {
            this.rowLength = rowLength;
        }

        public List<String> getRowNames() {
            return rowNames;
        }

        public void setRowNames(List<String> rowNames) {
            this.rowNames = rowNames;
        }

        public List<String> getRowField() {
            return rowField;
        }

        public void setRowField(List<String> rowField) {
            this.rowField = rowField;
        }

    }

}
