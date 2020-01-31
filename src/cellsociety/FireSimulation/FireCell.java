package cellsociety.FireSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class FireCell extends Cell {
    private static final int TYPE = 3;
    public static final Paint FILL = Color.TOMATO;
    private Boolean CANUPDATE = false;
    private int[] Location = new int[2];

    public FireCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public int getType() {
        return TYPE;
    }



}
