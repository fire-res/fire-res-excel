package io.github.fireres.excel.excess.pressure.column;

import io.github.fireres.core.model.DoublePoint;
import io.github.fireres.core.model.DoublePointSequence;
import io.github.fireres.excel.core.model.ChartColumn;
import io.github.fireres.excel.core.model.PointSequenceColumn;
import io.github.fireres.excel.core.style.chart.DefaultDataLineProperties;
import io.github.fireres.excess.pressure.model.ExcessPressure;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;

import java.util.stream.Collectors;

public class DeltaColumn extends PointSequenceColumn implements ChartColumn {

    private static final String HEADER = "Δ - %s";
    private static final String CHART_TITLE = "Избыточное давление - %s";

    private final Object sampleName;

    public DeltaColumn(String sampleName, ExcessPressure pressure) {
        super(String.format(HEADER, sampleName), true,
                new DoublePointSequence(pressure.getValue().stream()
                        .map(p -> new DoublePoint(p.getTime(), p.getNormalizedValue()))
                        .collect(Collectors.toList())));

        this.sampleName = sampleName;
    }

    @Override
    public String getChartLegendTitle() {
        return String.format(CHART_TITLE, sampleName);
    }

    @Override
    public XDDFLineProperties getLineProperties() {
        return new DefaultDataLineProperties();
    }
}
