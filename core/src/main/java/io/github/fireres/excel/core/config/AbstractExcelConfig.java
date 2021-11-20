package io.github.fireres.excel.core.config;

import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.excel.core.ExcelReportConstructor;
import io.github.fireres.excel.core.ReportConstructor;
import io.github.fireres.excel.core.builder.ExcelSheetsBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractExcelConfig {

    @Bean
    public ReportConstructor reportConstructor(List<ExcelSheetsBuilder> sheetsBuilders,
                                               GeneralProperties generalProperties) {

        sheetsBuilders.sort(Comparator.comparingInt(b -> getOrder(b.getClass())));

        return new ExcelReportConstructor(sheetsBuilders, generalProperties);
    }

    protected abstract int getOrder(Class<? extends ExcelSheetsBuilder> builderClass);

}
