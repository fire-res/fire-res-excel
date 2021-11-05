package io.github.fireres.excel.unheated.surface.column;

import io.github.fireres.excel.core.model.ChartColumn;
import io.github.fireres.excel.core.model.PointSequenceColumn;
import io.github.fireres.excel.core.style.chart.DefaultDataLineProperties;
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
