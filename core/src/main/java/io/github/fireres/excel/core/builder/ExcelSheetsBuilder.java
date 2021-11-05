package io.github.fireres.excel.core.builder;

import io.github.fireres.core.model.Sample;
import io.github.fireres.excel.core.model.ExcelSheet;

import java.util.List;

public interface ExcelSheetsBuilder {

    List<ExcelSheet> build(List<Sample> samples);

}
