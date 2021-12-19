package io.github.fireres.excel.fire.mode.column;

import io.github.fireres.excel.core.model.ChartColumn;
import io.github.fireres.excel.core.style.chart.ThinDataLineProperties;
import io.github.fireres.firemode.model.ThermocoupleTemperature;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;

public class ThermocoupleTemperatureChartColumn extends ThermocoupleTemperatureColumn implements ChartColumn {

    private static final String CHART_TITLE = "Термопара %s";

    public ThermocoupleTemperatureChartColumn(String sampleName,
                                              Integer index,
                                              ThermocoupleTemperature thermocoupleTemperature) {

        super(sampleName, index, thermocoupleTemperature);
    }

    @Override
    public String getChartLegendTitle() {
        return String.format(CHART_TITLE, index + 1);
    }

    @Override
    public XDDFLineProperties getLineProperties() {
        return new ThinDataLineProperties();
    }

}
