package cellsociety.GameSimulation;

import cellsociety.Cell;
import cellsociety.FireSimulation.FireCell;
import cellsociety.GridEntry;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Set;

public class DeadCell extends Cell {
    private static final int TYPE = 1;
    private static final Paint FILL = Color.MIDNIGHTBLUE;
    private static final boolean CANUPDATE = true;
    private int LiveCellsNeeded = 3;

    public DeadCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public void updateCell(GridEntry entry) {
        Boolean enoughNeighbors = checkNeighbors(entry);
        if(enoughNeighbors){
            Cell liveCell = new LiveCell(entry);
            entry.setNextStepCell(liveCell);
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

    private Boolean checkNeighbors(GridEntry entry){
        Set<GridEntry> neighborSet = entry.getNeighbors();
        int numLiveCellNeighbors = 0;
        for (GridEntry neighbor : neighborSet) {
            if (neighbor.getCell().getType() == 3) {
                numLiveCellNeighbors++;
            }
        }
        if(numLiveCellNeighbors == LiveCellsNeeded){
            return true;
        }
        return false;
    }
}
