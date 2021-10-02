package io.github.fireres.excel.column.excess.pressure;

import io.github.fireres.excess.pressure.model.MaxAllowedPressure;
import io.github.fireres.excel.chart.ChartColumn;
import io.github.fireres.excel.column.PointSequenceColumn;
import io.github.fireres.excel.style.chart.DefaultDataLineProperties;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;

public class MaxAllowedPressureColumn extends PointSequenceColumn implements ChartColumn {

    private static final String HEADER = "Δмакс - %s";
    private static final String CHART_TITLE = "Максимальный допуск избыточного давления - %s";

    private final String sampleName;

    public MaxAllowedPressureColumn(String sampleName, MaxAllowedPressure maxAllowedPressure) {
        super(String.format(HEADER, sampleName), false, maxAllowedPressure);
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
