package io.github.fireres.excel.excess.pressure.chart;

import io.github.fireres.excel.core.model.AbstractExcelChart;
import io.github.fireres.excel.core.model.Column;

import java.util.List;

public class ExcessPressureChart extends AbstractExcelChart {

    private static final Integer HEIGHT = 25;
    private static final Integer WIDTH = 13;

    private final String title = "Избыточное давление";
    private String valueAxisTitle = "Избыточное давление";

    public ExcessPressureChart(Integer time, List<Column> columns) {
        super(time, columns, HEIGHT, WIDTH);
    }

    @Override
    protected String getTitle() {
        return title;
    }

    @Override
    protected double getValueAxisMajorUnit() {
        return 0.5;
    }

    @Override
    public String getValueAxisTitle() {
        return valueAxisTitle;
    }

    public void setValueAxisTitle(String valueAxisTitle) {
        this.valueAxisTitle = valueAxisTitle;
    }

    @Override
    protected boolean isSmoothed() {
        return false;
    }
}
