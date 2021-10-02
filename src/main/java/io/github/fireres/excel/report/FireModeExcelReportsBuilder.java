package io.github.fireres.excel.report;

import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.core.model.Report;
import io.github.fireres.excel.annotation.FireMode;
import io.github.fireres.excel.chart.FireModeChart;
import io.github.fireres.excel.column.Column;
import io.github.fireres.excel.column.firemode.EightTimeColumn;
import io.github.fireres.excel.column.firemode.EnvTempColumn;
import io.github.fireres.excel.column.firemode.FurnaceTemperatureColumn;
import io.github.fireres.excel.column.firemode.MaxAllowedTemperatureColumn;
import io.github.fireres.excel.column.firemode.MinAllowedTemperatureColumn;
import io.github.fireres.excel.column.firemode.StandardTemperatureColumn;
import io.github.fireres.excel.column.firemode.ThermocoupleTemperatureColumn;
import io.github.fireres.excel.column.firemode.ThermocouplesMeanTemperatureColumn;
import io.github.fireres.excel.column.TimeColumn;
import io.github.fireres.firemode.report.FireModeReport;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static io.github.fireres.firemode.utils.FireModeUtils.getMaintainedFurnaceTemperature;
import static io.github.fireres.firemode.utils.FireModeUtils.getMaintainedMaxAllowedTemperature;
import static io.github.fireres.firemode.utils.FireModeUtils.getMaintainedMinAllowedTemperature;
import static io.github.fireres.firemode.utils.FireModeUtils.getMaintainedStandardTemperature;
import static io.github.fireres.firemode.utils.FireModeUtils.getMaintainedThermocoupleMeanTemperature;
import static io.github.fireres.firemode.utils.FireModeUtils.getMaintainedThermocoupleTemperatures;

@Component
@FireMode
public class FireModeExcelReportsBuilder implements ExcelReportsBuilder {

    @Override
    public List<ExcelReport> build(GeneralProperties generalProperties, List<Report> reports) {
        val time = generalProperties.getTime();
        val data = createData(generalProperties, reports);

        return List.of(ExcelReport.builder()
                .data(data)
                .chart(new FireModeChart(time, data))
                .build());
    }

    protected List<Column> createData(GeneralProperties generalProperties, List<Report> reports) {
        val columns = new ArrayList<Column>();

        val time = generalProperties.getTime();
        val environmentTemperature = generalProperties.getEnvironmentTemperature();

        columns.add(new TimeColumn(time));
        columns.add(new EightTimeColumn(time));
        columns.add(new EnvTempColumn(time, environmentTemperature));

        for (Report report : reports) {
            val fireModeReport = (FireModeReport) report;
            val sampleName = report.getSample().getSampleProperties().getName();

            columns.add(new FurnaceTemperatureColumn(sampleName, getMaintainedFurnaceTemperature(fireModeReport)));

            if (fireModeReport.getProperties().getShowBounds()) {
                columns.add(new MinAllowedTemperatureColumn(sampleName, getMaintainedMinAllowedTemperature(fireModeReport)));
                columns.add(new MaxAllowedTemperatureColumn(sampleName, getMaintainedMaxAllowedTemperature(fireModeReport)));
                columns.add(new StandardTemperatureColumn(sampleName, getMaintainedStandardTemperature(fireModeReport)));
            }

            val thermocoupleTemperatures = getMaintainedThermocoupleTemperatures(fireModeReport);

            for (int t = 0; t < thermocoupleTemperatures.size(); t++) {
                val thermocoupleTemperature = thermocoupleTemperatures.get(t);
                columns.add(new ThermocoupleTemperatureColumn(sampleName, t + 1, thermocoupleTemperature));
            }

            if (fireModeReport.getProperties().getShowMeanTemperature()) {
                columns.add(new ThermocouplesMeanTemperatureColumn(sampleName, getMaintainedThermocoupleMeanTemperature(fireModeReport)));
            }
        }

        return columns;
    }

}
