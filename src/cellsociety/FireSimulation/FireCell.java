package cellsociety.FireSimulation;

import cellsociety.Cell;
import cellsociety.EmptyCell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Set;

/**
 * fire cell for the fire simulation
 * @author Olga Suchankova
 */
public class FireCell extends Cell {
    private static final int TYPE = 3;
    private static final String LABEL = "Burning Trees";
    private static final Paint FILL = Color.web("db4e43");
    private static final boolean CANUPDATE = false;

    //constructor for the firecell
    public FireCell(GridEntry entry) {
        super(FILL, entry);
    }

    //update cell method for the fire cell
    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
        Cell burntCell= new EmptyCell(entry, Color.WHITE);
        entry.setNextStepCell(burntCell);
    }

    //get the type of the cell
    @Override
    public int getType() {
        return TYPE;
    }
    //get the race of the cell (not used)
    @Override
    public int getRace() {
        return 0;
    }

    //get the name of the cell for chart purposes
    @Override
    public String getLabel() { return LABEL; }
}
