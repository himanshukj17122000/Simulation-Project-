package cellsociety;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.application.Application;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {
    /**
     * Start of the program.
     */
    public static final String TITLE = "Cell Society";

    private Timeline myTimeline;


    // Initialize what will be displayed and how it will be updated.
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setTitle(TITLE);
        Visualization simulation = new Visualization(primaryStage, myTimeline);
        primaryStage.setScene(simulation.getMySplashScene());
        primaryStage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}