package cellsociety;

import javafx.scene.paint.Paint;

public class Cell {
    private int myState;
    private Paint myColor;

    public Cell(int state, Paint color) {
        this.myState = state;
        this.myColor = color;
    }

    public int getMyState() {
        return myState;
    }

    public Paint getMyColor() {
        return myColor;
    }
}
