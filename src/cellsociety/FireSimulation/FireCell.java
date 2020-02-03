package cellsociety.FireSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Set;

public class FireCell extends Cell {
    private static final int TYPE = 3;
    private static final Paint FILL = Color.TOMATO;
    private static final boolean CANUPDATE = false;

    public FireCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells) {
        entry.setNextStepCell(this);
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public int getRace() {
        return 0;
    }

}
