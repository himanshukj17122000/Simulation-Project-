package cellsociety;

import cellsociety.Configuration.Configuration;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.*;

public class SimulationB {
    private List<List<GridEntry>> SimulationGrid;
    private int status;
    private Set<GridEntry> emptyCellSet = new HashSet<GridEntry>();
    public Group myGroup = new Group();
    private double Width;
    private double Height;
    private Map<String, Integer> typesOfCells = new HashMap<>();
    private Configuration myConfiguration;

    public SimulationB(Configuration simConfig, Group simGroup, double width, double height) {
        setConfiguration(simConfig);
        initializeSimulationGrid();
        setMyGroup(simGroup, width, height);
        initializeCellSets();
    }

    public Group initializeSimulationGrid() {
        List<List<GridEntry>> grid = new ArrayList<>();
        for (int r = 0; r < myConfiguration.getRows(); r++) {
            List<GridEntry> insertRow = new ArrayList<>();
            for (int c = 0; c < myConfiguration.getColumns(); c++) {
                GridEntry insertGridEntry = createBorderGridEntry(c, r, myConfiguration.getTitle());
                if (insertGridEntry == null) {
                    insertGridEntry = randomizeGridEntry(r, c, myConfiguration.getTitle());
                }
                createShape(insertGridEntry, r, c);
                insertRow.add(insertGridEntry);
            }
            grid.add(insertRow);
        }
        initializeGridNeighbors(grid, myConfiguration.getTitle());

        SimulationGrid = grid;
        return myGroup;
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

    private void initializeGridNeighbors(List<List<GridEntry>> grid, String simulation) {
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

    private void initializeCellSets() {
        typesOfCells = new HashMap<>();
        Set<GridEntry> emptyCells = getEmptyCellSet();
        //System.out.println(emptyCells.size());
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
        System.out.println(emptyCells.size());
    }

    public Group step(List<Double> parameters) {
        typesOfCells = new HashMap<>();
        myGroup.getChildren().clear();
        Set<GridEntry> emptyCells = getEmptyCellSet();
        System.out.println(emptyCells.size());
        List<List<GridEntry>> currentGridConfig = getSimulationGrid();
        for (int r = 0; r < currentGridConfig.size(); r++) {
            for (int c = 0; c < currentGridConfig.get(r).size(); c++) {
                GridEntry currentGridEntry = currentGridConfig.get(r).get(c);
                Cell currentCell = currentGridEntry.getCell();
                //implementClickCell(currentCell, currentGridEntry, emptyCells, parameters);
                currentCell.updateCell(currentGridEntry, emptyCells, parameters);

            }
        }
        System.out.println(emptyCells.size());
        //emptyCells = new HashSet<GridEntry>();

        for (int r = 0; r < currentGridConfig.size(); r++) {
            for (int c = 0; c < currentGridConfig.get(r).size(); c++) {
                GridEntry currentGridEntry = currentGridConfig.get(r).get(c);
                currentGridEntry.updateGridEntry();
                //createShape(currentGridEntry, r, c);
//                if (currentGridEntry.getCellType() == 1) { // update to not hard code
//                    emptyCells.add(currentGridEntry);
//                }
                String cellType = currentGridEntry.getCell().getLabel();
                if (cellType != null) {
                    typesOfCells.putIfAbsent(cellType, 0);
                    typesOfCells.put(cellType, typesOfCells.get(cellType) + 1);
                }
            }
        }

        setEmptyCellSet(emptyCells);
        System.out.println(emptyCells.size());
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