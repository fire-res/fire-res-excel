package io.github.fireres.excel.column.heat.flow;

import io.github.fireres.excel.chart.ChartColumn;
import io.github.fireres.excel.column.PointSequenceColumn;
import io.github.fireres.excel.style.chart.DefaultDataLineProperties;
import io.github.fireres.heatflow.model.SensorTemperature;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;

public class HeatFlowThermocoupleColumn extends PointSequenceColumn implements ChartColumn {

    private static final String HEADER = "ПТП";
    private static final String CHART_TITLE = "Приемник теплового потока ";

    private final Integer index;

    public HeatFlowThermocoupleColumn(Integer index, SensorTemperature sensor) {
        super(HEADER + (index + 1), false, sensor);
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