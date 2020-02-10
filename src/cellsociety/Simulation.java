package cellsociety;

import cellsociety.Configuration.Configuration;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.*;

public class Simulation {
    private List<List<GridEntry>> SimulationGrid;
    private int status;
    private Set<GridEntry> emptyCellSet = new HashSet<GridEntry>();
    private Map<String, Integer> typesOfCells = new HashMap<>();
    private Group myGroup = new Group();
    private Map<Cell, Shape> cellShapes;
    private Configuration myConfiguration;
    private double Width;
    private double Height;

    //constructor for the simulation class
    public Simulation(Group simGroup, Configuration inputConfiguration, double width, double height) {
        setMyGroup(simGroup, width, height);
        setConfiguration(inputConfiguration);
        setSimulationGrid(makeCellGrid());
        //setSimulationGrid(simGrid);
        initializeCellSets();

    }

    private List<List<GridEntry>> makeCellGrid() {  // initialization of a grid of empty cells
        List<List<GridEntry>> grid = new ArrayList<>();
        for (int r = 0; r < myConfiguration.getRows(); r++) {
            List<GridEntry> insertRow = new ArrayList<>();
            for (int c = 0; c < myConfiguration.getColumns(); c++) {
                GridEntry insertGridEntry = createBorderGridEntry(c, r, myConfiguration.getTitle());
                if (insertGridEntry == null) {
                    insertGridEntry = randomizeGridEntry(r, c, myConfiguration.getTitle());
                }
                cellShapes = new HashMap<>();
                insertRow.add(insertGridEntry);
            }
            grid.add(insertRow);
        }
        initializeGridNeighbors(grid);
        return grid;
    }

    private void initializeGridNeighbors(List<List<GridEntry>> grid) {
        for (int r = 0; r < myConfiguration.getRows(); r++) {
            for (int c = 0; c < myConfiguration.getColumns(); c++) {
                GridEntry currentGridEntry = grid.get(r).get(c);
                currentGridEntry.setNeighbors(grid, myConfiguration.getRows(), myConfiguration.getColumns(), myConfiguration.getNeighPattern(), myConfiguration.getShape(), myConfiguration.getMyboundary());
            }
        }
    }


    private GridEntry createBorderGridEntry(int row, int col, String simulation) {
        if(simulation.equals("Ants")){
            if (myConfiguration.getBottom() != 0 && row == myConfiguration.getRows() + 1) {
                return new GridEntryAnts(row, col, simulation, myConfiguration.getBottom());
            } else if (myConfiguration.getTop() != 0 && row == 0) {
                return new GridEntryAnts(row, col, simulation, myConfiguration.getTop());
            } else if (myConfiguration.getLeft() != 0 && col == 0) {
                return new GridEntryAnts(row, col, simulation, myConfiguration.getLeft());
            } else if (myConfiguration.getRight() != 0 && col == myConfiguration.getColumns() + 1) {
                return new GridEntryAnts(row, col, simulation, myConfiguration.getRight());
            }
            return null;
        }else {
            if (myConfiguration.getBottom() != 0 && row == myConfiguration.getRows() + 1) {
                return new GridEntry(row, col, simulation, myConfiguration.getBottom());
            } else if (myConfiguration.getTop() != 0 && row == 0) {
                return new GridEntry(row, col, simulation, myConfiguration.getTop());
            } else if (myConfiguration.getLeft() != 0 && col == 0) {
                return new GridEntry(row, col, simulation, myConfiguration.getLeft());
            } else if (myConfiguration.getRight() != 0 && col == myConfiguration.getColumns() + 1) {
                return new GridEntry(row, col, simulation, myConfiguration.getRight());
            }
            return null;
        }
    }

    private int getRandomNumberInRange() {
        Random r = new Random();
        return r.nextInt(myConfiguration.getMaxStates()) + 1;
    }

    private GridEntry randomizeGridEntry(int row, int col, String simulation) {
        if (simulation.equals("Ant")) {
            if (myConfiguration.getStartingConfig().equals("Given")) {
                Double r = Math.random();
                if (r < myConfiguration.getConcentration()[0]) {
                    return new GridEntryAnts(row, col, simulation, 3);
                } else if (r < myConfiguration.getConcentration()[1] + myConfiguration.getConcentration()[0]) {
                    return new GridEntryAnts(row, col, simulation, 2);
                } else {
                    return new GridEntryAnts(row, col, simulation, 1);
                }
            } else {
                int randomType = getRandomNumberInRange();
                return new GridEntryAnts(row, col, simulation, randomType);
            }
        }else{
            if(myConfiguration.getStartingConfig().equals("Given")) {
                Double r = Math.random();
                if (r < myConfiguration.getConcentration()[0]) {
                    return new GridEntry(row, col, simulation, 3);
                } else if (r < myConfiguration.getConcentration()[1] + myConfiguration.getConcentration()[0]) {
                    return new GridEntry(row, col, simulation, 2);
                } else {
                    return new GridEntry(row, col, simulation, 1);
                }
            } else {
                int randomType = getRandomNumberInRange();
                return new GridEntry(row, col, simulation, randomType);
            }
        }
    }




    private void setConfiguration(Configuration simConfiguration){ myConfiguration = simConfiguration; }


    private void setSimulationGrid(List<List<GridEntry>> simGrid) {
        SimulationGrid = simGrid;
    }

    private List<List<GridEntry>> getSimulationGrid() {
        return SimulationGrid;
    }


    private void setMyGroup(Group simGroup, double width, double height) {
        myGroup = simGroup;
        simGroup.prefWidth(width);
        simGroup.prefHeight(height);
        simGroup.setTranslateX(10);
        simGroup.setTranslateY(20);
        Width = width;
        Height = height;
    }

    private void setEmptyCellSet(Set<GridEntry> emptyCells) {
        emptyCellSet = emptyCells;
    }

    private Set<GridEntry> getEmptyCellSet() {
        return emptyCellSet;
    }

    //method that gets the types of cells in the simulation for chart purposes
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
//                String cellType = currentGridEntry.getCell().getLabel();
//                if (cellType != null) {
//                    typesOfCells.putIfAbsent(cellType, 0);
//                    typesOfCells.put(cellType, typesOfCells.get(cellType) + 1);
//                }
                //createShape(currentGridEntry, r, c);
            }
        }
        setEmptyCellSet(emptyCells);
    }

    //step function of the simulation and returns a visual group which is added to the Hbox in visualization class
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
                //implementClickCell(currentCell, currentGridEntry, emptyCells, parameters, r, c);
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
//                if (cellType != null) {
//                    typesOfCells.putIfAbsent(cellType, 0);
//                    typesOfCells.put(cellType, typesOfCells.get(cellType)+1);
//                }
            }
        }
        setEmptyCellSet(emptyCells);
        return myGroup;
    }

    private void implementClickCell(Cell cell, GridEntry currentGridEntry, Set<GridEntry> emptyCells,
                                    List<Double> parameters, int r, int c) {
        System.out.println(cellShapes.get(cell).isPressed());
        cellShapes.get(cell).setOnMousePressed(e1 -> {
            cellShapes.get(cell).setFill(Color.BLACK);
        /*cell.updateCell(currentGridEntry, emptyCells, parameters);
        createShape(currentGridEntry, r, c);*/
        });
    }

    private void createShape(GridEntry curGridEntry, int row, int col){
        Cell cell = curGridEntry.getCell();
        Shape cellVis;
        if(myConfiguration.getShape().equals("Hexagon")) {
            double centerX = Width / myConfiguration.getColumns() * col;
            double centerY = Height / myConfiguration.getRows() * row;
            double radius = Width / myConfiguration.getColumns() / 2;
            if (row % 2 == 0) {
                centerX = centerX - radius;
            }
            cellVis = new Polygon();
            ((Polygon) cellVis).getPoints().addAll(
                    centerX + radius * Math.cos(Math.PI / 6), centerY + radius * Math.sin(Math.PI / 6),
                    centerX, centerY + radius,
                    centerX - radius * Math.cos(Math.PI / 6), centerY + radius * Math.sin(Math.PI / 6),
                    centerX - radius * Math.cos(Math.PI / 6), centerY - radius * Math.sin(Math.PI / 6),
                    centerX, centerY - radius,
                    centerX + radius * Math.cos(Math.PI / 6), centerY - radius * Math.sin(Math.PI / 6));
            cellVis.setFill(cell.getColor());
        } else if(myConfiguration.getShape().equals("Circle")) {
            double centerX = Width / myConfiguration.getColumns() * row;
            double centerY = Height / myConfiguration.getRows() * col;
            double radius = Width / myConfiguration.getColumns() / 2;
            if (col % 2 == 0) {
                centerX = centerX - radius;
            }
            cellVis = new Circle(centerX, centerY, radius, cell.getColor());
        } else {
            double width = Width/myConfiguration.getColumns();
            double height = Height/myConfiguration.getRows();
            cellVis = new Rectangle(width*row, height*col, width, height);
            cellVis.setFill(cell.getColor());
        }
        cellShapes.put(cell, cellVis);
        myGroup.getChildren().add(cellVis);
    }

}