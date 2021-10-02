package io.github.fireres.excel.report;

import io.github.fireres.excel.chart.ExcelChart;
import io.github.fireres.excel.column.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExcelReport {

    private List<Column> data;

    private ExcelChart chart;

}
