package cellsociety.PreySimulation;

import cellsociety.Cell;
import cellsociety.EmptyCell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PredatorCell extends AnimalCell { // make animal superclass // contains long classes plz refactor
    private static final int TYPE = 3;
    private static final String LABEL = "Predators";
    private static final Paint FILL = Color.web("#614A32");
    private static final Paint PREYFILL = Color.PALEGREEN;
    private static final boolean CANUPDATE = true;
    private int reproductionTime = 0;
    private int timeSinceReproduction;
    private int maxTimeWithoutEating = 5;
    private int timeSinceEating;

    public PredatorCell(GridEntry entry, int reproductionTime, int maxTimeWithoutEating) {
        super(entry, 2, reproductionTime);
        this.setColor(FILL);
        setReproductionTime(reproductionTime);
        setMaxTimeWithoutEating(maxTimeWithoutEating);
        setTimeSinceEating(0);
        setTimeSinceReproduction(0);
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, double[] parameters) {

        if(getTimeSinceEating() > maxTimeWithoutEating){
            die(entry, emptyCells);
        }else{
            move(entry, emptyCells);
            super.reproduce(entry, emptyCells);
        }
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public int getRace() {
        return 2;
    }

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

    @Override
    public int getReproductionTime() {
        return reproductionTime;
    }

    @Override
    public void setReproductionTime(int reproductionTime) {
        this.reproductionTime = reproductionTime;
    }

    public void setMaxTimeWithoutEating(int time){
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
