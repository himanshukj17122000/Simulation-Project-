package cellsociety;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Map;

public class Layout {
    private static final int MAX_SCREEN_HEIGHT = 1200;
    private static final int MAX_WIDTH = 400;
    private static final int CHART_WIDTH = 300;
    private static final int CHART_HEIGHT = 300;
    private static final Paint SIDEBAR_BACKGROUND = Color.web("5a76a1");

    public Button createButton(String text, String styleColor, Paint fontColor, int fontSize) {
        Button button = new Button(text);
        button.setTextFill(fontColor);
        button.setStyle("-fx-background-color:" + styleColor + ";-fx-font-size:" + fontSize + " px;");
        button.setPrefWidth(180);
        return button;
    }

    public Label createLabel(String text, int fontSize, Color textFill) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: " + fontSize);
        label.setTextFill(textFill);
        return label;
    }

    public Slider createSlider(double defaultValue, double min, double max, double majorTickUnit,
                               double minorTickCount, double blockIncrement) {
        Slider slider = new Slider();
        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(defaultValue);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(majorTickUnit);
        slider.setMinorTickCount((int) minorTickCount);
        slider.setBlockIncrement(blockIncrement);
        slider.setStyle("-fx-tick-label-fill: white");
        return slider;
    }

    public PieChart createChart(Simulation mySimulation) {
        Map<String, Integer> cellTypes = mySimulation.getTypesOfCells();
        ArrayList<PieChart.Data> cellData = new ArrayList<>();
        for (String cellType : cellTypes.keySet()) {
            PieChart.Data data = new PieChart.Data(cellType, cellTypes.get(cellType));
            cellData.add(data);
        }
        ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(cellData);
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Population Stats");
        chart.setPrefSize(CHART_WIDTH, CHART_HEIGHT);
        return chart;
    }

    public ScrollPane createScrollPane(VBox toolBar) {
        ScrollPane sp = new ScrollPane();
        sp.setVmax(MAX_SCREEN_HEIGHT);
        sp.setPrefWidth(MAX_WIDTH);
        sp.setFitToWidth(true);
        sp.setStyle("-fx-background: rgb(90, 118, 161)");
        sp.setContent(toolBar);
        return sp;
    }
}
