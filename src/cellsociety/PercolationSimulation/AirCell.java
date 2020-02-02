package cellsociety.PercolationSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashSet;
import java.util.Set;

public class AirCell extends Cell {
    private static final int TYPE = 2;
    private static final Paint FILL = Color.DARKGRAY;
    private static final boolean CANUPDATE = true;

    public AirCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public void updateCell(GridPane grid, GridEntry entry) {
        Boolean fillWater = checkWaterNeighbor(entry);
        if(fillWater){
            Cell waterCell = new WaterCell(entry);
            grid.add(null, entry.getRow(), entry.getColumn());
            grid.add(waterCell.getRectangle(), entry.getRow(), entry.getColumn());
            entry.setCell(waterCell);
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

    private Boolean checkWaterNeighbor(GridEntry entry) {
        Set<GridEntry> neighborSet = entry.getNeighbors();
        Boolean neighborWater = false;
        for (GridEntry neighbor : neighborSet) {
            if (neighbor.getCell().getType() == 3) {
                neighborWater = true;
            }
        }
        return neighborWater;
    }

}
