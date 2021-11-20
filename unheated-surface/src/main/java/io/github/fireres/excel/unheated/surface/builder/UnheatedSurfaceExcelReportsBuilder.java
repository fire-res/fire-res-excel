package io.github.fireres.excel.unheated.surface.builder;

import io.github.fireres.core.model.Report;
import io.github.fireres.core.model.ReportType;
import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.excel.core.builder.ExcelReportsBuilder;
import io.github.fireres.excel.core.model.Column;
import io.github.fireres.excel.core.model.ExcelReport;
import io.github.fireres.excel.core.model.TimeColumn;
import io.github.fireres.excel.unheated.surface.chart.UnheatedSurfaceChart;
import io.github.fireres.excel.unheated.surface.column.UnheatedSurfaceMeanBoundColumn;
import io.github.fireres.excel.unheated.surface.column.UnheatedSurfaceMeanColumn;
import io.github.fireres.excel.unheated.surface.column.UnheatedSurfaceThermocoupleBoundColumn;
import io.github.fireres.excel.unheated.surface.column.UnheatedSurfaceThermocoupleColumn;
import io.github.fireres.unheated.surface.annotation.UnheatedSurface;
import io.github.fireres.unheated.surface.report.UnheatedSurfaceReport;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static io.github.fireres.unheated.surface.report.UnheatedSurfaceReportType.UNHEATED_SURFACE;

@Component
@UnheatedSurface
@RequiredArgsConstructor
public class UnheatedSurfaceExcelReportsBuilder implements ExcelReportsBuilder {

    private final GeneralProperties generalProperties;

    @Override
    public List<ExcelReport> build(List<Report> reports) {
        val excelReports = new ArrayList<ExcelReport>();
        val groupIndex = new AtomicInteger(0);
        val thermocouplesIndexShift = new AtomicInteger(0);

        reports.forEach(report -> {
            val unheatedSurfaceReport = (UnheatedSurfaceReport) report;

            excelReports.add(createReportForGroup(groupIndex.get(), unheatedSurfaceReport, thermocouplesIndexShift.get()));
            groupIndex.incrementAndGet();
            thermocouplesIndexShift.addAndGet(unheatedSurfaceReport.getThermocoupleTemperatures().size());
        });

        return excelReports;
    }

    private ExcelReport createReportForGroup(Integer groupIndex,
                                             UnheatedSurfaceReport group,
                                             Integer thermocoupleIndexShift) {

        val time = generalProperties.getTime();
        val columns = new ArrayList<Column>();

        columns.add(new TimeColumn(time));

        for (int i = 0; i < group.getThermocoupleTemperatures().size(); i++) {
            val thermocouple = group.getThermocoupleTemperatures().get(i);
            columns.add(new UnheatedSurfaceThermocoupleColumn(thermocoupleIndexShift + i, thermocouple));
        }

        columns.add(new UnheatedSurfaceMeanColumn(group.getMeanTemperature()));

        if (group.getMaxAllowedMeanTemperature() != null) {
            columns.add(new UnheatedSurfaceMeanBoundColumn(group.getMaxAllowedMeanTemperature()));
        }

        columns.add(new UnheatedSurfaceThermocoupleBoundColumn(group.getMaxAllowedThermocoupleTemperature()));

        return ExcelReport.builder()
                .data(columns)
                .chart(new UnheatedSurfaceChart(time, columns, groupIndex))
                .build();
    }

    @Override
    public ReportType supportedReportType() {
        return UNHEATED_SURFACE;
    }

}
