package cellsociety.FireSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashSet;
import java.util.Set;

public class EmptyCell extends Cell {
    private static final int TYPE = 1;
    private static final Paint FILL = Color.WHITE;
    private static final boolean CANUPDATE = false;

    public EmptyCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public void updateCell(GridPane grid, GridEntry entry) {

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
