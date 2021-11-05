package io.github.fireres.excel.excess.pressure.column;

import io.github.fireres.excel.core.model.ChartColumn;
import io.github.fireres.excel.core.model.PointSequenceColumn;
import io.github.fireres.excel.core.style.chart.DefaultDataLineProperties;
import io.github.fireres.excess.pressure.model.ExcessPressureMinAllowedPressure;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;

public class MinAllowedPressureColumn extends PointSequenceColumn implements ChartColumn {

    private static final String HEADER = "Δмин - %s";
    private static final String CHART_TITLE = "Минимальный допуск избыточного давления - %s";

    private final String sampleName;

    public MinAllowedPressureColumn(String sampleName, ExcessPressureMinAllowedPressure minAllowedPressure) {
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
