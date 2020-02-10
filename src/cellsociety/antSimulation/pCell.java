package cellsociety.antSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Set;

public class pCell extends Cell {
    private static final Paint FILL = Color.web("#E890EC");
    private int pheromones = 0;
    private static final int TYPE = 4;
    private int antCellType = 5;
    private int pCellType = 4;
    private int nestCellType = 3;
    private int foodCellType = 2;
    private int emptyCellType = 1;


    public pCell( GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
        setPheromones(getPheromones()-1);
        entry.setNextStepCell(this);
    }

    private void setPheromones(int newVal){
        pheromones = newVal;
    }

    private void dropPheromones(GridEntry entry){
        List<Cell> cells = entry.getCellList();
        for(Cell curCell : cells){
            if(curCell.getType() == antCellType){

            }
        }
        entry.setNextStepCell(this);
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public int getRace() {
        return pheromones;
    }

    public int getPheromones(){
        return pheromones;
    }

    @Override
    public String getLabel() {
        return null;
    }
}
