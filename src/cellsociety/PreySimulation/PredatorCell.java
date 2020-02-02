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

public class PredatorCell extends AnimalCell { // make animal superclass // contains long classes plz refactor
    private static final int TYPE = 3;
    private static final Paint FILL = Color.web("#614A32");
    private static final Paint PREYFILL = Color.PALEGREEN;
    private static final boolean CANUPDATE = true;
    private int ReproductionTime = 5;
    private int timeSinceReproduction;
    private int maxTimeWithoutEating = 5;
    private int timeSinceEating;

    public PredatorCell(GridEntry entry) {
        super(entry);
        this.setColor(FILL);
        setTimeSinceEating(0);
        setTimeStepsSinceReproduce(0);
    }

    @Override
    public void updateCell(GridPane grid,  GridEntry entry) {
        if(getTimeSinceEating() > maxTimeWithoutEating){
            die(grid, entry);
        }else{
            move(grid, entry);
            super.reproduce(grid, entry);
        }
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public int getRace() {
        return 0;
    }

    private void setTimeStepsSinceReproduce(int time){
        timeSinceReproduction = time;
    }

    private int getTimeSinceReproduction(){
        return timeSinceReproduction;
    }

    private void setTimeSinceEating(int time){
        timeSinceEating = time;
    }

    private int getTimeSinceEating(){
        return timeSinceEating;
    }

    @Override
    protected AnimalCell offSpring(GridEntry entry){
        return new PredatorCell(entry);
    }

    @Override
    protected void move(GridPane grid, GridEntry entry) {
        Set<GridEntry> preyCellSet = setOfPreyNeighbors(entry);
        int size = preyCellSet.size();
        int space = new Random().nextInt(size);
        int i = 0;
        boolean ate = false;
        for (GridEntry gridSpace : preyCellSet) {
            if (i == space) {
                eatAnimal(grid, entry, gridSpace);
                ate = true;
                break;
            }
            i++;
        }
        if (ate) {
            setTimeSinceEating(0);
        } else {
            super.move(grid, entry);
            setTimeSinceEating(getTimeSinceEating() + 1);
        }
    }

    private void eatAnimal(GridPane grid, GridEntry currentGridSpace, GridEntry newGridSpace){
        Cell newEmptyCell = new EmptyCell(currentGridSpace, PREYFILL);
        grid.add(newEmptyCell.getRectangle(), currentGridSpace.getRow(), currentGridSpace.getColumn()); // setting current space to empty cell
        currentGridSpace.setCell(newEmptyCell);
        grid.add(this.getRectangle(), newGridSpace.getRow(), newGridSpace.getColumn()); //setting prey space to instance of current cell
        newGridSpace.setCell(this);
    }

    private Set<GridEntry> setOfPreyNeighbors(GridEntry entry){
        Set<GridEntry> neighborSet = entry.getNeighbors();
        Set<GridEntry> preyCellSet = new HashSet<GridEntry>();
        for (GridEntry neighbor : neighborSet) {
            if (neighbor.getCell().getType() == 2) {
                preyCellSet.add(neighbor);
            }
        }
        return preyCellSet;
    }

    private void die(GridPane grid, GridEntry entry) {
        Cell newEmptyCell = new EmptyCell(entry, PREYFILL);
        grid.add(newEmptyCell.getRectangle(), entry.getRow(), entry.getColumn());
    }


}
