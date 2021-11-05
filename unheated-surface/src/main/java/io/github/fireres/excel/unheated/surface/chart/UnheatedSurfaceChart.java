package io.github.fireres.excel.unheated.surface.chart;

import io.github.fireres.excel.core.model.AbstractExcelChart;
import io.github.fireres.excel.core.model.Column;

import java.util.List;

public class UnheatedSurfaceChart extends AbstractExcelChart {

    private static final Integer HEIGHT = 50;
    private static final Integer WIDTH = 25;

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
    protected String getValueAxisTitle() {
        return "Температура, оС";
    }

    @Override
    protected boolean isSmoothed() {
        return true;
    }

}