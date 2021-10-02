package io.github.fireres.excel.column.firemode;

import io.github.fireres.excel.column.PointSequenceColumn;
import io.github.fireres.firemode.model.ThermocoupleTemperature;

public class ThermocoupleTemperatureColumn extends PointSequenceColumn {

    private static final String HEADER = "ТП%d - %s";

    public ThermocoupleTemperatureColumn(String sampleName, Integer index, ThermocoupleTemperature thermocoupleTemperature) {
        super(String.format(HEADER, index, sampleName), false, thermocoupleTemperature);
    }
}
