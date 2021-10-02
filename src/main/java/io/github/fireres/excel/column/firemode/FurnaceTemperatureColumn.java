package io.github.fireres.excel.column.firemode;

import io.github.fireres.excel.column.PointSequenceColumn;
import io.github.fireres.firemode.model.FurnaceTemperature;

public class FurnaceTemperatureColumn extends PointSequenceColumn {

    private static final String HEADER = "Т - %s";

    public FurnaceTemperatureColumn(String sampleName, FurnaceTemperature points) {
        super(String.format(HEADER, sampleName), false, points);
    }
}
