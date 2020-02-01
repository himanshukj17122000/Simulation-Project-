package cellsociety.PercolationSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashSet;
import java.util.Set;

public class WaterCell extends Cell {
    private static final int TYPE = 1;
    public static final Paint FILL = Color.PALETURQUOISE;
    private Boolean CANUPDATE = false;
    private Set<int[]> NEIGHBORS = new HashSet<int[]>();
    private int[] Location = new int[2];

    public WaterCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
