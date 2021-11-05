package io.github.fireres.excel.fire.mode.column;

import io.github.fireres.excel.core.model.ChartColumn;
import io.github.fireres.excel.core.model.PointSequenceColumn;
import io.github.fireres.excel.core.style.chart.DefaultDataLineProperties;
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
