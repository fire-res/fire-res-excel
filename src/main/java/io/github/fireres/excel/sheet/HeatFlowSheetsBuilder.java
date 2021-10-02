package io.github.fireres.excel.sheet;

import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.core.model.Sample;
import io.github.fireres.excel.annotation.HeatFlow;
import io.github.fireres.excel.report.ExcelReportsBuilder;
import io.github.fireres.heatflow.report.HeatFlowReport;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;

@Component
@HeatFlow
public class HeatFlowSheetsBuilder implements ExcelSheetsBuilder {

    private static final String SHEET_NAME = "Тепловой поток - %s";

    private final ExcelReportsBuilder excelReportsBuilder;

    @Autowired
    public HeatFlowSheetsBuilder(@HeatFlow ExcelReportsBuilder excelReportsBuilder) {
        this.excelReportsBuilder = excelReportsBuilder;
    }

    @Override
    public List<ExcelSheet> build(GeneralProperties generalProperties, List<Sample> samples) {
        val time = generalProperties.getTime();

        return samples.stream()
                .flatMap(sample -> {
                    val report = sample.getReportByClass(HeatFlowReport.class);

                    if (report.isPresent()) {
                        val excelReports = excelReportsBuilder.build(generalProperties, singletonList(report.get()));
                        val sheetName = String.format(SHEET_NAME, sample.getSampleProperties().getName());

                        return Stream.of(new ExcelSheet(time, excelReports, sheetName));
                    } else {
                        return Stream.empty();
                    }
                })
                .collect(Collectors.toList());
    }
}