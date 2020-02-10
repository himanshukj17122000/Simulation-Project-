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
    private static final int TYPE = 1;
    private int antCellType = 4;
    private int pCellType = 1;
    private int nestCellType = 3;
    private int foodCellType = 2;


    public pCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
        if(getPheromones()> 0){
            setPheromones(getPheromones()-1);
        }
        entry.setNextStepCell(this);
        List<Cell> cells = entry.getCellList();
        for(Cell curCell : cells){
            if(curCell.getType() == antCellType){
                if(curCell.getRace() < 1){
                    setPheromones(getPheromones()+2);
                }else{
                    setPheromones(getPheromones()+3);
                }
            }
        }
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
