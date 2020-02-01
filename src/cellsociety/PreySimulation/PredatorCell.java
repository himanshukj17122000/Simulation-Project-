package cellsociety.PreySimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PredatorCell extends Cell { // make animal superclass // contains long classes plz refactor
    private static final int TYPE = 3;
    private static final Paint FILL = Color.web("#614A32");
    private static final boolean CANUPDATE = true;
    private int timeStepsToReproduce = 5;
    private int timeStepsSinceReproduce;
    private int timeStepsSurviveWithoutFood = 5;
    private int timeStepsSinceFood;

    public PredatorCell(GridEntry entry) {
        super(FILL, entry);
        setTimeStepsSinceFood(0);
        setTimeStepsSinceReproduce(0);
    }

    @Override
    public void updateCell(GridPane grid,  GridEntry entry) {
        if(getTimeStepsSinceFood() > timeStepsSurviveWithoutFood){
            die(grid, entry);
        }else{
            move(grid, entry);
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
        timeStepsSinceReproduce = time;
    }

    private int getTimeStepsSinceReproduce(){
        return timeStepsSinceReproduce;
    }

    private void setTimeStepsSinceFood(int time){
        timeStepsSinceFood = time;
    }

    private int getTimeStepsSinceFood(){
        return timeStepsSinceFood;
    }

    private void move(GridPane grid, GridEntry entry) {
        Set<GridEntry> preyCellSet = setOfPreyNeighbors();
        int size = preyCellSet.size();
        int space = new Random().nextInt(size);
        int i = 0;
        boolean moved = false;
        for (GridEntry gridSpace : preyCellSet) {
            if (i == space) {
                Cell newEmptyCell = new EmptyCell(entry);
                grid.add(newEmptyCell.getRectangle(), entry.getRow(), entry.getColumn()); // setting current space to empty cell
                entry.setCell(newEmptyCell);
                grid.add(this.getRectangle(), gridSpace.getRow(), gridSpace.getColumn()); //setting prey space to instance of current cell
                gridSpace.setCell(this);
                moved = true;
                break;
            }
            i++;
        }
        if (!moved) {
            Set<GridEntry> emptyCellSet = setOfEmptyNeighbors();
            size = emptyCellSet.size();
            space = new Random().nextInt(size);
            i = 0;
            for (GridEntry gridSpace : emptyCellSet) {
                if (i == space) {
                    Cell newEmptyCell = new EmptyCell(entry);
                    grid.add(newEmptyCell.getRectangle(), entry.getRow(), entry.getColumn()); // setting current space to empty cell
                    entry.setCell(newEmptyCell);
                    grid.add(this.getRectangle(), gridSpace.getRow(), gridSpace.getColumn()); //setting empty space to instance of current cell
                    gridSpace.setCell(this);
                    break;
                }
                i++;
            }
        }
        if (moved) {
            setTimeStepsSinceFood(0);
        } else {
            setTimeStepsSinceFood(getTimeStepsSinceFood() + 1);
        }
    }

    private Set<GridEntry> setOfEmptyNeighbors(){
        Set<GridEntry> neighborSet = getNeighbors();
        Set<GridEntry> emptyCellSet = new HashSet<GridEntry>();
        for (GridEntry neighbor : neighborSet) {
            if (neighbor.getCell().getType() == 1) {
                emptyCellSet.add(neighbor);
            }
        }
        return emptyCellSet;
    }

    private Set<GridEntry> setOfPreyNeighbors(){
        Set<GridEntry> neighborSet = getNeighbors();
        Set<GridEntry> preyCellSet = new HashSet<GridEntry>();
        for (GridEntry neighbor : neighborSet) {
            if (neighbor.getCell().getType() == 1) {
                preyCellSet.add(neighbor);
            }
        }
        return preyCellSet;
    }

    private void reproduce(GridPane grid, GridEntry entry) {
        boolean reproduced = false;
        if (getTimeStepsSinceReproduce() == timeStepsToReproduce) {
            Set<GridEntry> emptyCellSet = setOfEmptyNeighbors();
            int size = emptyCellSet.size();
            int space = new Random().nextInt(size);
            int i = 0;
            for (GridEntry gridSpace : emptyCellSet) {
                if (i == space) {
                    Cell newPredatorCell = new PredatorCell(entry);
                    grid.add(newPredatorCell.getRectangle(), entry.getRow(), entry.getColumn()); // setting current space to empty cell
                    entry.setCell(newPredatorCell);

                    grid.add(this.getRectangle(), gridSpace.getRow(), gridSpace.getColumn()); //setting empty space to instance of current cell
                    gridSpace.setCell(this);
                    reproduced = true;
                    break;
                }
                i++;
            }
        }
        if (!reproduced) {
            setTimeStepsSinceReproduce(0);
        } else {
            setTimeStepsSinceReproduce(getTimeStepsSinceReproduce()+1);
        }
    }

    private void die(GridPane grid, GridEntry entry) {
        Cell newEmptyCell = new EmptyCell(entry);
        grid.add(newEmptyCell.getRectangle(), entry.getRow(), entry.getColumn());
    }


}
