package cellsociety.PreySimulation;

import cellsociety.Cell;
import cellsociety.EmptyCell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * PredatorCell class class for Prey simulation
 * @author Olga Suchankova
 */
public class PredatorCell extends AnimalCell { // make animal superclass // contains long classes plz refactor
    private static final int TYPE = 3;
    private static final String LABEL = "Predators";
    private static final Paint FILL = Color.web("455bbe");
    private static final Paint PREYFILL = Color.web("71add3");
    private static final boolean CANUPDATE = true;
    private int reproductionTime = 1;
    private int timeSinceReproduction;
    private int maxTimeWithoutEating = 100;
    private int timeSinceEating;
    private static final int Race = 2;

    //predator cell constructor
    public PredatorCell(GridEntry entry, int reproductionTime, int maxTimeWithoutEating) {
        super(entry, Race, reproductionTime);
        this.setColor(FILL);
        setReproductionTime(reproductionTime);
        setMaxTimeWithoutEating(maxTimeWithoutEating);
        setTimeSinceEating(0);
        setTimeSinceReproduction(0);
    }

    //update cell method for predator cell
    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
        setParameters(parameters);
        this.setColor(FILL);
        if(getTimeSinceEating() > maxTimeWithoutEating){
            die(entry, emptyCells);
        }else{
            move(entry, emptyCells);
            super.reproduce(entry, emptyCells);
        }
    }

    private void setParameters(List<Double> parameters){
        double value = parameters.get(1);
        int val = (int) value;
        setMaxTimeWithoutEating(val);
        value = parameters.get(2);
        val = (int) value;
        setReproductionTime(val);
    }

    //get type for predator cell
    @Override
    public int getType() {
        return TYPE;
    }
//get race/special info for predator cell
    @Override
    public int getRace() {
        return Race;
    }
//get label/name for predator cell
    @Override
    public String getLabel() { return LABEL; }

    protected void setTimeSinceReproduction(int time){
        timeSinceReproduction = time;
    }

    protected int getTimeSinceReproduction(){
        return timeSinceReproduction;
    }

    private void setTimeSinceEating(int time){
        timeSinceEating = time;
    }

    private int getTimeSinceEating(){
        return timeSinceEating;
    }
//get the reporduction time for the predator cell
    @Override
    public int getReproductionTime() {
        return reproductionTime;
    }
//set the reproductiontime for the predator cell
    @Override
    public void setReproductionTime(int reproductionTime) {
        this.reproductionTime = reproductionTime;
    }

    private void setMaxTimeWithoutEating(int time){
        maxTimeWithoutEating = time;
    }

    @Override
    protected AnimalCell offSpring(GridEntry entry, Set<GridEntry> emptyCells){
        emptyCells.remove(entry);
        return new PredatorCell(entry, getReproductionTime(), maxTimeWithoutEating);
    }

    @Override
    protected void move(GridEntry entry, Set<GridEntry> emptyCells) {
        Set<GridEntry> preyCellSet = setOfPreyNeighbors(entry, emptyCells);
        boolean moved = false;
        int size = preyCellSet.size();
        if(size > 0){
            int space = new Random().nextInt(size);
            int i = 0;
            for (GridEntry gridSpace : preyCellSet) {
                if (i == space) {
                    eatAnimal(entry, gridSpace);
                    emptyCells.add(entry);
                    emptyCells.remove(gridSpace);
                    moved = true;
                    break;
                }
                i++;
            }
        }
        if (moved) {
            setTimeSinceEating(0);
        } else {
            //System.out.println("Bye");
            super.move(entry, emptyCells);
            //System.out.println("Bye");
            setTimeSinceEating(getTimeSinceEating() + 1);
        }
    }

    private void eatAnimal(GridEntry currentGridSpace, GridEntry newGridSpace){
        Cell newEmptyCell = new EmptyCell(currentGridSpace, PREYFILL); // setting current space to empty cell
        currentGridSpace.setNextStepCell(newEmptyCell);//setting prey space to instance of current cell
        newGridSpace.setNextStepCell(this);
    }

    private Set<GridEntry> setOfPreyNeighbors(GridEntry entry, Set<GridEntry> emptyCells){
        Set<GridEntry> neighborSet = entry.getNeighbors();
        Set<GridEntry> preyCellSet = new HashSet<GridEntry>();
        for (GridEntry neighbor : neighborSet) {
            if(emptyCells.contains(neighbor)){
                if(neighbor.getNextStepCell() == null){
                    if(neighbor.getCell().getRace() == 1){
                        preyCellSet.add(neighbor);
                    }
                }else if(neighbor.getNextStepCell().getRace() == 1 ){
                    preyCellSet.add(neighbor);
                }
            }
        }
        return preyCellSet;
    }


    protected void moveToEmptyGridEntry(GridEntry currentGridSpace, GridEntry newGridSpace){
        Cell newEmptyCell = new EmptyCell(currentGridSpace, PREYFILL); // setting current space to empty cell
        currentGridSpace.setNextStepCell(newEmptyCell); //setting empty space to instance of current cell
        newGridSpace.setNextStepCell(this);
    }


    protected Set<GridEntry> setOfEmptyNeighbors(GridEntry entry, Set<GridEntry> emptyCells){
        Set<GridEntry> neighborSet = entry.getNeighbors();
        Set<GridEntry> preyCellSet = new HashSet<GridEntry>();
        for (GridEntry neighbor : neighborSet) {
            if(emptyCells.contains(neighbor)){
               preyCellSet.add(neighbor);
            }
        }
        return preyCellSet;
    }

    private void die(GridEntry entry, Set<GridEntry> emptyCells) {
        Cell newEmptyCell = new EmptyCell(entry, PREYFILL);
        entry.setNextStepCell(newEmptyCell);
        emptyCells.add(entry);
    }

}
