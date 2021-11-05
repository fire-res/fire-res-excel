package io.github.fireres.excel.unheated.surface.column;

import io.github.fireres.excel.core.model.ChartColumn;
import io.github.fireres.excel.core.model.PointSequenceColumn;
import io.github.fireres.excel.core.style.chart.DefaultDataLineProperties;
import io.github.fireres.unheated.surface.model.ThermocoupleTemperature;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;

public class UnheatedSurfaceThermocoupleColumn extends PointSequenceColumn implements ChartColumn {

    private static final String HEADER = "ТП";
    private static final String CHART_TITLE = "Термопара ";

    private final Integer index;

    public UnheatedSurfaceThermocoupleColumn(Integer index, ThermocoupleTemperature thermocouple) {
        super(HEADER + (index + 1), false, thermocouple);
        this.index = index;
    }

    @Override
    public String getChartLegendTitle() {
        return CHART_TITLE + (index + 1);
    }

    @Override
    public XDDFLineProperties getLineProperties() {
        return new DefaultDataLineProperties();
    }
}
