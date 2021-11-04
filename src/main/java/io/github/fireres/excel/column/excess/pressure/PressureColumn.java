package io.github.fireres.excel.column.excess.pressure;

import io.github.fireres.core.model.DoublePoint;
import io.github.fireres.core.model.DoublePointSequence;
import io.github.fireres.excel.column.PointSequenceColumn;
import io.github.fireres.excess.pressure.model.ExcessPressure;

import java.util.stream.Collectors;

public class PressureColumn extends PointSequenceColumn {

    private static final String HEADER = "P - %s";

    public PressureColumn(String sampleName, ExcessPressure pressure, Double basePressure) {
        super(String.format(HEADER, sampleName), true,
                new DoublePointSequence(pressure.getValue().stream()
                        .map(p -> new DoublePoint(p.getTime(), basePressure + p.getNormalizedValue()))
                        .collect(Collectors.toList()))
        );
    }
}
