package io.github.fireres.excel.core.model;

import org.apache.poi.xddf.usermodel.XDDFLineProperties;

public interface ChartColumn {

    String getChartLegendTitle();

    XDDFLineProperties getLineProperties();

}