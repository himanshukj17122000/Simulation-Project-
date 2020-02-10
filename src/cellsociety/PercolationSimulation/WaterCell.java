package cellsociety.PercolationSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Set;

/**
 * Water cell class for the percolation simulation
 * @author Olga Suchankova
 */
public class WaterCell extends Cell {
    private static final int TYPE = 3;
    private static final String LABEL = "Water";
    private static final Paint FILL = Color.PALETURQUOISE;
    private static final boolean CANUPDATE = false;

    //constructor for water cell
    public WaterCell(GridEntry entry) {
        super(FILL, entry);
    }

    //water cell update method empty to simply inherit from cell method
    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
    }

    //get type method for water cell
    @Override
    public int getType() {
        return TYPE;
    }

    //get race method, not used for percolation simulation
    @Override
    public int getRace() {
        return 0;
    }

//get the name of the cell
    @Override
    public String getLabel() { return LABEL; }
}
