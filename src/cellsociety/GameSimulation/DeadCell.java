package cellsociety.GameSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Set;

/**
 * Dead cell object for the game of life simulation
 * @author Olga Suchankova
 */
public class DeadCell extends Cell {
    private static final int TYPE = 1;
    private static final String LABEL = "Dead";
    private static final Paint FILL = Color.MIDNIGHTBLUE;
    private static final boolean CANUPDATE = true;
    private int LiveCellsNeeded = 3;

    //constructor for the deadcell class
    public DeadCell(GridEntry entry) {
        super(FILL, entry);
    }

    //update method for deadcell class, updates everything for next simulation step
    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
        Boolean enoughNeighbors = checkNeighbors(entry);
        if(enoughNeighbors){
            Cell liveCell = new LiveCell(entry);
            emptyCells.remove(entry);
            entry.setNextStepCell(liveCell);
        }else{
            entry.setNextStepCell(this);
        }
    }

    //get the type of the cell
    @Override
    public int getType() {
        return TYPE;
    }

    //get the race of the cell not used in game of life
    @Override
    public int getRace() {
        return 0;
    }

    //get the name of the cell for chart purposes
    @Override
    public String getLabel() { return LABEL; }

    private Boolean checkNeighbors(GridEntry entry){
        Set<GridEntry> neighborSet = entry.getNeighbors();
        int numLiveCellNeighbors = 0;
        for (GridEntry neighbor : neighborSet) {
            if (neighbor.getCell().getType() == 2) {
                numLiveCellNeighbors++;
            }
        }
        if(numLiveCellNeighbors == LiveCellsNeeded){
            return true;
        }
        return false;
    }
}
