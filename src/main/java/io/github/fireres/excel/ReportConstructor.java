package io.github.fireres.excel;

import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.core.model.Sample;

import java.io.File;
import java.util.List;

public interface ReportConstructor {

    void construct(GeneralProperties generalProperties, List<Sample> samples, File outputFile);

}
