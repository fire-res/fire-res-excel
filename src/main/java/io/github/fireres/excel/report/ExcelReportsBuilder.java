package io.github.fireres.excel.report;

import io.github.fireres.core.model.Report;

import java.util.List;

public interface ExcelReportsBuilder {

    List<ExcelReport> build(List<Report> reports);

}
