package com.hr222.handler;

import com.hr222.enumeration.SupportEnum;
import com.hr222.exception.*;
import com.hr222.usermodel.ExcelModel;
import com.hr222.util.AnnotationsUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author: hr222
 * @create: 2019-10-06 23:56
 * @description: 处理Excel文件的数据
 **/
public class ExcelInputHandler {

    private ExcelModel excelModel = new ExcelModel();

    private Class target;

    /**
     * 设置处理器需要处理的对象信息
     * 注:若是两者
     *
     * @param cellNames excel文件中的单元格名称
     * @param cellField excel文件中单元格名称对应POJO中的属性
     * @throws LengthException
     */
    ExcelInputHandler(List<String> cellNames, List<String> cellField) throws LengthException {
        if (cellNames.isEmpty() || cellField.isEmpty()) {
            throw new LengthException();
        }
        if (cellNames.size() != cellField.size()) {
            throw new LengthException();
        }
        excelModel.setCellNames(cellNames);
        excelModel.setCellFields(cellField);
    }

    /**
     * 设置处理器
     *
     * @param targetClass 目标对象类
     */
    ExcelInputHandler(Class targetClass) {
        setTarget(targetClass);
    }

    /**
     * 初始化工作簿
     *
     * @param excelFileInputStream excel文件流
     * @param excelType            0:代表XLS(07年前的EXCEL文件格式) 1:代表XLSX(07后的EXCEL文件格式)
     * @return 初始工作簿
     * @throws IOException
     */
    private Workbook initWorkBook(InputStream excelFileInputStream, int excelType) throws IOException {
        Workbook wb = null;
        if (excelType == 0) {
            wb = new HSSFWorkbook(excelFileInputStream);
        } else if (excelType == 1) {
            wb = new XSSFWorkbook(excelFileInputStream);
        }
        return wb;
    }

    /**
     * 判断是否为标准的excel文件头
     * 注1:若是传入错误文件格式类型(excelType)则会抛出FileTypeException
     * 注2:若是文件内无工作表则不进行判断抛出SheetException
     *
     * @param excelFileInputStream excel文件IO流对象
     * @param excelType            0:代表XLS(07年前的EXCEL文件格式) 1:代表XLSX(07后的EXCEL文件格式)
     * @param isReadAllSheet       是否需要读取excel文件中所有工作表
     * @return true是标准反之亦然
     * @throws FileTypeException
     * @throws SheetException
     */
    public boolean judgeExcelHeaderStandard(InputStream excelFileInputStream, int excelType, boolean isReadAllSheet) throws
            IOException, FileTypeException, SheetException {
        Workbook wb = initWorkBook(excelFileInputStream, excelType);
        if (wb == null) {
            throw new FileTypeException();
        } else {
            //获取工作表的长度
            int sheetLength = 1;
            //判断是否需要全部读取
            if (isReadAllSheet) {
                sheetLength = wb.getNumberOfSheets();
            }
            List<String> rowNames = excelModel.getCellNames();
            for (int sheetIndex = 0; sheetIndex < sheetLength; sheetIndex++) {
                Sheet sheet = wb.getSheetAt(sheetIndex);
                if (sheet != null) {
                    //获取当前第一行的数据
                    Row row = sheet.getRow(0);
                    int rowLength = row.getLastCellNum();
                    //长度和当前的不符直接返回
                    if (rowLength != rowNames.size()) {
                        return false;
                    } else {
                        //获取单元格中的数据信息和当前传入的信息是否一直
                        for (int cellIndex = 0; cellIndex < rowLength; cellIndex++) {
                            Cell cell = row.getCell(cellIndex);
                            String cellName = cell.getStringCellValue();
                            String name = rowNames.get(cellIndex);
                            if (!name.equals(cellName)) {
                                return false;
                            }
                        }
                    }
                } else {
                    throw new SheetException();
                }
            }
            return true;
        }
    }

    /**
     * 读取特定的工作表判断是否为标准的excel文件头
     * 注1:若是传入错误文件格式类型(excelType)则会抛出FileTypeException
     * 注2:若是文件内无工作表则不进行判断抛出SheetException
     *
     * @param excelFileInputStream excel文件对象IO流
     * @param excelType            0:代表XLS(07年前的EXCEL文件格式) 1:代表XLSX(07后的EXCEL文件格式)
     * @param sheetName            工作表名称
     * @return true是标准反之亦然
     * @throws IOException
     * @throws FileTypeException
     * @throws SheetException
     */
    public boolean judgeExcelHeaderStandard(InputStream excelFileInputStream, int excelType, String sheetName) throws
            IOException, FileTypeException, SheetException {
        Workbook wb = initWorkBook(excelFileInputStream, excelType);
        if (wb == null) {
            throw new FileTypeException();
        } else {
            Sheet sheet = wb.getSheet(sheetName);
            if (sheet == null) {
                throw new NullPointerException("文件内无该指定工作表");
            } else {
                List<String> rowNames = excelModel.getCellNames();
                //获取当前第一行的数据
                Row row = sheet.getRow(0);
                int rowLength = row.getLastCellNum();
                //长度和当前的不符直接返回
                if (rowLength != rowNames.size()) {
                    return false;
                } else {
                    //获取单元格中的数据信息和当前传入的信息是否一直
                    for (int cellIndex = 0; cellIndex < rowLength; cellIndex++) {
                        Cell cell = row.getCell(cellIndex);
                        String cellName = cell.getStringCellValue();
                        String name = rowNames.get(cellIndex);
                        if (!name.equals(cellName)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    /**
     * 通过指定工作表的名称给用户使用的获取excel数据.并返回List<Map<String,Object>>集合对象
     * 注1:推荐使用readExcelData.该方法获取的是原始的excel数据
     * 注2:若是单元格存在空单元格和空皆,则返回的是""/null
     *
     * @param excelFileInputStream excel文件集合
     * @param excelType            0:代表XLS(07年前的EXCEL文件格式) 1:代表XLSX(07后的EXCEL文件格式)
     * @return 转换好的对象
     */
    public List<Map<String, Object>> readExcelDataByMap(InputStream excelFileInputStream, int excelType, String sheetName) throws
            IOException, FileStandardException {
        boolean isStandard = judgeExcelHeaderStandard(excelFileInputStream, excelType, sheetName);
        if (!isStandard) {
            throw new FileStandardException();
        }
        List<Map<String, Object>> data = new ArrayList<>();
        //因为前面判定了所以这里不做任何判定
        Workbook wb = excelType == 0 ? new HSSFWorkbook(excelFileInputStream) : new XSSFWorkbook(excelFileInputStream);
        Sheet sheet = wb.getSheet(sheetName);
        List<String> rowField = excelModel.getCellFields();
        for (int rowIndex = 1; rowIndex < sheet.getLastRowNum(); rowIndex++) {
            Map<String, Object> value = new HashMap<>(rowField.size());
            Row row = sheet.getRow(rowIndex);
            for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
                Cell cell = row.getCell(cellIndex);
                String k = rowField.get(cellIndex);
                Object v;
                switch (cell.getCellType()) {
                    //数字类型
                    case NUMERIC:
                        v = cell.getNumericCellValue();
                        value.put(k, v);
                        break;
                    //字符串
                    case STRING:
                        v = cell.getStringCellValue();
                        value.put(k, v);
                        break;
                    //公式
                    case FORMULA:
                        //公式对象比较特殊这里需要注意这里我们需要获取计算后的公式值就好了不需要有多余设置公式值.
                        if (wb instanceof HSSFWorkbook) {
                            HSSFCell hssfCell = (HSSFCell) cell;
                            v = hssfCell.getCellFormula();
                        } else {
                            XSSFCell xssfCell = (XSSFCell) cell;
                            v = cell.getCellFormula();
                        }
                        value.put(k, v);
                        break;
                    //布莱恩
                    case BOOLEAN:
                        v = cell.getBooleanCellValue();
                        value.put(k, v);
                        break;
                    //空
                    case BLANK:
                        v = "";
                        value.put(k, v);
                        break;
                    //异常属性
                    case _NONE:
                    case ERROR:
                        value.put(k, null);
                        break;
                }
            }
            data.add(value);
        }
        return data;
    }

    /**
     * @param excelFileInputStream
     * @param excelType
     * @param sheetName
     * @return
     */
    public <T> List<Object> readExcelData(InputStream excelFileInputStream, Integer excelType, String sheetName) throws IOException,
            IllegalAccessException, InstantiationException {
        if (target == null) {
            throw new TargetException();
        }
        boolean isStandard = judgeExcelHeaderStandard(excelFileInputStream, excelType, sheetName);
        if (!isStandard) {
            throw new FileStandardException();
        }
        List<Object> data = new ArrayList<>();
        //因为前面判定了所以这里不做任何判定
        Workbook wb = excelType == 0 ? new HSSFWorkbook(excelFileInputStream) : new XSSFWorkbook(excelFileInputStream);
        Sheet sheet = wb.getSheet(sheetName);
        List<String> rowField = excelModel.getCellFields();
        List<SupportEnum> enums = excelModel.getFieldParam();
        for (int rowIndex = 1; rowIndex < sheet.getLastRowNum(); rowIndex++) {
            T clazz = (T) target.newInstance();
            Row row = sheet.getRow(rowIndex);
            for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
                //获取单元格信息,属性名称
                Cell cell = row.getCell(cellIndex);
                String field = rowField.get(cellIndex);

            }
        }
        return data;
    }

    /**
     * 指定转换对象属性
     *
     * @param target 目标对象属性
     */
    public void setTarget(Class target) {
        this.target = target;
        excelModel = AnnotationsUtil.getExcelClassField(target);
    }

    /**
     * 获取目标转换对象
     *
     * @return target对象
     */
    public Class getTarget() {
        return target;
    }
}
