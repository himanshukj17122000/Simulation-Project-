package cellsociety;

import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Set;

public class EmptyCell extends Cell {
    private static final int TYPE = 1;

    //constructor for the empty cell
    public EmptyCell(GridEntry entry, Paint color) {
        super(color, entry);

    }

    //empty constructor for empty cell
    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
    }

    //get the type of cell
    @Override
    public int getType() {
        return TYPE;
    }

    //get any information stored in the cell
    @Override
    public int getRace() {
        return 0;
    }

    //get the display name of the cell
    @Override
    public String getLabel() { return null; }
}
