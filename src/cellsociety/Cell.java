package cellsociety;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Set;

public abstract class Cell { // potentially implement a check conditions method to override
    //also make a hashmap of cell types
    //potentially implement a super updateCell method
    private Rectangle CELLVISUAL;
    private int Row;
    private int Column;
    private double standardWidth = 25; //need to assign / finalzie
    private double standardHeight = 25;

    public Cell(Paint color, GridEntry entry){
        CELLVISUAL = new Rectangle(standardWidth, standardHeight);
        setColor(color);
        setWidth(standardWidth);
        setHeight(standardHeight);
        setLocation(entry);
    }
    public Rectangle getRectangle(){
        return CELLVISUAL;
    }
    public void setColor(Paint newColor){
        CELLVISUAL.setFill(newColor);
    }
    public double getWidth() {
        return CELLVISUAL.getWidth();
    }
    public double getHeight() {
        return CELLVISUAL.getHeight();
    }
    public void setWidth(double newWidth) {
        CELLVISUAL.setWidth(newWidth);
    }
    public void setHeight(double newHeight) {
        CELLVISUAL.setHeight(newHeight);
    }

    public abstract void updateCell(GridEntry entry, Set<GridEntry> emptyCells);

    public abstract int getType();

    private void setLocation(GridEntry entry){
        Row = entry.getRow();
        Column = entry.getColumn();
    }

    public abstract int getRace();


}
