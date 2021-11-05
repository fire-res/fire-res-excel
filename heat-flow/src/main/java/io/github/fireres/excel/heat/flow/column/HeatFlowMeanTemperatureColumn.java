package io.github.fireres.excel.heat.flow.column;

import io.github.fireres.excel.core.model.ChartColumn;
import io.github.fireres.excel.core.model.PointSequenceColumn;
import io.github.fireres.excel.core.style.chart.DefaultDataLineProperties;
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