package io.github.fireres.excel.core;

import io.github.fireres.core.model.Sample;
import io.github.fireres.excel.core.builder.ExcelSheetsBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExcelReportConstructor implements ReportConstructor {

    public static final String TIMES_NEW_ROMAN = "Times New Roman";

    private final List<ExcelSheetsBuilder> sheetsBuilders;

    @Override
    @SneakyThrows
    public void construct(List<Sample> samples, File outputFile) {
        log.info("Writing excel report to: {}", outputFile.getAbsolutePath());

        try (val excel = generateExcel(samples);
             val outputStream = new FileOutputStream(outputFile)) {
            excel.write(outputStream);
        }
    }

    private Workbook generateExcel(List<Sample> samples) {
        val workbook = new XSSFWorkbook();

        sheetsBuilders.forEach(builder -> {
            val sheets = builder.build(samples);

            sheets.forEach(sheet -> sheet.create(workbook));
        });

        return workbook;
    }

}
