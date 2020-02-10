package cellsociety.FireSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Set;

/**
 * tree cell for fire simulation
 * @author Olga Suchankova
 */
public class TreeCell extends Cell {
    private static final int TYPE = 2;
    private static final String LABEL = "Trees";
    private static final Paint FILL = Color.web("42db49");
    private static final boolean CANUPDATE = true;
    private double catchFireProb = 0.15;

    //constructor for tree cell class
    public TreeCell(GridEntry entry, double burnProb) {
        super(FILL, entry);
        setCatchProb(burnProb);
    }

    //get the type of cell
    @Override
    public int getType() {
        return TYPE;
    }

    //get the race of the cell (not used)
    @Override
    public int getRace() {
        return 0;
    }

    //get the name of the cell for chart/display purposes
    @Override
    public String getLabel() { return LABEL; }

    //
    private void setCatchProb(double prob){
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

    private void setBurnProbability(double probability){
        catchFireProb = probability;
    }

    private Boolean checkCatchFire(GridEntry entry){
        Boolean FireNeighbor = checkBurningNeighbor(entry);
        double random = Math.random();
        if(random < catchFireProb && FireNeighbor){
            return true;
        }
        return false;
    }

    //update cell method for the tree cell class
    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters){
        setBurnProbability(parameters.get(0));
        Boolean catchFire = checkCatchFire(entry);
        if(catchFire){
            Cell fireCell = new FireCell(entry);
            entry.setNextStepCell(fireCell);
        }else{
            entry.setNextStepCell(this);
        }
    }

}