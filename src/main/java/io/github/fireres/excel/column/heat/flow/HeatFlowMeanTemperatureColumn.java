package io.github.fireres.excel.column.heat.flow;

import io.github.fireres.excel.chart.ChartColumn;
import io.github.fireres.excel.column.PointSequenceColumn;
import io.github.fireres.excel.style.chart.DefaultDataLineProperties;
import io.github.fireres.heatflow.model.MeanTemperature;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;

public class HeatFlowMeanTemperatureColumn extends PointSequenceColumn implements ChartColumn {

    private static final String HEADER = "Ср.";
    private static final String CHART_TITLE = "Среднее значение теплового потока";

    public HeatFlowMeanTemperatureColumn(MeanTemperature meanTemperature) {
        super(HEADER, false, meanTemperature);
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