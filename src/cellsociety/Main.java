package cellsociety;

import cellsociety.Configuration.Fire;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.util.Duration;

import java.util.List;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {
    /**
     * Start of the program.
     */
    public static final String TITLE = "Cell Society";
    public static final int FRAME_DELAY_MILLISECONDS = 15;

    private Timeline myTimeline;

    // Initialize what will be displayed and how it will be updated.
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setTitle(TITLE);
        Splash simulation = new Splash(primaryStage, myTimeline);
        primaryStage.setScene(simulation.getMySplashScene());
        primaryStage.show();

    }

    public static void main (String[] args) {
        launch(args);
    }

}