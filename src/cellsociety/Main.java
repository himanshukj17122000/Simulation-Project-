package cellsociety;

import cellsociety.Visualization.Splash;
import javafx.stage.Stage;

import javafx.application.Application;

/**
 * Class for Main that is the main class of the Simulation project and is in charge of launching the program.
 */
public class Main extends Application {
    /**
     * Start of the program.
     */
    public static final String TITLE = "Cell Society";

    /*
     Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle(TITLE);
        Splash simulation = new Splash(primaryStage);
        primaryStage.setScene(simulation.getSplashScene());
        primaryStage.show();
    }

    /*
    Main function that launches program
     */
    public static void main (String[] args) {
        launch(args);
    }

}