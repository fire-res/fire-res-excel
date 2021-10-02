package io.github.fireres.excel;

import io.github.fireres.core.properties.GenerationProperties;
import io.github.fireres.core.model.Sample;
import io.github.fireres.excel.config.TestConfig;
import io.github.fireres.excess.pressure.service.ExcessPressureService;
import io.github.fireres.firemode.service.FireModeService;
import io.github.fireres.heatflow.service.HeatFlowService;
import io.github.fireres.unheated.surface.service.UnheatedSurfaceService;
import lombok.val;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static io.github.fireres.core.test.TestUtils.repeatTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles({"test"})
@SpringBootConfiguration
@ComponentScan(basePackages = "io.github.fireres")
public class ExcelReportConstructorTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

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
    private GenerationProperties generationProperties;

    @Test
    public void construct() {
        repeatTest(() -> {
            val sample = new Sample(generationProperties.getSamples().get(0));

            fireModeService.createReport(sample);
            excessPressureService.createReport(sample);
            unheatedSurfaceService.createReport(sample);
            heatFlowService.createReport(sample);

            File file = null;
            try {
                file = temporaryFolder.newFile("test.xls");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            reportConstructor.construct(generationProperties.getGeneral(), Collections.singletonList(sample), file);

            file.delete();
        });
    }

}