package cellsociety;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashSet;
import java.util.Set;

public class EmptyCell extends Cell {
    private static final String TYPE = "EMPTY";
    public static final Paint FILL = Color.WHITE;
    private Boolean CANUPDATE = true;
    private Set<int[]> NEIGHBORS = new HashSet<int[]>();
    private int[] Location = new int[2];

    public EmptyCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
