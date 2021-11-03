package io.github.fireres.excel;

import io.github.fireres.core.model.Sample;

import java.io.File;
import java.util.List;

public interface ReportConstructor {

    void construct(List<Sample> samples, File outputFile);

}
