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

public class AnimalCell extends Cell {
    private static final int TYPE = 1;
    private static final String LABEL = "Preys";
    private static final Paint[] FILL = {Color.web("fc9dc3"), Color.web("455bbe")};
    protected static final Paint PREYFILL = Color.web("71add3");
    private int reproductionTime = 200;
    private int timeSinceReproduction;
    private static final boolean CANUPDATE = true;
    private int RACE;


    public AnimalCell(GridEntry entry, int species, int reproductionTime) {
        super(FILL[species-1], entry);
        setTimeSinceReproduction(0);
        setReproductionTime(reproductionTime);
        setRace(1);

    }

    private void setRace(int race){
        RACE = race;
    }


    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
        double value = parameters.get(0);
        int val = (int) value;
        setReproductionTime(val);
//        System.out.println(getReproductionTime());
        reproduce(entry, emptyCells);
        move(entry, emptyCells);

    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public int getRace() {
        return 1;
    }

    @Override
    public String getLabel() { return LABEL; }

    protected void setTimeSinceReproduction(int time){
        this.timeSinceReproduction = time;
    }

    protected int getTimeSinceReproduction(){
        return this.timeSinceReproduction;
    }

    protected void setReproductionTime(int time){
        reproductionTime = time;
    }

    protected int getReproductionTime(){ return reproductionTime; }

    protected void move(GridEntry entry, Set<GridEntry> emptyCells){
       Set<GridEntry> emptyCellSet = setOfEmptyNeighbors(entry, emptyCells);
        boolean moved = false;
        int size = emptyCellSet.size();

        if(size > 0){
        int space = new Random().nextInt(size);
        int i = 0;

        for(GridEntry gridSpace : emptyCellSet) {
            if (i == space){
                moveToEmptyGridEntry(entry, gridSpace);
                emptyCells.add(entry);
                emptyCells.remove(gridSpace);
                moved = true;
                break;
            }
            i++;
        }}
        if(!moved){
            if(entry.getNextStepCell() == null){
                //System.out.print("hi");
                entry.setNextStepCell(this);
            }
        }
    }

    protected void moveToEmptyGridEntry(GridEntry currentGridSpace, GridEntry newGridSpace){
        if(currentGridSpace.getNextStepCell()!=null) {
            if (currentGridSpace.getNextStepCell().getRace() != 2) {
                Cell newEmptyCell = new EmptyCell(currentGridSpace, PREYFILL); // setting current space to empty cell
                currentGridSpace.setNextStepCell(newEmptyCell); //setting empty space to instance of current cell
                newGridSpace.setNextStepCell(this);
            }
        }else{
            Cell newEmptyCell = new EmptyCell(currentGridSpace, PREYFILL); // setting current space to empty cell
            currentGridSpace.setNextStepCell(newEmptyCell); //setting empty space to instance of current cell
            newGridSpace.setNextStepCell(this);
        }
    }

    private void reproduceInEmptyGridEntry(GridEntry newGridSpace, AnimalCell offSpring){ // setting current space to empty cell
        if(newGridSpace.getCell().getRace() == 0){
            newGridSpace.setNextStepCell(offSpring);
        }

    }

    protected AnimalCell offSpring(GridEntry entry, Set<GridEntry> emptyCells){
        return new AnimalCell(entry, 1, getReproductionTime());
    }

    protected Set<GridEntry> setOfEmptyNeighbors(GridEntry entry, Set<GridEntry> emptyCells){
        Set<GridEntry> neighborSet = entry.getNeighbors();
        Set<GridEntry> emptyCellSet = new HashSet<GridEntry>();
        for (GridEntry neighbor : neighborSet) {
            Cell neighborCell = neighbor.getCell();
            if(emptyCells.contains(neighbor)) {
                if (neighbor.getNextStepCell() == null) {
                    if (neighborCell.getRace() == 0) {
                        emptyCellSet.add(neighbor);
                    }
                } else if (neighbor.getNextStepCell().getRace() ==0) {
                    emptyCellSet.add(neighbor);
                }
            }
        }
        return emptyCellSet;
    }

    protected void reproduce(GridEntry entry, Set<GridEntry> emptyCells){
        boolean reproduced = false;
        if(getTimeSinceReproduction() >= getReproductionTime()){
            Set<GridEntry> emptyCellSet = setOfEmptyNeighbors(entry, emptyCells);
            int size = emptyCellSet.size();
            if(size>0){
            int space = new Random().nextInt(size);
            int i = 0;
            for(GridEntry gridSpace : emptyCellSet) {
                if (i == space){
                    reproduceInEmptyGridEntry(gridSpace, offSpring(gridSpace, emptyCells));
                    reproduced = true;
                    break;
                }
                i++;
            }
        }}
        if(reproduced){
            setTimeSinceReproduction(0);
        }else{
            setTimeSinceReproduction(getTimeSinceReproduction()+1);
        }
    }

}
