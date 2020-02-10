package cellsociety.rPSSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class rpsCell extends Cell {
    private int TYPE;
    private static final String[] LABEL = {"Group Blue", "Group Red", "Group Green"};
    private static final Paint[] FILL = {Color.BLUE, Color.RED, Color.GREEN}; // array entry corresponds to race
    private static boolean CANUPDATE = true;
    private int RACE;
    private int Threshold = 4;

    public rpsCell(GridEntry entry, int type, int threshold) {
        super(FILL[type-1], entry);
        setTYPE(type);
        setThreshold(threshold);
    }

    @Override
    public void updateCell(GridEntry entry, Set<GridEntry> emptyCells, List<Double> parameters) {
        double value = parameters.get(0);
        int val = (int) value;
        setThreshold(val);
         if(battle(entry)!=0){
            Cell cell = new rpsCell(entry, battle(entry), Threshold);
            entry.setNextStepCell(cell);
        }
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public int getRace() {
        return 0;
    }

    @Override
    public String getLabel() {
        return null;
    }

    private void setTYPE(int type){ TYPE = type; }


    private void setThreshold(int newValue){
        Threshold = newValue;
    }

    private int battle(GridEntry entry) {
        Set<GridEntry> neighborSet = entry.getNeighbors();
        Map<Integer, Integer> typesOfCells = new HashMap<>();
        for (GridEntry neighbor : neighborSet) {
            int cellType = neighbor.getCell().getType();
            typesOfCells.putIfAbsent(cellType, 0);
            typesOfCells.put(cellType, typesOfCells.get(cellType) + 1);
        }
        int max = 0;
        int maxType = 1;
        if(getType()<FILL.length && typesOfCells.containsKey(getType()+1)){
            if(typesOfCells.get(getType()+1)> Threshold){
                return getType()+1;
            }
        }else if(getType()==FILL.length && typesOfCells.containsKey(1)){
            if(typesOfCells.get(1)> Threshold){
                return 1;
            }
        }
        return 0;
    }
}
