package cellsociety;

import cellsociety.Configuration.Configuration;
import javafx.scene.Group;

import java.util.*;

public class Simulation {
    private List<List<GridEntry>> SimulationGrid;
    private int status;
    private Set<GridEntry> emptyCellSet = new HashSet<GridEntry>();
    public Group myGroup = new Group();
    private double Width;
    private double Height;
    private Map<String, Integer> typesOfCells = new HashMap<>();
    private Configuration myConfiguration;

    public Simulation(Configuration simConfig, Group simGroup, double width, double height) {
        setConfiguration(simConfig);
        initializeSimulationGrid();
        setMyGroup(simGroup, width, height);
        initializeCellSets();
    }

    private void initializeSimulationGrid() {
        List<List<GridEntry>> grid = new ArrayList<>();
        for (int r = 0; r < myConfiguration.getRows(); r++) {
            List<GridEntry> insertRow = new ArrayList<>();
            for (int c = 0; c < myConfiguration.getColumns(); c++) {
                GridEntry insertGridEntry = createBorderGridEntry(c, r, myConfiguration.getTitle());
                if (insertGridEntry == null) {
                    insertGridEntry = randomizeGridEntry(r, c, myConfiguration.getTitle());
                }
                insertRow.add(insertGridEntry);
            }
            grid.add(insertRow);
        }
        initializeGridNeighbors(grid, myConfiguration.getTitle());

        SimulationGrid = grid;
    }

    public List<List<GridEntry>> getSimulationGrid() {
        return SimulationGrid;
    }

    private void setConfiguration(Configuration simConfiguration){ myConfiguration = simConfiguration; }

    private void setMyGroup(Group simGroup, double width, double height) {
        myGroup = simGroup;
        simGroup.prefWidth(width);
        simGroup.prefHeight(height);
        Width = width;
        Height = height;
    }

    private Group getMyGroup() {
        return myGroup;
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

    public void initializeGridNeighbors(List<List<GridEntry>> grid, String simulation) {
        for (int r = 0; r < myConfiguration.getRows(); r++) {
            for (int c = 0; c < myConfiguration.getColumns(); c++) {
                GridEntry currentGridEntry = grid.get(r).get(c);
                currentGridEntry.setNeighbors(grid, simulation, myConfiguration.getRows(), myConfiguration.getColumns(), myConfiguration.getNeighPattern());
            }
        }
    }


    private GridEntry createBorderGridEntry(int row, int col, String simulation) {
        if (myConfiguration.getBottom() != 0 && row == myConfiguration.getRows() + 1) {
            return new GridEntry(row, col, simulation, myConfiguration.getBottom());
        }
        else if (myConfiguration.getTop() != 0 && row == 0) {
            return new GridEntry(row, col, simulation, myConfiguration.getTop());
        }
        else if (myConfiguration.getLeft() != 0 && col == 0) {
            return new GridEntry(row, col, simulation, myConfiguration.getLeft());
        }
        else if (myConfiguration.getRight() != 0 && col == myConfiguration.getColumns() + 1) {
            return new GridEntry(row, col, simulation, myConfiguration.getRight());
        }
        return null;
    }

    private int getRandomNumberInRange() {
        Random r = new Random();
        return r.nextInt(myConfiguration.getMaxStates())+1;
    }

    private GridEntry randomizeGridEntry(int row, int col, String simulation) {
        int randomType = getRandomNumberInRange();
        return new GridEntry(row, col, simulation, randomType);
    }

    private void initializeCellSets() {
        typesOfCells = new HashMap<>();
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

    public Group step(List<Double> parameters) {
        myGroup.getChildren().clear();
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
//                Rectangle cell = currentGridConfig.get(r).get(c).getCell().getRectangle();
//                cell.setWidth(Width / currentGridConfig.get(r).size());
//                cell.setHeight(Height / currentGridConfig.size());
//                cell.setX(cell.getWidth() * c);
//                cell.setY(cell.getHeight() * r);
//                myGroup.getChildren().add(cell);
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
        return myGroup;

    }

    private void implementClickCell(Cell cell, GridEntry currentGridEntry, Set<GridEntry> emptyCells,
                                    List<Double> parameters) {
        cell.getRectangle().setOnMouseClicked(e -> cell.updateCell(currentGridEntry, emptyCells, parameters));
    }*/
}
