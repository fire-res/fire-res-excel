package io.github.fireres.excel.column.unheated.surface;

import io.github.fireres.excel.chart.ChartColumn;
import io.github.fireres.excel.column.PointSequenceColumn;
import io.github.fireres.excel.style.chart.DefaultDataLineProperties;
import io.github.fireres.unheated.surface.model.MeanTemperature;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;

public class UnheatedSurfaceMeanColumn extends PointSequenceColumn implements ChartColumn {

    private static final String HEADER = "Ср. ар.";
    private static final String CHART_TITLE = "Среднее значение температуры";

    public UnheatedSurfaceMeanColumn(MeanTemperature meanTemperature) {
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
