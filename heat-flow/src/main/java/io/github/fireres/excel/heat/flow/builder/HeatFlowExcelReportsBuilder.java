package io.github.fireres.excel.heat.flow.builder;

import io.github.fireres.core.model.Report;
import io.github.fireres.core.model.ReportType;
import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.excel.core.builder.ExcelReportsBuilder;
import io.github.fireres.excel.core.model.Column;
import io.github.fireres.excel.core.model.ExcelReport;
import io.github.fireres.excel.core.model.TimeColumn;
import io.github.fireres.excel.heat.flow.chart.HeatFlowChart;
import io.github.fireres.excel.heat.flow.column.HeatFlowBoundColumn;
import io.github.fireres.excel.heat.flow.column.HeatFlowMeanTemperatureColumn;
import io.github.fireres.excel.heat.flow.column.HeatFlowThermocoupleColumn;
import io.github.fireres.heatflow.annotation.HeatFlow;
import io.github.fireres.heatflow.report.HeatFlowReport;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.fireres.heatflow.report.HeatFlowReportType.HEAT_FLOW;

@Component
@HeatFlow
@RequiredArgsConstructor
public class HeatFlowExcelReportsBuilder implements ExcelReportsBuilder {

    private final GeneralProperties generalProperties;

    @Override
    public List<ExcelReport> build(List<Report> reports) {
        val time = generalProperties.getTime();

        return reports.stream()
                .map(report -> {
                    val data = createData(((HeatFlowReport) report));

                    return ExcelReport.builder()
                            .data(data)
                            .chart(new HeatFlowChart(time, data))
                            .build();
                })
                .collect(Collectors.toList());
    }

    protected List<Column> createData(HeatFlowReport report) {
        val time = generalProperties.getTime();
        val columns = new ArrayList<Column>();

        columns.add(new TimeColumn(time));
        columns.add(new HeatFlowBoundColumn(report.getBound()));
        columns.add(new HeatFlowMeanTemperatureColumn(report.getMeanTemperature()));

        val sensorTemperatures = report.getSensorTemperatures();
        for (int t = 0; t < sensorTemperatures.size(); t++) {
            val sensorTemperature = sensorTemperatures.get(t);

            columns.add(new HeatFlowThermocoupleColumn(t, sensorTemperature));
        }

        return columns;
    }

    @Override
    public ReportType supportedReportType() {
        return HEAT_FLOW;
    }

}