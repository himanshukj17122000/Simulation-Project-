package cellsociety;

import cellsociety.Configuration.Configuration;
import cellsociety.Visualization.DialogBox;
import cellsociety.Visualization.Splash;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Layout {
    private static final String BUTTON_STYLE_COLOR = "#3197bc";
    private static final int BUTTON_FONT_SIZE = 16;
    private static final double MAX_SPEED = 1000;
    private static final double MIN_SPEED = 0;
    private static final double DEFAULT_SPEED = 500;

    private Configuration mySimulationConfig;
    private Timeline myTimeline;
    private Button buttonPause;
    private Button buttonStep;
    private Button buttonResume;
    private Boolean isPaused;
    private double mySpeed;

    public Timeline getTimeline() { return myTimeline; }
    public Boolean getIsPaused() { return isPaused; }
    public Button getButtonPause() { return buttonPause; }
    public Button getButtonResume() { return buttonResume; }
    public Button getButtonStep() { return buttonStep; }
    public double getSpeed() { return mySpeed;}
    public void setSpeed(double speed) { mySpeed = speed; }
    public void setTimeline(Timeline timeline) {myTimeline = timeline; }

    public void implementButtons(Stage primaryStage, VBox toolBar) {
        Button buttonHome = createButton("Back to Main", "lightgray", BUTTON_FONT_SIZE);
        buttonHome.setOnAction(e -> primaryStage.setScene(new Splash(primaryStage).getSplashScene()));
        buttonPause = createButton("Pause Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        isPaused = false;
        if (!isPaused) {
            pauseSim(buttonPause);
        }
        buttonStep = createButton("Next Step", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        buttonResume = createButton("Resume Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        if (isPaused) {
            stepSim(buttonStep);
            resumeSim(buttonResume);
        }
        Button buttonStop = createButton("Stop Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        stopSim(buttonStop);
        Button buttonRestart = createButton("Restart Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        restartSim(buttonRestart);
        Button buttonUpload = createButton("Upload New Simulation", BUTTON_STYLE_COLOR, BUTTON_FONT_SIZE);
        uploadSim(buttonUpload, primaryStage);
        toolBar.getChildren().addAll(buttonHome, buttonPause, buttonStep, buttonResume, buttonStop, buttonUpload);
    }

    public Button createButton(String text, String styleColor, int fontSize) {
        Button button = new Button(text);
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color:" + styleColor + ";-fx-font-size:" + fontSize + " px;");
        button.setPrefWidth(180);
        return button;
    }

    public void implementSlider(Configuration simulationConfig, VBox toolBar) {
        String probCatchLabel = simulationConfig.getProbCatchLabel();
        if (probCatchLabel != null) {
            Label setProbCatch = createLabel("Set the " + probCatchLabel + ":", 16, Color.WHITE);
            Slider probabilitySlider = createSlider(simulationConfig.getProbCatch(), 0, 1, 0.5,
                    5, 0.05);
            updateProbCatch(probabilitySlider, simulationConfig);
            toolBar.getChildren().addAll(setProbCatch, probabilitySlider);
        }
        Label setSpeed = createLabel ("Set the simulation speed:", 16, Color.WHITE);
        Slider mySpeedSlider = createSlider(DEFAULT_SPEED, MIN_SPEED, MAX_SPEED, (MAX_SPEED - MIN_SPEED) / 2,
                (MAX_SPEED - MIN_SPEED) / 100, 100);
        updateSpeed(mySpeedSlider);
        toolBar.getChildren().addAll(setSpeed, mySpeedSlider);
    }

    private void updateProbCatch(Slider slider, Configuration simulationConfig) {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> simulationConfig.setProbCatch((double) newValue));
    }

    private void updateSpeed(Slider slider) {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> mySpeed = (double) newValue);
    }

    private Label createLabel(String text, int fontSize, Color textFill) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: " + fontSize);
        label.setTextFill(textFill);
        return label;
    }

    private Slider createSlider(double defaultValue, double min, double max, double majorTickUnit,
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

    // Next 6 methods: Creating the button functions
    public void pauseSim(Button buttonPause) {
        buttonPause.setOnAction(e -> myTimeline.pause());
        isPaused = true;
    }

    public void stepSim(Button buttonStep, Simulation simulation) {
        buttonStep.setOnAction(e -> {
            mySimulation.step();
            drawGrid(myGrid);
        });
    }

    public void resumeSim(Button buttonResume) {
        buttonResume.setOnAction(e -> myTimeline.play());
        isPaused = false;
    }

    private void restartSim(Button buttonRestart) {
        buttonRestart.setOnAction(e -> {
            myTimeline.pause();
            myTimeline = new Timeline();
        });
    }

    private void stopSim(Button buttonStop) {
        buttonStop.setOnAction(e -> myTimeline.stop());
    }

    private void uploadSim(Button buttonUpload, Stage primaryStage) {
        buttonUpload.setOnAction(e -> {
            try {
                DialogBox popup = new DialogBox();
                popup.start(primaryStage, mySimulationConfig);
                mySimulationConfig = popup.getSimulationConfig();
            } catch (NullPointerException ex) {
                String errorMessage = "No file chosen";
                new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
            }
        });
    }
}
