package cellsociety;

import cellsociety.Visualization.Splash;
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
    public static final int FRAME_DELAY_MILLISECONDS = 15;

    // Initialize what will be displayed and how it will be updated.
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setTitle(TITLE);
        Splash simulation = new Splash(primaryStage);
        primaryStage.setScene(simulation.getSplashScene());
        primaryStage.show();

    }

    public static void main (String[] args) {
        launch(args);
    }

}