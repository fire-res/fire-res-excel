package io.github.fireres.excel.excess.pressure.builder;

import io.github.fireres.core.model.Report;
import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.excel.core.builder.ExcelReportsBuilder;
import io.github.fireres.excel.core.model.Column;
import io.github.fireres.excel.core.model.ExcelReport;
import io.github.fireres.excel.core.model.TimeColumn;
import io.github.fireres.excel.excess.pressure.chart.ExcessPressureChart;
import io.github.fireres.excel.excess.pressure.column.DeltaColumn;
import io.github.fireres.excel.excess.pressure.column.MaxAllowedPressureColumn;
import io.github.fireres.excel.excess.pressure.column.MinAllowedPressureColumn;
import io.github.fireres.excel.excess.pressure.column.PressureColumn;
import io.github.fireres.excess.pressure.annotation.ExcessPressure;
import io.github.fireres.excess.pressure.report.ExcessPressureReport;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component
@ExcessPressure
@RequiredArgsConstructor
public class ExcessPressureExcelReportsBuilder implements ExcelReportsBuilder {

    private final GeneralProperties generalProperties;

    @Override
    public List<ExcelReport> build(List<Report> reports) {
        val time = generalProperties.getTime();
        val data = createData(reports);
        val chart = new ExcessPressureChart(time, data);

        if (basePressuresAreSame(reports)) {
            chart.setValueAxisTitle(String.format(Locale.US, "%s - %.1f±ΔПа",
                    chart.getValueAxisTitle(),
                    ((ExcessPressureReport) reports.get(0)).getBasePressure()));
        }

        return Collections.singletonList(ExcelReport.builder()
                .data(data)
                .chart(chart)
                .build());
    }

    private boolean basePressuresAreSame(List<Report> reports) {
        val basePressure = ((ExcessPressureReport) reports.get(0)).getBasePressure();

        return reports.stream()
                .allMatch(report -> ((ExcessPressureReport) report).getBasePressure().equals(basePressure));
    }

    private List<Column> createData(List<Report> reports) {
        val time = generalProperties.getTime();
        val columns = new ArrayList<Column>();

        columns.add(new TimeColumn(time));

        for (Report report : reports) {
            val excessPressureReport = (ExcessPressureReport) report;
            val basePressure = excessPressureReport.getBasePressure();
            val sampleName = report.getSample().getSampleProperties().getName();

            columns.add(new MaxAllowedPressureColumn(sampleName, excessPressureReport.getMaxAllowedPressure()));
            columns.add(new MinAllowedPressureColumn(sampleName, excessPressureReport.getMinAllowedPressure()));
            columns.add(new PressureColumn(sampleName, excessPressureReport.getPressure(), basePressure));
            columns.add(new DeltaColumn(sampleName, excessPressureReport.getPressure()));
        }

        return columns;
    }

}
