package io.github.fireres.excel.column.firemode;

import io.github.fireres.excel.chart.ChartColumn;
import io.github.fireres.excel.column.PointSequenceColumn;
import io.github.fireres.excel.style.chart.DefaultDataLineProperties;
import io.github.fireres.firemode.model.MinAllowedTemperature;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;

public class MinAllowedTemperatureColumn extends PointSequenceColumn implements ChartColumn {

    private static final String HEADER = "Тмин - %s";
    private static final String CHART_TITLE = "Минимальный допуск температуры - %s";

    private final String sampleName;

    public MinAllowedTemperatureColumn(String sampleName, MinAllowedTemperature minAllowedTemperature) {
        super(String.format(HEADER, sampleName), false, minAllowedTemperature);
        this.sampleName = sampleName;
    }

    @Override
    public String getChartLegendTitle() {
        return String.format(CHART_TITLE, sampleName);
    }

    @Override
    public XDDFLineProperties getLineProperties() {
        return new DefaultDataLineProperties();
    }
}
