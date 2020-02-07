package cellsociety.FireSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Set;

public class TreeCell extends Cell {
    private static final int TYPE = 2;
    private static final Paint FILL = Color.CHARTREUSE;
    private static final boolean CANUPDATE = true;
    private double catchFireProb = getProbCatch();


    public TreeCell(GridEntry entry) {
        super(FILL, entry);
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public int getRace() {
        return 5;
    }

    public void setCatchProb(Double prob){
        catchFireProb = prob;
    }

    private Boolean checkBurningNeighbor(GridEntry entry) {
        Set<GridEntry> neighborSet = entry.getNeighbors();
        boolean neighborFire = false;
        for (GridEntry neighbor : neighborSet) {
            if (neighbor.getCell().getType() == 3) {
                neighborFire = true;
            }
        }
        return neighborFire;
    }

    public void setBurnProbability(Double probability){
        catchFireProb = probability;
    }

    public Double getBurnProbability(){
        return catchFireProb;
    }

    private Boolean checkCatchFire(GridEntry entry){
        Boolean FireNeighbor = checkBurningNeighbor(entry);
        double random = Math.random();
        if(random < catchFireProb && FireNeighbor){
            return true;
        }
        return false;
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells){
        Boolean catchFire = checkCatchFire(entry);
        if(catchFire){
            Cell fireCell = new FireCell(entry);
            entry.setNextStepCell(fireCell);
        }else{
            entry.setNextStepCell(this);
        }
    }

}