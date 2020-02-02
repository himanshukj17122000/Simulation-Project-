package cellsociety;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class EmptyCell extends Cell {
    private static final int TYPE = 1;
    private static final boolean CANUPDATE = false;

    public EmptyCell(GridEntry entry, Paint color) {
        super(color, entry);

    }

    @Override
    public void updateCell(GridEntry entry) {
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
