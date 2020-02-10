package cellsociety.FireSimulation;

import cellsociety.Cell;
import cellsociety.EmptyCell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Set;

public class FireCell extends Cell {
    private static final int TYPE = 3;
    private static final String LABEL = "Burning Trees";
    private static final Paint FILL = Color.web("db4e43");
    private static final boolean CANUPDATE = false;

    public FireCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
        Cell burntCell= new EmptyCell(entry, Color.WHITE);
        entry.setNextStepCell(burntCell);
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
}
