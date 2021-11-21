package io.github.fireres.excel.core.model;

import io.github.fireres.excel.core.style.chart.AxisGridLineProperties;
import io.github.fireres.excel.core.style.chart.AxisLineProperties;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.math3.util.Pair;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.AxisTickMark;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.MarkerStyle;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFScatterChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory.fromNumericCellRange;

@RequiredArgsConstructor
public abstract class AbstractExcelChart implements ExcelChart {

    private final Integer time;
    private final List<Column> columns;
    private final Integer height;
    private final Integer width;

    public void plot(XSSFSheet sheet, Integer position) {
        val drawing = sheet.createDrawingPatriarch();
        val anchor = createAnchor(drawing, position);

        val chart = drawing.createChart(anchor);
        createLegend(chart);

        val data = createData(chart);
        addDataSeries(sheet, data, position);

        chart.plot(data);
    }

    protected abstract String getTitle();

    private XDDFChartData createData(XSSFChart chart) {
        return chart.createData(ChartTypes.SCATTER, createCategoryAxis(chart), createValueAxis(chart));
    }

    private XDDFValueAxis createValueAxis(XSSFChart chart) {
        val valueAxis = chart.createValueAxis(AxisPosition.LEFT);
        valueAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        valueAxis.setTitle(getValueAxisTitle());
        valueAxis.setVisible(true);
        valueAxis.setMajorTickMark(AxisTickMark.OUT);
        valueAxis.setMinorTickMark(AxisTickMark.NONE);

        val shape = valueAxis.getOrAddShapeProperties();
        shape.setLineProperties(new AxisLineProperties());

        val grid = valueAxis.getOrAddMajorGridProperties();
        grid.setLineProperties(new AxisGridLineProperties());

        return valueAxis;
    }

    private XDDFCategoryAxis createCategoryAxis(XSSFChart chart) {
        val categoryAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        categoryAxis.setTitle("Время, мин.");
        categoryAxis.setVisible(true);
        categoryAxis.setMajorTickMark(AxisTickMark.OUT);
        categoryAxis.setMinorTickMark(AxisTickMark.NONE);

        val shape = categoryAxis.getOrAddShapeProperties();
        shape.setLineProperties(new AxisLineProperties());

        val grid = categoryAxis.getOrAddMajorGridProperties();
        grid.setLineProperties(new AxisGridLineProperties());

        return categoryAxis;
    }

    private void createLegend(XSSFChart chart) {
        val legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.BOTTOM);
    }

    private XSSFClientAnchor createAnchor(XSSFDrawing drawing, Integer position) {
        val columnCount = columns.size();

        return drawing.createAnchor(
                0, 0, 0, 0,
                columnCount + 1, position + 1, columnCount + width, position + height);
    }

    private void addDataSeries(XSSFSheet sheet, XDDFChartData data, Integer position) {
        val timeSource = dataSource(sheet, findColumnIndex(columns, TimeColumn.class), position);

        for (val column : collectChartColumns(columns)) {
            val dataSource = dataSource(sheet, column.getFirst(), position);
            val series = (XDDFScatterChartData.Series) data.addSeries(timeSource, dataSource);

            series.setMarkerStyle(MarkerStyle.NONE);
            series.setSmooth(isSmoothed());
            series.setLineProperties(column.getSecond().getLineProperties());
            series.setTitle(column.getSecond().getChartLegendTitle(), null);
        }
    }

    private List<Pair<Integer, ChartColumn>> collectChartColumns(List<Column> columns) {
        return IntStream.range(0, columns.size())
                .filter(i -> columns.get(i) instanceof ChartColumn)
                .mapToObj(i -> new Pair<>(i, (ChartColumn) columns.get(i)))
                .collect(Collectors.toList());
    }

    private Integer findColumnIndex(List<Column> columns, Class<? extends Column> clazz) {
        return IntStream.range(0, columns.size())
                .filter(i -> columns.get(i).getClass() == clazz)
                .findFirst()
                .orElseThrow();
    }

    private XDDFNumericalDataSource<Double> dataSource(XSSFSheet sheet, Integer column, Integer position) {
        return fromNumericCellRange(sheet,
                new CellRangeAddress(position + 1, position + time, column, column));
    }

    protected abstract String getValueAxisTitle();

    protected abstract boolean isSmoothed();

}
