package io.github.fireres.excel.chart;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface ExcelChart {

    void plot(XSSFSheet sheet, Integer position);

}
