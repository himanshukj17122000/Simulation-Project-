package cellsociety.SegregationSimulation;

import cellsociety.Cell;
import cellsociety.EmptyCell;
import cellsociety.GridEntry;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Random;
import java.util.Set;

public class PersonCell extends Cell {
    private static final int TYPE = 2;
    private static final Paint[] FILL = {Color.BLUE, Color.RED}; // array entry corresponds to race
    private static boolean CANUPDATE = true;
    private static int RACE;
    private double Threshold = 0.3;
    private static final Paint SEGREGATIONFILL = Color.WHITE;


    public PersonCell(GridEntry entry, int race) {
        super(FILL[race-2], entry);
        initializeRace(race);
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells) { //need to fix to get empty cell set somewhere or make new method
        boolean satisfied = checkSatisfaction(entry);
        boolean moved = false;
        if(!satisfied){
            int space = new Random().nextInt(emptyCells.size());
            int i = 0;
            for(GridEntry gridSpace : emptyCells) {
                if ( !entry.getIsOccupied()) {
                    Cell newEmptyCell = new EmptyCell(entry, SEGREGATIONFILL); // setting current space to empty cell
                    entry.setNextStepCell(newEmptyCell); //setting empty space to instance of current cell
                    gridSpace.setNextStepCell(this);
                    emptyCells.add(entry);
                    emptyCells.remove(space);

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
        for (GridEntry neighbor : neighborSet) {
            if (neighbor.getCell().getType() == 2) {
                if(neighbor.getCell().getRace() == this.getRace()){
                    numSameRaceNeighbors++;
                }
            }
        }
        if((numSameRaceNeighbors / neighborSet.size()) < Threshold){
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
}
