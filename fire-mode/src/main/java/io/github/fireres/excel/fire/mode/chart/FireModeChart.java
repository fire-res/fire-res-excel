package io.github.fireres.excel.fire.mode.chart;

import io.github.fireres.excel.core.model.AbstractExcelChart;
import io.github.fireres.excel.core.model.Column;

import java.util.List;

public class FireModeChart extends AbstractExcelChart {

    private static final Integer HEIGHT = 25;
    private static final Integer WIDTH = 13;

    public FireModeChart(Integer time, List<Column> columns) {
        super(time, columns, HEIGHT, WIDTH);
    }

    @Override
    protected String getTitle() {
        return "Режим пожара";
    }

    @Override
    protected double getValueAxisMajorUnit() {
        return 200;
    }

    @Override
    protected String getValueAxisTitle() {
        return "Температура, оС";
    }

    @Override
    protected boolean isSmoothed() {
        return true;
    }
}
