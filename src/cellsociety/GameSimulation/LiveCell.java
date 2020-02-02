package cellsociety.GameSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Set;

public class LiveCell extends Cell {
    private static final int TYPE = 2;
    private static final Paint FILL = Color.ORCHID;
    private static final boolean CANUPDATE = true;
    private int minLiveCellsNeeded = 2;
    private int maxLiveCellsNeeded = 3;

    public LiveCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public void updateCell(GridPane grid, GridEntry entry) {
        Boolean enoughNeighbors = checkNeighbors(entry);
        if(enoughNeighbors){
            Cell deadCell = new DeadCell(entry);
            grid.add(null, entry.getRow(), entry.getColumn());
            grid.add(deadCell.getRectangle(), entry.getRow(), entry.getColumn());
            entry.setCell(deadCell);
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
        if(numLiveCellNeighbors >= minLiveCellsNeeded || numLiveCellNeighbors <= maxLiveCellsNeeded){
            return true;
        }
        return false;
    }
}
