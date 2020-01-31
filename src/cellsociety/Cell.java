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
    private Set<int[]> NEIGHBORS= new HashSet<int[]>();
    private double standardWidth;
    private double standardHeight;

    public Cell(Paint color, GridEntry entry){
        CELLVISUAL = new Rectangle(standardWidth, standardHeight);
        setColor(color);
        setWidth(standardWidth);
        setHeight(standardHeight);
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

    public void updateCell(GridPane grid, int row, int col, Cell newCell) {
        grid.add(null, row, col);
        grid.add(newCell.getRectangle(), row, col);
    }

    public abstract String getType();

    public void setNeighbors(GridEntry entry) {
        NEIGHBORS = entry.getNeighbors();
    }

    public Set<int[]> getNeighbors(GridEntry entry) {
        return NEIGHBORS;
    }

}
