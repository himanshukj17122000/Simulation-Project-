package cellsociety;

import javafx.scene.paint.Paint;

import java.util.Set;

public class EmptyCell extends Cell {
    private static final int TYPE = 1;
    private boolean CANUPDATE = false;

    public EmptyCell(GridEntry entry, Paint color) {
        super(color, entry);

    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, double[] parameters) {
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
