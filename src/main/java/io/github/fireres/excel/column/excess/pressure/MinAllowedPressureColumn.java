package io.github.fireres.excel.column.excess.pressure;

import io.github.fireres.excel.chart.ChartColumn;
import io.github.fireres.excel.column.PointSequenceColumn;
import io.github.fireres.excel.style.chart.DefaultDataLineProperties;
import io.github.fireres.excess.pressure.model.MinAllowedPressure;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;

public class MinAllowedPressureColumn extends PointSequenceColumn implements ChartColumn {

    private static final String HEADER = "Δмин - %s";
    private static final String CHART_TITLE = "Минимальный допуск избыточного давления - %s";

    private final String sampleName;

    public MinAllowedPressureColumn(String sampleName, MinAllowedPressure minAllowedPressure) {
        super(String.format(HEADER, sampleName), false, minAllowedPressure);
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
