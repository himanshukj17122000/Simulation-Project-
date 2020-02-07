package cellsociety.SegregationSimulation;

import cellsociety.Cell;
import cellsociety.EmptyCell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Random;
import java.util.Set;

public class PersonCell extends Cell {
    private static final int TYPE = 2;
    private static final String[] LABEL = {"Group Blue", "Group Red"};
    private static final Paint[] FILL = {Color.BLUE, Color.RED}; // array entry corresponds to race
    private static boolean CANUPDATE = true;
    private int RACE;
    private double Threshold = 0.7;
    private static final Paint SEGREGATIONFILL = Color.WHITE;


    public PersonCell(GridEntry entry, int race, double threshold) {
        super(FILL[race-2], entry);
        initializeRace(race);
        setThreshold(threshold);
    }

    private void setThreshold(double newValue){
        Threshold = newValue;
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, double[] parameters) { //need to fix to get empty cell set somewhere or make new method
        setThreshold(parameters[0]);
        boolean satisfied = checkSatisfaction(entry);
        boolean moved = false;
        if(!satisfied){
            int space = new Random().nextInt(emptyCells.size());
            int i = 0;
            for(GridEntry gridSpace : emptyCells) {
                if(i == space) {
                    Cell newEmptyCell = new EmptyCell(entry, SEGREGATIONFILL); // setting current space to empty cell
                    entry.setNextStepCell(newEmptyCell); //setting empty space to instance of current cell
                    gridSpace.setNextStepCell(this);
                    emptyCells.add(entry);
                    emptyCells.remove(gridSpace);
                    moved = true;
                    break;
                }
                i++;
            }
        }
        if(!moved){
            entry.setNextStepCell(this);
        }
    }

    private Boolean checkSatisfaction(GridEntry entry) {
        Set<GridEntry> neighborSet = entry.getNeighbors();
        double numSameRaceNeighbors = 0;
        double numNeighbors = 0;
        for (GridEntry neighbor : neighborSet) {
            if (neighbor.getCell().getType() == 2) {
                numNeighbors++;
                if(neighbor.getCell().getRace() == this.getRace()){
                    numSameRaceNeighbors++;
                }
            }
        }
        if((numSameRaceNeighbors / numNeighbors) < Threshold || numNeighbors == 0){
            return false;
        }
        return true;

    }

    @Override
    public int getType() {
        return TYPE;
    }

    private void initializeRace(int race){
        RACE = race;
    }

    public int getRace(){
        return RACE;
    }

    @Override
    public String getLabel() { return LABEL[0]; }
}
