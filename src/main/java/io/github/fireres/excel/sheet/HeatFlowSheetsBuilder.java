package io.github.fireres.excel.sheet;

import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.core.model.Sample;
import io.github.fireres.excel.report.ExcelReportsBuilder;
import io.github.fireres.heatflow.annotation.HeatFlow;
import io.github.fireres.heatflow.report.HeatFlowReport;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@HeatFlow
public class HeatFlowSheetsBuilder implements ExcelSheetsBuilder {

    private static final String SHEET_NAME = "Тепловой поток - %s";

    private final ExcelReportsBuilder excelReportsBuilder;
    private final GeneralProperties generalProperties;

    @Autowired
    public HeatFlowSheetsBuilder(@HeatFlow ExcelReportsBuilder excelReportsBuilder,
                                 GeneralProperties generalProperties) {
        this.excelReportsBuilder = excelReportsBuilder;
        this.generalProperties = generalProperties;
    }

    @Override
    public List<ExcelSheet> build(List<Sample> samples) {
        val time = generalProperties.getTime();

        return samples.stream()
                .flatMap(sample -> {
                    val reports = sample.getReports().stream()
                            .filter(report -> report instanceof HeatFlowReport)
                            .collect(Collectors.toList());

                    if (!reports.isEmpty()) {
                        val excelReports = excelReportsBuilder.build(reports);
                        val sheetName = String.format(SHEET_NAME, sample.getSampleProperties().getName());

                        return Stream.of(new ExcelSheet(time, excelReports, sheetName));
                    } else {
                        return Stream.empty();
                    }
                })
                .collect(Collectors.toList());
    }
}