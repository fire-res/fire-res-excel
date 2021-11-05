package io.github.fireres.excel.core.style.chart;

import org.apache.poi.xddf.usermodel.PresetColor;

import static io.github.fireres.core.utils.RandomUtils.generateValueInInterval;
import static org.apache.poi.xddf.usermodel.PresetColor.AQUA;
import static org.apache.poi.xddf.usermodel.PresetColor.AQUAMARINE;
import static org.apache.poi.xddf.usermodel.PresetColor.BISQUE;
import static org.apache.poi.xddf.usermodel.PresetColor.BLACK;
import static org.apache.poi.xddf.usermodel.PresetColor.BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.BLUE_VIOLET;
import static org.apache.poi.xddf.usermodel.PresetColor.CADET_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.CHARTREUSE;
import static org.apache.poi.xddf.usermodel.PresetColor.CHOCOLATE;
import static org.apache.poi.xddf.usermodel.PresetColor.CORAL;
import static org.apache.poi.xddf.usermodel.PresetColor.CORNFLOWER_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.CRIMSON;
import static org.apache.poi.xddf.usermodel.PresetColor.CYAN;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_CYAN;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_GOLDENROD;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_GRAY;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_KHAKI;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_MAGENTA;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_OLIVE_GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_ORANGE;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_ORCHID;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_RED;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_SALMON;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_SEA_GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_SLATE_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_SLATE_GRAY;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_TURQUOISE;
import static org.apache.poi.xddf.usermodel.PresetColor.DARK_VIOLET;
import static org.apache.poi.xddf.usermodel.PresetColor.DEEP_PINK;
import static org.apache.poi.xddf.usermodel.PresetColor.DEEP_SKY_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.DIM_GRAY;
import static org.apache.poi.xddf.usermodel.PresetColor.DODGER_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.FIREBRICK;
import static org.apache.poi.xddf.usermodel.PresetColor.FOREST_GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.FUCHSIA;
import static org.apache.poi.xddf.usermodel.PresetColor.GOLD;
import static org.apache.poi.xddf.usermodel.PresetColor.GOLDENROD;
import static org.apache.poi.xddf.usermodel.PresetColor.GRAY;
import static org.apache.poi.xddf.usermodel.PresetColor.GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.GREEN_YELLOW;
import static org.apache.poi.xddf.usermodel.PresetColor.HOT_PINK;
import static org.apache.poi.xddf.usermodel.PresetColor.INDIAN_RED;
import static org.apache.poi.xddf.usermodel.PresetColor.INDIGO;
import static org.apache.poi.xddf.usermodel.PresetColor.KHAKI;
import static org.apache.poi.xddf.usermodel.PresetColor.LAVENDER;
import static org.apache.poi.xddf.usermodel.PresetColor.LAWN_GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.LIGHT_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.LIGHT_CORAL;
import static org.apache.poi.xddf.usermodel.PresetColor.LIGHT_GRAY;
import static org.apache.poi.xddf.usermodel.PresetColor.LIGHT_GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.LIGHT_PINK;
import static org.apache.poi.xddf.usermodel.PresetColor.LIGHT_SALMON;
import static org.apache.poi.xddf.usermodel.PresetColor.LIGHT_SEA_GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.LIGHT_SKY_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.LIGHT_SLATE_GRAY;
import static org.apache.poi.xddf.usermodel.PresetColor.LIGHT_STEEL_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.LIME;
import static org.apache.poi.xddf.usermodel.PresetColor.LIME_GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.MAGENTA;
import static org.apache.poi.xddf.usermodel.PresetColor.MAROON;
import static org.apache.poi.xddf.usermodel.PresetColor.MEDIUM_AQUAMARINE;
import static org.apache.poi.xddf.usermodel.PresetColor.MEDIUM_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.MEDIUM_ORCHID;
import static org.apache.poi.xddf.usermodel.PresetColor.MEDIUM_PURPLE;
import static org.apache.poi.xddf.usermodel.PresetColor.MEDIUM_SEA_GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.MEDIUM_SLATE_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.MEDIUM_SPRING_GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.MEDIUM_TURQUOISE;
import static org.apache.poi.xddf.usermodel.PresetColor.MEDIUM_VIOLET_RED;
import static org.apache.poi.xddf.usermodel.PresetColor.MIDNIGHT_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.MOCCASIN;
import static org.apache.poi.xddf.usermodel.PresetColor.NAVY;
import static org.apache.poi.xddf.usermodel.PresetColor.OLIVE;
import static org.apache.poi.xddf.usermodel.PresetColor.OLIVE_DRAB;
import static org.apache.poi.xddf.usermodel.PresetColor.ORANGE;
import static org.apache.poi.xddf.usermodel.PresetColor.ORANGE_RED;
import static org.apache.poi.xddf.usermodel.PresetColor.ORCHID;
import static org.apache.poi.xddf.usermodel.PresetColor.PALE_GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.PALE_TURQUOISE;
import static org.apache.poi.xddf.usermodel.PresetColor.PALE_VIOLET_RED;
import static org.apache.poi.xddf.usermodel.PresetColor.PEACH_PUFF;
import static org.apache.poi.xddf.usermodel.PresetColor.PERU;
import static org.apache.poi.xddf.usermodel.PresetColor.PINK;
import static org.apache.poi.xddf.usermodel.PresetColor.PLUM;
import static org.apache.poi.xddf.usermodel.PresetColor.POWDER_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.PURPLE;
import static org.apache.poi.xddf.usermodel.PresetColor.RED;
import static org.apache.poi.xddf.usermodel.PresetColor.ROSY_BROWN;
import static org.apache.poi.xddf.usermodel.PresetColor.ROYAL_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.SADDLE_BROWN;
import static org.apache.poi.xddf.usermodel.PresetColor.SALMON;
import static org.apache.poi.xddf.usermodel.PresetColor.SANDY_BROWN;
import static org.apache.poi.xddf.usermodel.PresetColor.SEA_GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.SIENNA;
import static org.apache.poi.xddf.usermodel.PresetColor.SILVER;
import static org.apache.poi.xddf.usermodel.PresetColor.SKY_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.SLATE_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.SLATE_GRAY;
import static org.apache.poi.xddf.usermodel.PresetColor.SPRING_GREEN;
import static org.apache.poi.xddf.usermodel.PresetColor.STEEL_BLUE;
import static org.apache.poi.xddf.usermodel.PresetColor.TAN;
import static org.apache.poi.xddf.usermodel.PresetColor.TEAL;
import static org.apache.poi.xddf.usermodel.PresetColor.THISTLE;
import static org.apache.poi.xddf.usermodel.PresetColor.TOMATO;
import static org.apache.poi.xddf.usermodel.PresetColor.TURQUOISE;
import static org.apache.poi.xddf.usermodel.PresetColor.VIOLET;
import static org.apache.poi.xddf.usermodel.PresetColor.WHEAT;
import static org.apache.poi.xddf.usermodel.PresetColor.YELLOW;
import static org.apache.poi.xddf.usermodel.PresetColor.YELLOW_GREEN;

public final class AvailableColors {

    private static final PresetColor[] COLORS = new PresetColor[]{
            AQUA,
            AQUAMARINE,
            BISQUE,
            BLACK,
            BLUE,
            BLUE_VIOLET,
            CADET_BLUE,
            CHARTREUSE,
            CHOCOLATE,
            CORAL,
            CORNFLOWER_BLUE,
            CRIMSON,
            CYAN,
            DEEP_PINK,
            DEEP_SKY_BLUE,
            DIM_GRAY,
            DARK_BLUE,
            DARK_CYAN,
            DARK_GOLDENROD,
            DARK_GRAY,
            DARK_GREEN,
            DARK_KHAKI,
            DARK_MAGENTA,
            DARK_OLIVE_GREEN,
            DARK_ORANGE,
            DARK_ORCHID,
            DARK_RED,
            DARK_SALMON,
            DARK_SEA_GREEN,
            DARK_SLATE_BLUE,
            DARK_SLATE_GRAY,
            DARK_TURQUOISE,
            DARK_VIOLET,
            DODGER_BLUE,
            FIREBRICK,
            FOREST_GREEN,
            FUCHSIA,
            GOLD,
            GOLDENROD,
            GRAY,
            GREEN,
            GREEN_YELLOW,
            HOT_PINK,
            INDIAN_RED,
            INDIGO,
            KHAKI,
            LAVENDER,
            LAWN_GREEN,
            LIME,
            LIME_GREEN,
            LIGHT_BLUE,
            LIGHT_CORAL,
            LIGHT_GRAY,
            LIGHT_GREEN,
            LIGHT_PINK,
            LIGHT_SALMON,
            LIGHT_SEA_GREEN,
            LIGHT_SKY_BLUE,
            LIGHT_SLATE_GRAY,
            LIGHT_STEEL_BLUE,
            MAGENTA,
            MAROON,
            MEDIUM_AQUAMARINE,
            MEDIUM_BLUE,
            MEDIUM_ORCHID,
            MEDIUM_PURPLE,
            MEDIUM_SEA_GREEN,
            MEDIUM_SLATE_BLUE,
            MEDIUM_SPRING_GREEN,
            MEDIUM_TURQUOISE,
            MEDIUM_VIOLET_RED,
            MIDNIGHT_BLUE,
            MOCCASIN,
            NAVY,
            OLIVE,
            OLIVE_DRAB,
            ORANGE,
            ORANGE_RED,
            ORCHID,
            PALE_GREEN,
            PALE_TURQUOISE,
            PALE_VIOLET_RED,
            PEACH_PUFF,
            PERU,
            PINK,
            PLUM,
            POWDER_BLUE,
            PURPLE,
            RED,
            ROSY_BROWN,
            ROYAL_BLUE,
            SADDLE_BROWN,
            SALMON,
            SANDY_BROWN,
            SEA_GREEN,
            SIENNA,
            SILVER,
            SKY_BLUE,
            SLATE_BLUE,
            SLATE_GRAY,
            SPRING_GREEN,
            STEEL_BLUE,
            TAN,
            TEAL,
            THISTLE,
            TOMATO,
            TURQUOISE,
            VIOLET,
            WHEAT,
            YELLOW,
            YELLOW_GREEN
    };

    public static PresetColor get() {
        return COLORS[generateValueInInterval(0, COLORS.length - 1)];
    }

}
