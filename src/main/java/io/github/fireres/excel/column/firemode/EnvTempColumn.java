package io.github.fireres.excel.column.firemode;

import io.github.fireres.excel.column.Column;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EnvTempColumn extends Column {

    private static final String HEADER = "Т0";

    public EnvTempColumn(Integer time, Integer envTemp) {
        super(HEADER, false, IntStream.range(0, time)
                .mapToObj(t -> envTemp)
                .collect(Collectors.toList()));
    }

}
