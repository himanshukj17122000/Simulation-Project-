package cellsociety.antSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Set;

public class nestCell extends Cell {
    private static final Paint FILL = Color.web("#400A55");
    private static final int TYPE = 3;
    private int antCellType = 4;
    private int pCellType = 1;
    private int nestCellType = 3;
    private int foodCellType = 2;
    private int foodAmount = 0;

    public nestCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
        List<Cell> cells = entry.getCellList();
        if(cells.size()>0){
        for(Cell curCell : cells){
            if(curCell.getType() == antCellType){
                if(curCell.getRace() > 0){
                    foodAmount = foodAmount + 1;
                }
            }
        }}
        entry.setNextStepCell(this);
        Cell spawnAnt = new antCell(entry);
        entry.setNextStepCell(spawnAnt);
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public int getRace() {
        return foodAmount;
    }

    @Override
    public String getLabel() {
        return null;
    }
}
