package cellsociety.GameSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Set;

public class LiveCell extends Cell {
    private static final int TYPE = 2;
    private static final String LABEL = "Alive";
    private static final Paint FILL = Color.ORCHID;
    private static final boolean CANUPDATE = true;
    private int minLiveCellsNeeded = 2;
    private int maxLiveCellsNeeded = 3;

    public LiveCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
        Boolean enoughNeighbors = checkNeighbors(entry);
        if(enoughNeighbors){
            entry.setNextStepCell(this);
        }else{
            Cell deadCell = new DeadCell(entry);
            emptyCells.add(entry);
            entry.setNextStepCell(deadCell);
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
        if(numLiveCellNeighbors >= minLiveCellsNeeded && numLiveCellNeighbors <= maxLiveCellsNeeded){
            return true;
        }
        return false;
    }
}
