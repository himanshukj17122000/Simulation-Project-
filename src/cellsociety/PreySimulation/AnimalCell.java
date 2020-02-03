package cellsociety.PreySimulation;

import cellsociety.Cell;
import cellsociety.EmptyCell;
import cellsociety.GridEntry;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AnimalCell extends Cell {
    private static final int TYPE = 2;
    private static final Paint FILL = Color.TAN;
    protected static final Paint PREYFILL = Color.PALEGREEN;
    private int reproductionTime = 5;
    private int timeSinceReproduction;
    private static final boolean CANUPDATE = true;


    public AnimalCell(GridEntry entry) {
        super(FILL, entry);
        setTimeSinceReproduction(0);
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells) {
        move(entry, emptyCells);
        reproduce(entry, emptyCells);
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public int getRace() {
        return 0;
    }

    protected void setTimeSinceReproduction(int time){
        this.timeSinceReproduction = time;
    }

    protected int getTimeSinceReproduction(){
        return this.timeSinceReproduction;
    }

    protected void setReproductionTime(int time){
        reproductionTime = time;
    }

    protected int getReproductionTime(){ return reproductionTime; }

    protected void move(GridEntry entry, Set<GridEntry> emptyCells){
       Set<GridEntry> emptyCellSet = setOfEmptyNeighbors(entry);
        boolean moved = false;
        int size = emptyCellSet.size();
        if(size > 0){
        int space = new Random().nextInt(size);
        int i = 0;

        for(GridEntry gridSpace : emptyCellSet) {
            if (i == space){
                moveToEmptyGridEntry(entry, gridSpace);
                emptyCells.add(entry);
                emptyCells.remove(space);
                moved = true;
                break;
            }
            i++;
        }}
        if(!moved){
            entry.setNextStepCell(this);
        }
    }

    private void moveToEmptyGridEntry(GridEntry currentGridSpace, GridEntry newGridSpace){
        Cell newEmptyCell = new EmptyCell(currentGridSpace, PREYFILL); // setting current space to empty cell
        currentGridSpace.setNextStepCell(newEmptyCell); //setting empty space to instance of current cell
        newGridSpace.setNextStepCell(this);
    }

    private void reproduceInEmptyGridEntry(GridEntry newGridSpace, AnimalCell offSpring){ // setting current space to empty cell
        newGridSpace.setNextStepCell(offSpring);
    }

    protected AnimalCell offSpring(GridEntry entry){
        return new AnimalCell(entry);
    }

    private Set<GridEntry> setOfEmptyNeighbors(GridEntry entry){
        Set<GridEntry> neighborSet = entry.getNeighbors();
        Set<GridEntry> emptyCellSet = new HashSet<GridEntry>();
        for (GridEntry neighbor : neighborSet) {
            if (!neighbor.getIsOccupied()) {
                emptyCellSet.add(neighbor);
            }
        }
        return emptyCellSet;
    }

    protected void reproduce(GridEntry entry, Set<GridEntry> emptyCells){
        boolean reproduced = false;
        if(getTimeSinceReproduction() == getReproductionTime()){
            Set<GridEntry> emptyCellSet = setOfEmptyNeighbors(entry);
            int size = emptyCellSet.size();
            if(size>0){
            int space = new Random().nextInt(size);
            int i = 0;
            for(GridEntry gridSpace : emptyCellSet) {
                if (i == space){
                    reproduceInEmptyGridEntry(gridSpace, offSpring(gridSpace));
                    emptyCells.remove(space);
                    reproduced = true;
                    break;
                }
                i++;
            }
        }}
        if(reproduced){
            setTimeSinceReproduction(0);
        }else{
            setTimeSinceReproduction(getTimeSinceReproduction()+1);
        }
    }

}
