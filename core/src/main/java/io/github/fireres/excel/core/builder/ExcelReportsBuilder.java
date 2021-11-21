package io.github.fireres.excel.core.builder;

import io.github.fireres.core.model.Report;
import io.github.fireres.core.model.ReportType;
import io.github.fireres.excel.core.model.ExcelReport;

import java.util.List;

public interface ExcelReportsBuilder {

    List<ExcelReport> build(List<Report> reports);

    ReportType supportedReportType();

}
