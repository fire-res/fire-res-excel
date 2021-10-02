package io.github.fireres.excel.column.heat.flow;

import io.github.fireres.excel.chart.ChartColumn;
import io.github.fireres.excel.column.PointSequenceColumn;
import io.github.fireres.excel.style.chart.DefaultDataLineProperties;
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