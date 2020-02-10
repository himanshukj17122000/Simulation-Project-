package cellsociety;

import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Set;

public abstract class Cell { // potentially implement a check conditions method to override
    //also make a hashmap of cell types
    //potentially implement a super updateCell method

    private Paint myColor;

    //constructor for cell class, sets the location
    public Cell(Paint color, GridEntry entry){
        setColor(color);
    }

    //getter and setter for the color of the cells
    public void setColor(Paint newColor){
        myColor = newColor;
    }
    public Paint getColor(){return myColor;
    }

//abstract method to update the cell for next simulation step
    public abstract void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters);

    //get type method to get cell type
    public abstract int getType();
//get race method to get information about the cell
    public abstract int getRace();
//get label method to get the string name of the cell
    public abstract String getLabel();
}
