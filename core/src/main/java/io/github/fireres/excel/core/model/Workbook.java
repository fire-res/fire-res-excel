package io.github.fireres.excel.core.model;

import lombok.val;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static io.github.fireres.excel.core.config.DefaultFontConfig.DEFAULT_FONT_SIZE;
import static io.github.fireres.excel.core.config.DefaultFontConfig.TIMES_NEW_ROMAN;

public class Workbook extends XSSFWorkbook {

    @Override
    public XSSFFont createFont() {
        val font = super.createFont();

        font.setFontName(TIMES_NEW_ROMAN);
        font.setFontHeight(DEFAULT_FONT_SIZE);

        return font;
    }

}
