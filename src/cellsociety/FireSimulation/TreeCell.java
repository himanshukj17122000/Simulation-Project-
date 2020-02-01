package cellsociety.FireSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.HashSet;
import java.util.Set;

public class TreeCell extends Cell {
    private static final int TYPE = 2;
    public static final Paint FILL = Color.CHARTREUSE;
    private Boolean CANUPDATE = true;
    private Double catchFireProb = 0.15;


    public TreeCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public int getType() {
        return TYPE;
    }

    private Boolean checkBurningNeighbor(GridEntry entry) {
        Set<GridEntry> neighborSet = getNeighbors(entry);
        Boolean neighborFire = false;
        for (GridEntry neighbor : neighborSet) {
            if (neighbor.getCell().getType() == 3) {
                neighborFire = true;
            }
        }
        return neighborFire;
    }

    public void setBurnProbability(Double probability){
        catchFireProb = probability;
    }

    public Double getBurnProbability(){
        return catchFireProb;
    }

    private Boolean checkCatchFire(GridPane grid, GridEntry entry){
        Boolean FireNeighbor = checkBurningNeighbor(entry);
        double random = Math.random();
        if(random < catchFireProb && FireNeighbor){
            Cell fireCell = new FireCell(entry);
            return true;
        }
        return false;
    }

    @Override
    public void updateCell(GridPane grid, int row, int col, Cell newCell, GridEntry entry){
        Boolean catchFire = checkCatchFire(grid, entry);
        if(catchFire){
            grid.add(null, row, col);
            grid.add(newCell.getRectangle(), row, col);
            entry.setCell(newCell);
        }
    }

}