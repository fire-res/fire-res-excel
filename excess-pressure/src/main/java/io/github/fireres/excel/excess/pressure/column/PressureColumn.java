package io.github.fireres.excel.excess.pressure.column;

import io.github.fireres.core.model.DoublePoint;
import io.github.fireres.core.model.DoublePointSequence;
import io.github.fireres.excel.core.model.PointSequenceColumn;
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
