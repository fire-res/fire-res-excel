package io.github.fireres.excel.core.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.URL;

@Configuration
@Slf4j
public class ApachePoiConfig {

    @PostConstruct
    public void setupApachePoi() {
        ClassLoader classloader =
                POIFSFileSystem.class.getClassLoader();
        URL res = classloader.getResource(
                "org/apache/poi/poifs/filesystem/POIFSFileSystem.class");
        String path = res.getPath();
        log.info("POI Core came from " + path);

        classloader = POIXMLDocument.class.getClassLoader();
        res = classloader.getResource("org/apache/poi/ooxml/POIXMLDocument.class");
        path = res.getPath();
        log.info("POI OOXML came from " + path);

        classloader = HSLFSlideShow.class.getClassLoader();
        res = classloader.getResource("org/apache/poi/hslf/usermodel/HSLFSlideShow.class");
        path = res.getPath();
        log.info("POI Scratchpad came from " + path);
    }

}
