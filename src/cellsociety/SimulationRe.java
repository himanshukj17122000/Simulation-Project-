package cellsociety;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class SimulationRe {
    private List<List<GridEntry>> SimulationGrid;
    private int status;
    private Set<GridEntry> emptyCellSet = new HashSet<GridEntry>();
    public Group myGroup = new Group();
    private double Width;
    private double Height;
    private Map<String, Integer> typesOfCells = new HashMap<>();

    public SimulationRe(List<List<GridEntry>> simGrid, Group simGroup, double gridWidth, double gridHeight) {
        setSimulationGrid(simGrid);
        setMyGroup(simGroup, gridWidth, gridHeight);
        initializeCellSets();
    }

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

    private Group getMyGroup() {
        return myGroup;
    }

    private void setEmptyCellSet(Set<GridEntry> emptyCells) {
        emptyCellSet = emptyCells;
    }

    public Set<GridEntry> getEmptyCellSet() {
        return emptyCellSet;
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
                currentCell.updateCell(currentGridEntry, emptyCells, parameters);
            }
        }

        for (int r = 0; r < currentGridConfig.size(); r++) {
            for (int c = 0; c < currentGridConfig.get(r).size(); c++) {
                GridEntry currentGridEntry = currentGridConfig.get(r).get(c);
                currentGridEntry.updateGridEntry();
                Rectangle cell = currentGridConfig.get(r).get(c).getCell().getRectangle();
                cell.setWidth(Width / currentGridConfig.get(r).size());
                cell.setHeight(Height / currentGridConfig.size());
                cell.setX(cell.getWidth() * c);
                cell.setY(cell.getHeight() * r);
                myGroup.getChildren().add(cell);
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
}
