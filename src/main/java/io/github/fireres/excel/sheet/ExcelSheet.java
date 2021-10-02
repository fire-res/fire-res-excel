package io.github.fireres.excel.sheet;

import io.github.fireres.excel.chart.ExcelChart;
import io.github.fireres.excel.column.Column;
import io.github.fireres.excel.report.ExcelReport;
import io.github.fireres.excel.style.data.DataCellStyles;
import io.github.fireres.excel.style.data.HeaderCellStyles;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class ExcelSheet {

    private static final Integer REPORT_INTERVAL = 3;

    private final Integer time;
    private final List<ExcelReport> reports;
    private final String sheetName;

    public void create(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet(sheetName);

        for (int i = 0; i < reports.size(); i++) {
            ExcelReport excelReport = reports.get(i);
            Integer position = i * (time + REPORT_INTERVAL + 1);

            List<Column> columns = excelReport.getData();

            generateHeaders(sheet, columns, new HeaderCellStyles(workbook), position);
            generateData(sheet, columns, new DataCellStyles(workbook), position);
            generateChart(sheet, excelReport, position);
        }
    }

    protected void generateHeaders(XSSFSheet sheet, List<Column> columns,
                                   HeaderCellStyles cellStyles, Integer position) {

        XSSFRow headerRow = sheet.createRow(position);
        headerRow.setHeightInPoints(40);

        for (int i = 0; i < columns.size(); i++) {
            XSSFCell headerCell = headerRow.createCell(i);

            headerCell.setCellValue(columns.get(i).getHeader());
            headerCell.setCellStyle(i == columns.size() - 1
                    ? cellStyles.getLastCellStyle()
                    : cellStyles.getCommonCellStyle());
        }
    }

    protected void generateData(XSSFSheet sheet, List<Column> columns,
                                DataCellStyles cellStyles, Integer position) {

        IntStream.range(0, time).forEach(t -> {
            XSSFRow row = sheet.createRow(position + t + 1);

            IntStream.range(0, columns.size()).forEach(i -> {
                Column column = columns.get(i);
                XSSFCell cell = row.createCell(i);

                cell.setCellValue(column.getValues().get(t).doubleValue());
                cell.setCellStyle(column.isHighlighted()
                        ? cellStyles.getHighlightedCellStyle()
                        : cellStyles.getCommonCellStyle());
            });
        });
    }

    protected void generateChart(XSSFSheet sheet, ExcelReport report, Integer position) {
        ExcelChart chart = report.getChart();
        chart.plot(sheet, position);
    }
}
