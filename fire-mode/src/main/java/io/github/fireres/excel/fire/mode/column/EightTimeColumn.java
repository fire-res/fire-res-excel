package io.github.fireres.excel.fire.mode.column;

import io.github.fireres.excel.core.model.Column;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EightTimeColumn extends Column {

    private static final String HEADER = "8t + 1";

    public EightTimeColumn(Integer time) {
        super(HEADER, false, IntStream.range(0, time)
                .mapToObj(t -> 8 * t + 1)
                .collect(Collectors.toList()));
    }

}
