package cellsociety;

import cellsociety.Configuration.Configuration;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.*;

public class Simulation {
    private List<List<GridEntry>> SimulationGrid;
    private int status;
    private Set<GridEntry> emptyCellSet = new HashSet<GridEntry>();
    private Map<String, Integer> typesOfCells = new HashMap<>();
    private Group myGroup = new Group();
    private Configuration myConfiguration;
    private double Width;
    private double Height;

    public Simulation(Group simGroup, Configuration inputConfiguration,  double width, double height) {
        setMyGroup(simGroup, width, height);
        setConfiguration(inputConfiguration);
        setSimulationGrid(makeCellGrid());
        //setSimulationGrid(simGrid);
        initializeCellSets();

    }

    public List<List<GridEntry>> makeCellGrid() {  // initialization of a grid of empty cells
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
        return grid;
    }

    public void initializeGridNeighbors(List<List<GridEntry>> grid, String simulation) {
        for (int r = 0; r < myConfiguration.getRows(); r++) {
            for (int c = 0; c < myConfiguration.getColumns(); c++) {
                GridEntry currentGridEntry = grid.get(r).get(c);
                currentGridEntry.setNeighbors(grid, myConfiguration.getRows(), myConfiguration.getColumns(), myConfiguration.getNeighPattern(), myConfiguration.getShape(), simulation);
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

    private void setConfiguration(Configuration simConfiguration){ myConfiguration = simConfiguration; }


    private void setSimulationGrid(List<List<GridEntry>> simGrid) {
        SimulationGrid = simGrid;
    }

    public List<List<GridEntry>> getSimulationGrid() {
        return SimulationGrid;
    }


    private void setMyGroup(Group simGroup, double width, double height) {
        myGroup = simGroup;
        simGroup.prefWidth(width);
        simGroup.prefHeight(height);
        Width = width;
        Height = height;
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

    public Group step(List<Double> parameters) {
        //int[] cellType = new int[2];  // random line to initialize
        typesOfCells = new HashMap<>();
        Set<GridEntry> emptyCells = getEmptyCellSet();
        myGroup.getChildren().clear();
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
                createShape(currentGridEntry, r, c);
//                cellType[0] = currentGridEntry.getCellType();
//                cellType[1] = currentGridEntry.getCell().getRace();
                String cellType = currentGridEntry.getCell().getLabel();
                if (cellType != null) {
                    typesOfCells.putIfAbsent(cellType, 0);
                    typesOfCells.put(cellType, typesOfCells.get(cellType)+1);
                    //  System.out.println(typesOfCells.get(cellType));
                }
            }
        }
        setEmptyCellSet(emptyCells);
        return myGroup;
    }

    private void implementClickCell(Cell cell, GridEntry currentGridEntry, Set<GridEntry> emptyCells,
                                    List<Double> parameters) {
        cell.getRectangle().setOnMouseClicked(e -> cell.updateCell(currentGridEntry, emptyCells, parameters));
    }

    private void createShape(GridEntry curGridEntry, int row, int col){
        Cell cell = curGridEntry.getCell();
        Shape cellVis;
        if(myConfiguration.getShape().equals("Hexagon")){
            double centerX = Width/myConfiguration.getColumns() * row;
            double centerY = Height/myConfiguration.getRows() * col;
            double radius = Width/myConfiguration.getColumns()/2;
            if(col%2 == 0){
                centerX = centerX - radius;
            }
            cellVis = new Circle(centerX, centerY, radius, cell.getColor());
        }else{
            double width = Width/myConfiguration.getColumns();
            double height = Height/myConfiguration.getRows();
            cellVis = new Rectangle(width*row, height*col, width, height);
            cellVis.setFill(cell.getColor());
        }
        myGroup.getChildren().add(cellVis);
    }


}
