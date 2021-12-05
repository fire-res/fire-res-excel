package io.github.fireres.excel.config;

import io.github.fireres.core.model.IntegerPoint;
import io.github.fireres.core.model.Point;
import io.github.fireres.core.model.Sample;
import io.github.fireres.core.properties.FunctionForm;
import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.core.properties.SampleProperties;
import io.github.fireres.excel.core.builder.ExcelSheetsBuilder;
import io.github.fireres.excel.core.config.AbstractExcelConfig;
import io.github.fireres.excess.pressure.properties.ExcessPressureProperties;
import io.github.fireres.firemode.properties.FireModeProperties;
import io.github.fireres.heatflow.properties.HeatFlowProperties;
import io.github.fireres.unheated.surface.properties.UnheatedSurfaceProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static io.github.fireres.excess.pressure.report.ExcessPressureReportType.EXCESS_PRESSURE;
import static io.github.fireres.firemode.report.FireModeReportType.FIRE_MODE;
import static io.github.fireres.heatflow.report.HeatFlowReportType.HEAT_FLOW;
import static io.github.fireres.unheated.surface.report.UnheatedSurfaceReportType.UNHEATED_SURFACE;

@TestConfiguration
public class TestConfig extends AbstractExcelConfig {

    public static final List<Point<Integer>> FIREMODE_INTERPOLATION_POINTS = List.of(
            new IntegerPoint(0, 40),
            new IntegerPoint(1, 306),
            new IntegerPoint(18, 749),
            new IntegerPoint(21, 789),
            new IntegerPoint(26, 822),
            new IntegerPoint(48, 898),
            new IntegerPoint(49, 901),
            new IntegerPoint(70, 943)
    );

    public static final List<Point<Integer>> UNHEATED_SURFACE_INTERPOLATION_POINTS = List.of(
            new IntegerPoint(70, 154)
    );

    @Bean
    public GeneralProperties generalProperties() {
        return GeneralProperties.builder()
                .includedReports(List.of(
                        FIRE_MODE,
                        EXCESS_PRESSURE,
                        HEAT_FLOW,
                        UNHEATED_SURFACE
                ))
                .environmentTemperature(21)
                .time(71)
                .build();
    }

    @Bean
    public FireModeProperties fireModeProperties() {
        return FireModeProperties.builder()
                .functionForm(FunctionForm.<Integer>builder()
                        .linearityCoefficient(0.3)
                        .interpolationPoints(FIREMODE_INTERPOLATION_POINTS)
                        .build())
                .thermocoupleCount(6)
                .build();
    }

    @Bean
    public ExcessPressureProperties excessPressureProperties() {
        return ExcessPressureProperties.builder()
                .basePressure(10.0)
                .delta(2.0)
                .build();
    }

    @Bean
    public HeatFlowProperties heatFlowProperties() {
        return HeatFlowProperties.builder()
                .sensorCount(3)
                .bound(3.5)
                .build();
    }

    @Bean
    public List<UnheatedSurfaceProperties> unheatedSurfaceProperties() {
        return List.of(
                UnheatedSurfaceProperties.builder()
                        .functionForm(FunctionForm.<Integer>builder()
                                .interpolationPoints(UNHEATED_SURFACE_INTERPOLATION_POINTS)
                                .linearityCoefficient(1d)
                                .build())
                        .thermocoupleCount(5)
                        .build(),
                UnheatedSurfaceProperties.builder()
                        .functionForm(FunctionForm.<Integer>builder()
                                .linearityCoefficient(1d)
                                .build())
                        .thermocoupleCount(6)
                        .bound(300)
                        .build(),
                UnheatedSurfaceProperties.builder()
                        .functionForm(FunctionForm.<Integer>builder()
                                .linearityCoefficient(1d)
                                .build())
                        .thermocoupleCount(6)
                        .bound(300)
                        .build());
    }

    @Bean
    public SampleProperties sampleProperties() {
        return SampleProperties.builder()
                .name("test sample")
                .build();
    }

    @Bean
    public Sample sample(SampleProperties sampleProperties) {
        return new Sample(sampleProperties);
    }

    @Override
    protected int getOrder(Class<? extends ExcelSheetsBuilder> builderClass) {
        return 0;
    }
}
