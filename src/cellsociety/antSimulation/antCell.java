package cellsociety.antSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class antCell extends Cell {
    private static final int numDirections = 2;

    public antCell(Paint color, GridEntry entry) {
        super(color, entry);
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {

    }

    private void move(GridEntry entry){
        int lOrR = new Random().nextInt(numDirections);
        if(lOrR == 0){
            entry.setNextStepCell(this);
        }
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getRace() {
        return 0;
    }

    @Override
    public String getLabel() {
        return null;
    }
}
