package io.github.fireres.excel.heat.flow.chart;

import io.github.fireres.excel.core.model.AbstractExcelChart;
import io.github.fireres.excel.core.model.Column;

import java.util.List;

public class HeatFlowChart extends AbstractExcelChart {

    private static final Integer HEIGHT = 25;
    private static final Integer WIDTH = 13;

    public HeatFlowChart(Integer time, List<Column> columns) {
        super(time, columns, HEIGHT, WIDTH);
    }

    @Override
    protected String getTitle() {
        return "Плотность теплового потока";
    }

    @Override
    protected double getValueAxisMajorUnit() {
        return 0.5;
    }

    @Override
    protected String getValueAxisTitle() {
        return "Тепловой поток, КВт/м^2";
    }

    @Override
    protected boolean isSmoothed() {
        return true;
    }

}