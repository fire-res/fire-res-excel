package io.github.fireres.excel.sheet;

import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.core.model.Report;
import io.github.fireres.core.model.Sample;
import io.github.fireres.excel.annotation.FireMode;
import io.github.fireres.excel.report.ExcelReportsBuilder;
import io.github.fireres.firemode.report.FireModeReport;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@FireMode
public class FireModeSheetsBuilder implements ExcelSheetsBuilder {

    private static final String SHEET_NAME = "Режим пожара";

    private final ExcelReportsBuilder excelReportsBuilder;

    @Autowired
    public FireModeSheetsBuilder(@FireMode ExcelReportsBuilder excelReportsBuilder) {
        this.excelReportsBuilder = excelReportsBuilder;
    }

    @Override
    public List<ExcelSheet> build(GeneralProperties generalProperties, List<Sample> samples) {
        val time = generalProperties.getTime();

        val reports = samples.stream()
                .map(sample -> sample.getReportByClass(FireModeReport.class))
                .filter(Optional::isPresent)
                .map(optional -> (Report) optional.get())
                .collect(Collectors.toList());

        if (!reports.isEmpty()) {
            val excelReports = excelReportsBuilder.build(generalProperties, reports);

            return Collections.singletonList(new ExcelSheet(time, excelReports, SHEET_NAME));
        } else {
            return Collections.emptyList();
        }
    }
}
