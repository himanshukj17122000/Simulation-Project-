package cellsociety;

import javafx.scene.Group;

import java.util.*;

public class Simulation {
    private List<List<GridEntry>> SimulationGrid;
    private int status;
    private Set<GridEntry> emptyCellSet = new HashSet<GridEntry>();
    private Map<int[], Integer> typesOfCells = new HashMap<>();

    public Simulation(List<List<GridEntry>> simGrid) {
        setSimulationGrid(simGrid);
        initializeCellSets();
        //initializeHashMap();
    }

    private void setSimulationGrid(List<List<GridEntry>> simGrid) {
        SimulationGrid = simGrid;
    }


    public List<List<GridEntry>> getSimulationGrid() {
        return SimulationGrid;
    }

    public Group myGroup = new Group();

    private void setMyGroup(Group simGroup){
        myGroup = simGroup;
    }

    private void setEmptyCellSet(Set<GridEntry> emptyCells) {
        emptyCellSet = emptyCells;
    }

    public Set<GridEntry> getEmptyCellSet() {
        return emptyCellSet;
    }

    public Map<int[], Integer> getTypesOfCells(){
        return typesOfCells;
    }

    private void initializeCellSets(){
        Set<GridEntry> emptyCells = getEmptyCellSet();
        int[] cellType = new int[2];  // random line to initialize
        List<List<GridEntry>> currentGridConfig = getSimulationGrid();
        for (int r = 0; r < currentGridConfig.size(); r++) {
            for (int c = 0; c < currentGridConfig.get(r).size(); c++) {
                GridEntry currentGridEntry = currentGridConfig.get(r).get(c);
                if (currentGridEntry.getCellType() == 1) { // update to not hard code
                    emptyCells.add(currentGridEntry);
                }
                cellType[0] = currentGridEntry.getCellType();
                cellType[1] = currentGridEntry.getCell().getRace();
                typesOfCells.putIfAbsent(cellType, 0);
                typesOfCells.put(cellType, typesOfCells.get(cellType)+1);
            }
        }
        setEmptyCellSet(emptyCells);
    }

    public void step() {
        int[] cellType = new int[2];  // random line to initialize
        Set<GridEntry> emptyCells = getEmptyCellSet();
        List<List<GridEntry>> currentGridConfig = getSimulationGrid();
        for (int r = 0; r < currentGridConfig.size(); r++) {
            for (int c = 0; c < currentGridConfig.get(r).size(); c++) {
                GridEntry currentGridEntry = currentGridConfig.get(r).get(c);
                Cell currentCell = currentGridEntry.getCell();
                currentCell.updateCell(currentGridEntry, emptyCells);
            }
        }

       for (int r = 0; r < currentGridConfig.size(); r++) {
            for (int c = 0; c < currentGridConfig.get(r).size(); c++) {
                GridEntry currentGridEntry = currentGridConfig.get(r).get(c);
                currentGridEntry.updateGridEntry();
                if (currentGridEntry.getCellType() == 1) { // update to not hard code
                    emptyCells.add(currentGridEntry);
                }
                cellType[0] = currentGridEntry.getCellType();
                cellType[1] = currentGridEntry.getCell().getRace();
                typesOfCells.putIfAbsent(cellType, 0);
                typesOfCells.put(cellType, typesOfCells.get(cellType)+1);
           }
        }
        setEmptyCellSet(emptyCells);
    }

}
