package io.github.fireres.excel.fire.mode.column;

import io.github.fireres.excel.core.model.PointSequenceColumn;
import io.github.fireres.firemode.model.ThermocoupleTemperature;

public class ThermocoupleTemperatureColumn extends PointSequenceColumn {

    private static final String HEADER = "ТП%d - %s";
    protected Integer index;

    public ThermocoupleTemperatureColumn(String sampleName, Integer index, ThermocoupleTemperature thermocoupleTemperature) {
        super(String.format(HEADER, index + 1, sampleName), false, thermocoupleTemperature);
        this.index = index;
    }
}
