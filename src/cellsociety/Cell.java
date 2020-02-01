package cellsociety;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import java.util.HashSet;
import java.util.Set;

public abstract class Cell {
    private Rectangle CELLVISUAL;
    private Set<GridEntry> NEIGHBORS= new HashSet<GridEntry>();
    private int[] Location = new int[2];
    private double standardWidth;
    private double standardHeight;

    public Cell(Paint color, GridEntry entry){
        CELLVISUAL = new Rectangle(standardWidth, standardHeight);
        setColor(color);
        setWidth(standardWidth);
        setHeight(standardHeight);
        setLocation(entry);
        setNeighbors(entry);
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

    public void updateCell(GridPane grid, int row, int col, Cell newCell, GridEntry entry) {
        grid.add(null, row, col);
        grid.add(newCell.getRectangle(), row, col);
        entry.setCell(newCell);
    }

    public abstract int getType();

    public void setNeighbors(GridEntry entry) {
        NEIGHBORS = entry.getNeighbors();
    }

    public Set<GridEntry> getNeighbors(GridEntry entry) {
        return NEIGHBORS;
    }

    private void setLocation(GridEntry entry){
        Location = entry.getID();
    }

    public int[] getLocation(){
        return Location;
    }


}
