package cellsociety;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import java.util.HashSet;
import java.util.Set;

public class Cell {
    private Rectangle CELLVISUAL;


    public Cell(double startX, double startY, int width, int height, Paint color){
        CELLVISUAL = new Rectangle(startX, startY, width, height);
        setColor(color);
        setX(startX);
        setY(startY);
        setWidth(width);
        setHeight(height);
    }
    public Rectangle getRectangle(){
        return CELLVISUAL;
    }
    public void setColor(Paint newColor){
        CELLVISUAL.setFill(newColor);
    }
    public double getX(){
        return CELLVISUAL.getX();
    }
    public double getY(){
        return CELLVISUAL.getY();
    }
    public void setX(double newX){
        CELLVISUAL.setX(newX);
    }
    public void setY(double newY){
        CELLVISUAL.setY(newY);
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

    public void updateCell(Group group, GridPane grid, int row, int col, Cell newCell) {
        grid.add(null, row, col);
        grid.add(newCell.getRectangle(), row, col);
    }



}
