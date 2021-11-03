package io.github.fireres.excel.sheet;

import io.github.fireres.core.model.Sample;

import java.util.List;

public interface ExcelSheetsBuilder {

    List<ExcelSheet> build(List<Sample> samples);

}
