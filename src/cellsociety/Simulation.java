package cellsociety;

import javafx.scene.Group;

import java.util.*;

public class Simulation {
    private List<List<GridEntry>> SimulationGrid;
    private int status;
    private Set<GridEntry> emptyCellSet = new HashSet<GridEntry>();
    private Map<String, Integer> typesOfCells = new HashMap<>();

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

    public Map<String, Integer> getTypesOfCells(){
        return typesOfCells;
    }

    private void initializeCellSets(){
        Set<GridEntry> emptyCells = getEmptyCellSet();
        List<List<GridEntry>> currentGridConfig = getSimulationGrid();
        for (int r = 0; r < currentGridConfig.size(); r++) {
            for (int c = 0; c < currentGridConfig.get(r).size(); c++) {
                GridEntry currentGridEntry = currentGridConfig.get(r).get(c);
                if (currentGridEntry.getCellType() == 1) { // update to not hard code
                    emptyCells.add(currentGridEntry);
                }
                String cellType = currentGridEntry.getCell().getLabel();
                if (cellType != null) {
                    typesOfCells.putIfAbsent(cellType, 0);
                    typesOfCells.put(cellType, typesOfCells.get(cellType) + 1);
                }
            }
        }
        setEmptyCellSet(emptyCells);
    }

    public void step(List<Double> parameters) {
        //int[] cellType = new int[2];  // random line to initialize
        typesOfCells = new HashMap<>();
        Set<GridEntry> emptyCells = getEmptyCellSet();
        List<List<GridEntry>> currentGridConfig = getSimulationGrid();
        for (int r = 0; r < currentGridConfig.size(); r++) {
            for (int c = 0; c < currentGridConfig.get(r).size(); c++) {
                GridEntry currentGridEntry = currentGridConfig.get(r).get(c);
                Cell currentCell = currentGridEntry.getCell();
                //implementClickCell(currentCell, currentGridEntry, emptyCells, parameters);
                currentCell.updateCell(currentGridEntry, emptyCells, parameters);
            }
        }
       for (int r = 0; r < currentGridConfig.size(); r++) {
            for (int c = 0; c < currentGridConfig.get(r).size(); c++) {
                GridEntry currentGridEntry = currentGridConfig.get(r).get(c);
                currentGridEntry.updateGridEntry();
                if (currentGridEntry.getCellType() == 1) { // update to not hard code
                    emptyCells.add(currentGridEntry);
                }
//                cellType[0] = currentGridEntry.getCellType();
//                cellType[1] = currentGridEntry.getCell().getRace();
                String cellType = currentGridEntry.getCell().getLabel();
                if (cellType != null) {
                    typesOfCells.putIfAbsent(cellType, 0);
                    typesOfCells.put(cellType, typesOfCells.get(cellType)+1);
                    System.out.println(typesOfCells.get(cellType));
                }
           }
        }
        setEmptyCellSet(emptyCells);
    }

    /*private void implementClickCell(Cell cell, GridEntry currentGridEntry, Set<GridEntry> emptyCells,
                                   List<Double> parameters) {
        cell.getRectangle().setOnMouseClicked(e -> cell.updateCell(currentGridEntry, emptyCells, parameters));
    }*/
}
