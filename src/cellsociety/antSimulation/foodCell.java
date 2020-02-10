package cellsociety.antSimulation;


import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Set;

public class foodCell extends Cell {
    private static final Paint FILL = Color.web("#F5C326");
    private int foodAmount = 10;
    private static final int TYPE = 2;
    private int antCellType = 5;
    private int pCellType = 4;
    private int nestCellType = 3;
    private int foodCellType = 2;
    private int emptyCellType = 1;

    public foodCell(Paint color, GridEntry entry, int foodAmount) {
        super(FILL, entry);
        setFoodAmount(foodAmount);
    }

    private void setFoodAmount(int amount){
        foodAmount = amount;
    }

    private int getFoodAmount(){
        return foodAmount;
    }


    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
        List<Cell> cells = entry.getCellList();
        for(Cell curCell : cells){
            if(curCell.getType() == antCellType){
                if(curCell.getRace() < 1){
                    setFoodAmount(getFoodAmount()-1);
                }
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
        return foodAmount;
    }

    @Override
    public String getLabel() {
        return null;
    }
}
