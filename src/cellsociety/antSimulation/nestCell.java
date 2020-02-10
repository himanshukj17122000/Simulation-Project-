package cellsociety.antSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Set;

public class nestCell extends Cell {

    public nestCell(Paint color, GridEntry entry) {
        super(color, entry);
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {

    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getRace() {
        return 0;
    }

    @Override
    public String getLabel() {
        return null;
    }
}
