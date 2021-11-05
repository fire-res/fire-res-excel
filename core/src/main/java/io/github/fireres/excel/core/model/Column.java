package io.github.fireres.excel.core.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public abstract class Column {

    private final String header;
    private final boolean highlighted;
    private final List<Number> values;

}
