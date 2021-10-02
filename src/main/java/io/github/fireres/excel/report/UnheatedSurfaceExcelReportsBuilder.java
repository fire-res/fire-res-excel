package io.github.fireres.excel.report;

import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.core.properties.GenerationProperties;
import io.github.fireres.core.model.Report;
import io.github.fireres.excel.annotation.UnheatedSurface;
import io.github.fireres.excel.chart.UnheatedSurfaceChart;
import io.github.fireres.excel.column.Column;
import io.github.fireres.excel.column.TimeColumn;
import io.github.fireres.excel.column.unheated.surface.UnheatedSurfaceMeanBoundColumn;
import io.github.fireres.excel.column.unheated.surface.UnheatedSurfaceMeanColumn;
import io.github.fireres.excel.column.unheated.surface.UnheatedSurfaceThermocoupleBoundColumn;
import io.github.fireres.excel.column.unheated.surface.UnheatedSurfaceThermocoupleColumn;
import io.github.fireres.unheated.surface.model.Group;
import io.github.fireres.unheated.surface.report.UnheatedSurfaceReport;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@UnheatedSurface
@RequiredArgsConstructor
public class UnheatedSurfaceExcelReportsBuilder implements ExcelReportsBuilder {

    private final GenerationProperties generationProperties;

    @Override
    public List<ExcelReport> build(GeneralProperties generalProperties, List<Report> reports) {

        return reports.stream()
                .flatMap(report -> {
                    val unheatedSurfaceReport = (UnheatedSurfaceReport) report;

                    return List.of(
                            createReportForGroup(1, unheatedSurfaceReport.getFirstGroup(), 0),

                            createReportForGroup(2, unheatedSurfaceReport.getSecondGroup(),
                                    unheatedSurfaceReport.getFirstGroup().getThermocoupleTemperatures().size()),

                            createReportForGroup(3, unheatedSurfaceReport.getThirdGroup(),
                                    unheatedSurfaceReport.getFirstGroup().getThermocoupleTemperatures().size() +
                                            unheatedSurfaceReport.getSecondGroup().getThermocoupleTemperatures().size())
                    ).stream();
                })
                .collect(Collectors.toList());
    }

    private ExcelReport createReportForGroup(Integer groupIndex, Group group,
                                             Integer thermocoupleIndexShift) {
        val time = generationProperties.getGeneral().getTime();
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
}
