package io.github.fireres.excel.heat.flow.column;

import io.github.fireres.excel.core.model.ChartColumn;
import io.github.fireres.excel.core.model.PointSequenceColumn;
import io.github.fireres.excel.core.style.chart.DefaultDataLineProperties;
import io.github.fireres.heatflow.model.MaxAllowedFlow;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;

public class HeatFlowBoundColumn extends PointSequenceColumn implements ChartColumn {

    private static final String HEADER = "ТП max";
    private static final String CHART_TITLE = "Предельное значение теплового потока";

    public HeatFlowBoundColumn(MaxAllowedFlow thermocouple) {
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