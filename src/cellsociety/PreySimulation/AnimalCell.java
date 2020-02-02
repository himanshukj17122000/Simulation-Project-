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
    public void updateCell(GridPane grid, GridEntry entry) {
        move(grid, entry);
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

    protected void move(GridPane grid, GridEntry entry){
       Set<GridEntry> emptyCellSet = setOfEmptyNeighbors(entry);
        int size = emptyCellSet.size();
        int space = new Random().nextInt(size);
        int i = 0;
        for(GridEntry gridSpace : emptyCellSet) {
            if (i == space){
                moveToEmptyGridEntry(grid, entry, gridSpace);
                break;
            }
            i++;
        }
    }

    private void moveToEmptyGridEntry(GridPane grid, GridEntry currentGridSpace, GridEntry newGridSpace){
        Cell newEmptyCell = new EmptyCell(currentGridSpace, PREYFILL);
        grid.add(newEmptyCell.getRectangle(), currentGridSpace.getRow(),currentGridSpace.getColumn()); // setting current space to empty cell
        currentGridSpace.setCell(newEmptyCell);
        grid.add(this.getRectangle(), newGridSpace.getRow(), newGridSpace.getColumn()); //setting empty space to instance of current cell
        newGridSpace.setCell(this);
    }

    private void reproduceInEmptyGridEntry(GridPane grid, GridEntry newGridSpace, AnimalCell offSpring){
        grid.add(offSpring.getRectangle(), newGridSpace.getRow(),newGridSpace.getColumn()); // setting current space to empty cell
        newGridSpace.setCell(offSpring);
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

    protected void reproduce(GridPane grid, GridEntry entry){
        boolean reproduced = false;
        if(getTimeSinceReproduction() == getReproductionTime()){
            Set<GridEntry> emptyCellSet = setOfEmptyNeighbors(entry);
            int size = emptyCellSet.size();
            int space = new Random().nextInt(size);
            int i = 0;
            for(GridEntry gridSpace : emptyCellSet) {
                if (i == space){
                    reproduceInEmptyGridEntry(grid, gridSpace, offSpring(gridSpace));
                    reproduced = true;
                    break;
                }
                i++;
            }
        }
        if(reproduced){
            setTimeSinceReproduction(0);
        }else{
            setTimeSinceReproduction(getTimeSinceReproduction()+1);
        }
    }

}
