package io.github.fireres.excel.sheet;

import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.core.model.Sample;

import java.util.List;

public interface ExcelSheetsBuilder {

    List<ExcelSheet> build(GeneralProperties generalProperties, List<Sample> samples);

}
