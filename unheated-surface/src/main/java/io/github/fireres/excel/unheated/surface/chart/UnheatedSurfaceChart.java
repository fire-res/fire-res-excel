package io.github.fireres.excel.unheated.surface.chart;

import io.github.fireres.excel.core.model.AbstractExcelChart;
import io.github.fireres.excel.core.model.Column;

import java.util.List;

public class UnheatedSurfaceChart extends AbstractExcelChart {

    private static final Integer HEIGHT = 25;
    private static final Integer WIDTH = 13;

    private final Integer index;

    public UnheatedSurfaceChart(Integer time, List<Column> columns, Integer index) {
        super(time, columns, HEIGHT, WIDTH);
        this.index = index;
    }

    @Override
    protected String getTitle() {
        return "Необогреваемая поверхность - Группа №" + index;
    }

    @Override
    protected double getValueAxisMajorUnit() {
        return 100;
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