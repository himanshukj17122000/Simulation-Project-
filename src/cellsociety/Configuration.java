package cellsociety;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class Configuration {
    private static final int SIZE=400;
    private static int cellHeight;
    private static int cellWidth;
    private static XMLReader reader;
    private static int numRows;
    private static int numColumns;

    public Configuration(){
        String numr= reader.getRows();
        String numc= reader.getColumns();
        numRows= Integer.parseInt(numr);
        numColumns=Integer.parseInt(numc);
    }

    public void setUp(){
        cellHeight= SIZE/numRows;
        cellWidth=SIZE/numColumns;
        GridPane gridPane = new GridPane();
        initialise(gridPane);
    }

    public GridPane initialise(GridPane grid){
        
        return null;
    }



}

