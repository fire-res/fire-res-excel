package io.github.fireres.excel.sheet;

import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.core.model.Sample;
import io.github.fireres.excel.annotation.ExcessPressure;
import io.github.fireres.excel.report.ExcelReportsBuilder;
import io.github.fireres.excess.pressure.report.ExcessPressureReport;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ExcessPressure
public class ExcessPressureSheetsBuilder implements ExcelSheetsBuilder {

    private static final String SHEET_NAME = "Избыточное давление";

    private final ExcelReportsBuilder excelReportsBuilder;
    private final GeneralProperties generalProperties;

    @Autowired
    public ExcessPressureSheetsBuilder(@ExcessPressure ExcelReportsBuilder excelReportsBuilder,
                                       GeneralProperties generalProperties) {
        this.excelReportsBuilder = excelReportsBuilder;
        this.generalProperties = generalProperties;
    }

    @Override
    public List<ExcelSheet> build(List<Sample> samples) {
        val time = generalProperties.getTime();

        val reports = samples.stream()
                .flatMap(sample -> sample.getReports().stream())
                .filter(report -> report instanceof ExcessPressureReport)
                .collect(Collectors.toList());

        if (!reports.isEmpty()) {
            val excelReports = excelReportsBuilder.build(reports);

            return Collections.singletonList(new ExcelSheet(time, excelReports, SHEET_NAME));
        } else {
            return Collections.emptyList();
        }
    }
}
