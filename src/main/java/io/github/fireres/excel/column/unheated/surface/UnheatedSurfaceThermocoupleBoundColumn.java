package io.github.fireres.excel.column.unheated.surface;

import io.github.fireres.excel.chart.ChartColumn;
import io.github.fireres.excel.column.PointSequenceColumn;
import io.github.fireres.excel.style.chart.DefaultDataLineProperties;
import io.github.fireres.unheated.surface.model.MaxAllowedThermocoupleTemperature;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;

public class UnheatedSurfaceThermocoupleBoundColumn extends PointSequenceColumn implements ChartColumn {

    private static final String HEADER = "ТП max";
    private static final String CHART_TITLE = "Предельное значение температуры термопар";

    public UnheatedSurfaceThermocoupleBoundColumn(MaxAllowedThermocoupleTemperature thermocouple) {
        super(HEADER, false, thermocouple);
    }

    @Override
    public String getChartLegendTitle() {
        return CHART_TITLE;
    }

    @Override
    public XDDFLineProperties getLineProperties() {
        return new DefaultDataLineProperties();
    }
}
