package cellsociety.PreySimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PreyCell extends Cell {
    private static final int TYPE = 2;
    private static final Paint FILL = Color.TAN;
    private int timeStepsToReproduce = 5;
    private int timeStepsSinceReproduce;
    private static final boolean CANUPDATE = true;


    public PreyCell(GridEntry entry) {
        super(FILL, entry);
        setTimeStepsSinceReproduce(0);
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

    private void setTimeStepsSinceReproduce(int time){
        timeStepsSinceReproduce = time;
    }

    private int getTimeStepsSinceReproduce(){
        return timeStepsSinceReproduce;
    }

    private void move(GridPane grid, GridEntry entry){
       Set<GridEntry> emptyCellSet = setOfEmptyNeighbors();
        int size = emptyCellSet.size();
        int space = new Random().nextInt(size);
        int i = 0;
        for(GridEntry gridSpace : emptyCellSet) {
            if (i == space){
                Cell newEmptyCell = new EmptyCell(entry);
                grid.add(newEmptyCell.getRectangle(), entry.getRow(),entry.getColumn()); // setting current space to empty cell
                entry.setCell(newEmptyCell);

                grid.add(this.getRectangle(), gridSpace.getRow(), gridSpace.getColumn()); //setting empty space to instance of current cell
                gridSpace.setCell(this);

                break;
            }
            i++;
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

    private void reproduce(GridPane grid, GridEntry entry){
        if(getTimeStepsSinceReproduce() == timeStepsToReproduce){
            Set<GridEntry> emptyCellSet = setOfEmptyNeighbors();
            int size = emptyCellSet.size();
            int space = new Random().nextInt(size);
            int i = 0;
            for(GridEntry gridSpace : emptyCellSet) {
                if (i == space){
                    Cell newPreyCell = new PreyCell(entry);
                    grid.add(newPreyCell.getRectangle(), entry.getRow(),entry.getColumn()); // setting current space to empty cell
                    entry.setCell(newPreyCell);

                    grid.add(this.getRectangle(), gridSpace.getRow(), gridSpace.getColumn()); //setting empty space to instance of current cell
                    gridSpace.setCell(this);

                    break;
                }
                i++;
            }
            if(size > 0){
                setTimeStepsSinceReproduce(0);
            }else{
                setTimeStepsSinceReproduce(getTimeStepsSinceReproduce()+1);
            }

        }else{
            setTimeStepsSinceReproduce(getTimeStepsSinceReproduce()+1);
        }
    }

}
