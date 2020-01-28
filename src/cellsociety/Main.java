package cellsociety;

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

    // Initialize what will be displayed and how it will be updated.
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setTitle(TITLE);
        Scene animationScene = Visualization.buildAnimationScene(primaryStage);

    }

    public static void main (String[] args) {
        launch(args);
    }
}