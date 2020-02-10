package cellsociety.antSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class antCell extends Cell {
    private static final Paint FILL = Color.web("#302A24");
    private static final int numDirections = 2;
    private int[] direction = {1, 1}; // row, col
    private static final int TYPE = 4;
    private int antCellType = 5;
    private int pCellType = 4;
    private int nestCellType = 3;
    private int foodCellType = 2;
    private int emptyCellType = 1;
    private int carryingFood = 1;

    public antCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
        move(entry);
    }

    private void switchDirection(){
        if(direction[0] == 0){
            direction[0] = 1;
        }else{
            direction[0] = 0;
        }
        if(direction[1] == 0){
            direction[1] = 1;
        }else{
            direction[1] = 0;
        }
    }

    private void move(GridEntry entry){
        Set<GridEntry> directionNeighbors = getNeighbors(entry);
        GridEntry moveTo = entry;
        int maxP = 0;
        for(GridEntry neighbor : directionNeighbors){
            List<Cell> containedCells = neighbor.getCellList();
            for(int i = 0; i < containedCells.size(); i++){
                Cell curCell = containedCells.get(i);
                if(curCell.getType() == pCellType && curCell.getRace()> maxP){
                    moveTo = neighbor;
                }
                if(curCell.getType() == foodCellType && curCell.getRace() > 0 && carryingFood == 0){
                    neighbor.setNextStepCell(this);
                    carryingFood = 1;
                    switchDirection();
                    maxP = 1;
                    break;
                }
            }if(neighbor.getRow() == 0 && neighbor.getColumn() == 0){
                carryingFood = 0;
                switchDirection();
            }
        }
        if(maxP == 0){
            int space = new Random().nextInt(directionNeighbors.size());
            int i = 0;
            for (GridEntry neighbor : directionNeighbors) {
                if (i == space) {
                    moveTo = neighbor;
                    break;
                }
                i++;
            }
        }
        moveTo.setNextStepCell(this);
    }


    private Set<GridEntry> getNeighbors(GridEntry entry){
        Set<GridEntry> neighbors = entry.getNeighbors();
        Set<GridEntry> retNeigh = new HashSet<GridEntry>();
        for(GridEntry adjacent : neighbors){
            if(adjacent.getRow() < entry.getRow() && adjacent.getColumn() < entry.getColumn() && direction[0] == 0 && direction[1] == 0){
                retNeigh.add(adjacent);
            }
            if(adjacent.getRow() > entry.getRow() && adjacent.getColumn() < entry.getColumn() && direction[0] == 1 && direction[1] == 0){
                retNeigh.add(adjacent);
            }
            if(adjacent.getRow() < entry.getRow() && adjacent.getColumn() > entry.getColumn() && direction[0] == 0 && direction[1] == 1){
                retNeigh.add(adjacent);
            }
            if(adjacent.getRow() > entry.getRow() && adjacent.getColumn() > entry.getColumn() && direction[0] == 1 && direction[1] == 1){
                retNeigh.add(adjacent);
            }
        }
        return retNeigh;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public int getRace() {
        return carryingFood;
    }

    @Override
    public String getLabel() {
        return null;
    }
}
