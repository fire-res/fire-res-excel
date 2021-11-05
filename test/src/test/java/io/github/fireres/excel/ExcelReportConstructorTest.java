package io.github.fireres.excel;

import io.github.fireres.core.model.Sample;
import io.github.fireres.excel.config.TestConfig;
import io.github.fireres.excel.core.ReportConstructor;
import io.github.fireres.excess.pressure.properties.ExcessPressureProperties;
import io.github.fireres.excess.pressure.service.ExcessPressureService;
import io.github.fireres.firemode.properties.FireModeProperties;
import io.github.fireres.firemode.service.FireModeService;
import io.github.fireres.heatflow.properties.HeatFlowProperties;
import io.github.fireres.heatflow.service.HeatFlowService;
import io.github.fireres.unheated.surface.properties.UnheatedSurfaceProperties;
import io.github.fireres.unheated.surface.service.UnheatedSurfaceService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.io.TempDir;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static io.github.fireres.core.test.TestUtils.TEST_ATTEMPTS;
import static java.util.Collections.singletonList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles({"test"})
@SpringBootConfiguration
@ComponentScan(basePackages = "io.github.fireres")
public class ExcelReportConstructorTest {

    @TempDir
    public static Path temporaryFolder;

    @Autowired
    private ReportConstructor reportConstructor;

    @Autowired
    private FireModeService fireModeService;

    @Autowired
    private ExcessPressureService excessPressureService;

    @Autowired
    private UnheatedSurfaceService unheatedSurfaceService;

    @Autowired
    private HeatFlowService heatFlowService;

    @Autowired
    private Sample sample;

    @Autowired
    private FireModeProperties fireModeProperties;

    @Autowired
    private ExcessPressureProperties excessPressureProperties;

    @Autowired
    private HeatFlowProperties heatFlowProperties;

    @Autowired
    private List<UnheatedSurfaceProperties> unheatedSurfaceProperties;

    @BeforeEach
    public void setup() {
        sample.removeAllReports();
    }

    @RepeatedTest(TEST_ATTEMPTS / 10)
    public void construct() {
        fireModeService.createReport(sample, fireModeProperties);
        excessPressureService.createReport(sample, excessPressureProperties);
        heatFlowService.createReport(sample, heatFlowProperties);
        unheatedSurfaceProperties.forEach(p ->
                unheatedSurfaceService.createReport(sample, p));

        File file = null;
        try {
            file = temporaryFolder.resolve("test.xls").toFile();
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        reportConstructor.construct(singletonList(sample), file);

        file.delete();
    }

    //todo: fix
    @Test
    public void forRemoval() {
        //do nothing
    }

}