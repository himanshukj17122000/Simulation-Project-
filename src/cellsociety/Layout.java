package cellsociety;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Map;

public class Layout {
    public Button createButton(String text, String styleColor, int fontSize) {
        Button button = new Button(text);
        button.setTextFill(Color.BLACK);
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
        chart.setStyle("-fx-background-color: #ffffff");
        return chart;
    }
}
