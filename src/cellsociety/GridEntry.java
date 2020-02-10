package cellsociety;

import cellsociety.FireSimulation.FireCell;
import cellsociety.FireSimulation.TreeCell;
import cellsociety.GameSimulation.DeadCell;
import cellsociety.GameSimulation.LiveCell;
import cellsociety.PercolationSimulation.AirCell;
import cellsociety.PercolationSimulation.WaterCell;
import cellsociety.PreySimulation.AnimalCell;
import cellsociety.PreySimulation.PredatorCell;
import cellsociety.SegregationSimulation.PersonCell;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import rPSSimulation.rpsCell;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


//When making a Grid Entry instance, be sure to first initialize the entire grid and then using a second pass use methods
//to fill in all the parameters
//Also maybe change ID/ location thing to just be
public class GridEntry {

    private int containsCellType;
    private Cell containedCell;
    private Cell nextStepCell = null;
    private boolean isOccupied;
    private Set<GridEntry> NEIGHBORS;
    private int ROW;
    private int COLUMN;
    private static final Paint FIREFILL = Color.WHITE; //fills for empty cells, put these variables somewhere elseeee
    private static final Paint PERCOLATIONFILL = Color.DARKGRAY;
    private static final Paint PREYFILL = Color.web("71add3");
    private static final Paint SEGREGATIONFILL = Color.WHITE;

    public GridEntry(int row, int col, String simulation, int type){
        initializeGridEntryID(row, col);
        createCell(simulation, type);
    }

    private void initializeGridEntryID(int row, int col){
        ROW = row;
        COLUMN = col;
    }


    public void setNextStepCell(Cell nextCell){
        nextStepCell = nextCell;
    }

    public Cell getNextStepCell(){
        return nextStepCell;
    }

    public void updateGridEntry(){
        if(getNextStepCell() == null){
            setCell(getCell());
        }else {
            setCell(getNextStepCell());
        }
    }

    public void setNeighbors(List<List<GridEntry>> grid, int numRows, int numCols, int[] neighborBool, String shape, String simulation){ //refactor to be 20 lines

        Set<GridEntry> NSET = new HashSet<GridEntry>();
        if(shape.equals("Rectangle")){
            rectangleNeighbors(grid, numRows, numCols, neighborBool, simulation, NSET);
        }else if(shape.equals("Hexagon")){
            hexagonNeighbors(grid, numRows, numCols, neighborBool, simulation, NSET);
        }
    }

    private void hexagonNeighbors(List<List<GridEntry>> grid, int numRows, int numCols, int[] neighborBool, String simulation, Set<GridEntry> NSET) {
        if (getRow() > 0) {
            if (neighborBool[0] == 1) {
                GridEntry topLeftNeighbor;
                if (getColumn() >  0 && getRow() % 2 == 0) {
                    topLeftNeighbor = grid.get(getRow()-1).get(getColumn()-1);
                }else{
                    topLeftNeighbor = grid.get(getRow()-1).get(getColumn());
                }
                NSET.add(topLeftNeighbor);
            }
            if (neighborBool[1] == 1) {
                GridEntry topRightNeighbor;
                if (getColumn() < numCols - 1 && getRow() % 2 == 1) {
                    topRightNeighbor = grid.get(getRow()-1).get(getColumn()+1);
                }else{
                    topRightNeighbor = grid.get(getRow()-1).get(getColumn());
                }
                NSET.add(topRightNeighbor);
            }
        }

        if (getRow() < numRows - 1) {
            if (neighborBool[4] == 1) {
                GridEntry bottomLeftNeighbor;
                if (getColumn() >  0 && getRow() % 2 == 0) {
                    bottomLeftNeighbor = grid.get(getRow()+1).get(getColumn()-1);
                }else{
                    bottomLeftNeighbor = grid.get(getRow()+1).get(getColumn());
                }
                NSET.add(bottomLeftNeighbor);
            }
            if (neighborBool[3] == 1) {
                GridEntry bottomRightNeighbor;
                if (getColumn() < numCols - 1 && getRow() % 2 == 1) {
                    bottomRightNeighbor = grid.get(getRow()+1).get(getColumn()+1);
                }else{
                    bottomRightNeighbor = grid.get(getRow()+1).get(getColumn());
                }
                NSET.add(bottomRightNeighbor);
            }
        }
        if (getColumn() > 0 && neighborBool[2] == 1) {
            GridEntry leftNeighbor = grid.get(getRow()).get(getColumn()-1);
            NSET.add(leftNeighbor);
        }
        if (getColumn() < numCols - 1 && neighborBool[5] == 1) {
            GridEntry rightNeighbor = grid.get(getRow()).get(getColumn()+1);
            NSET.add(rightNeighbor);
        }
        NEIGHBORS = NSET;
    }

    private void rectangleNeighbors(List<List<GridEntry>> grid, int numRows, int numCols, int[] neighborBool, String simulation, Set<GridEntry> NSET){
        if (getRow() > 0 && neighborBool[1] == 1) {
            GridEntry topNeighbor = grid.get(getRow() - 1).get(getColumn());
            NSET.add(topNeighbor);
        }
        if (getRow() < numRows - 1 && neighborBool[5] == 1) {
            GridEntry bottomNeighbor = grid.get(getRow() + 1).get(getColumn());
            NSET.add(bottomNeighbor);
        }
        if (getColumn() > 0 && neighborBool[3] == 1) {
            GridEntry leftNeighbor = grid.get(getRow()).get(getColumn()-1);
            NSET.add(leftNeighbor);
        }
        if (getColumn() < numCols - 1 && neighborBool[7] == 1) {
            GridEntry rightNeighbor = grid.get(getRow()).get(getColumn()+1);
            NSET.add(rightNeighbor);
        }
        if (getRow() > 0) {
            if (getColumn() > 0 && neighborBool[0] == 1) {
                GridEntry topLeftNeighbor = grid.get(getRow() - 1).get(getColumn() - 1);
                NSET.add(topLeftNeighbor);
            }
            if (getColumn() < numCols - 1 && neighborBool[2] == 1) {
                GridEntry topRightNeighbor = grid.get(getRow() - 1).get(getColumn() + 1);
                NSET.add(topRightNeighbor);
            }
        }
        if (getRow() < numRows - 1) {
            if (getColumn() > 0 && neighborBool[6] == 1) {
                GridEntry bottomLeftNeighbor = grid.get(getRow() + 1).get(getColumn() - 1);
                NSET.add(bottomLeftNeighbor);
            }
            if (getColumn() < numCols - 1 && neighborBool[4] == 1) {
                GridEntry bottomRightNeighbor = grid.get(getRow() + 1).get(getColumn() + 1);
                NSET.add(bottomRightNeighbor);
            }
        }

        NEIGHBORS = NSET;
    }

    private void setOccupancy(Boolean set){
        isOccupied = set;
    }

    public void setCell(Cell cell){
        containedCell = cell;
        containsCellType = cell.getType();
        if(cell.getType() == 1) {
            setOccupancy(false);
        }else{
            setOccupancy(true);
        }
    }

    public void createCell(String simulation, int type) { // refactor (make into smaller functions)
        Cell cellToSet;
        switch(simulation){
            case "Fire":
                cellToSet = fireSimulationCell(type);
                break;
            case "Game of Life":
                cellToSet = gameSimulationCell(type);
                break;
            case "Percolation":
                cellToSet = percolationSimulationCell(type);
                break;
            case "Prey":
                cellToSet = preySimulationCell(type);
                break;
            case "Segregation":
                cellToSet = segregationSimulationCell(type);
                break;
            case "Rps":
                cellToSet = rpsSimulationCell(type);
            default:
                cellToSet = new EmptyCell(this, SEGREGATIONFILL);
        }
        setCell(cellToSet);
    }

    private Cell fireSimulationCell(int type){
        if(type == 3){
            return new FireCell(this);
        }else if(type == 2){
            return new TreeCell(this, 0.15);
        }else{
            return new EmptyCell(this, FIREFILL);
        }
    }

    private Cell gameSimulationCell(int type){
        if(type == 2){
            return new LiveCell(this);
        }else{
            return new DeadCell(this);
        }
    }

    private Cell percolationSimulationCell(int type){
        if(type == 3){
            return new WaterCell(this);
        }else if(type == 2){
            return new AirCell(this);
        }else{
            return new EmptyCell(this, PERCOLATIONFILL);
        }
    }


    private Cell segregationSimulationCell(int type){
        if(type == 1){
            return new EmptyCell(this, SEGREGATIONFILL);
        }else{
            return new PersonCell(this, type, 0.5);
        }
    }


    private Cell preySimulationCell(int type){
        if(type == 3){
            return new PredatorCell(this, 10, 5);
        }else if(type == 2){
            return new AnimalCell(this, 1, 3);
        }else{
            return new EmptyCell(this, PREYFILL);
        }
    }

    private Cell rpsSimulationCell(int type){
        return new rpsCell(this, type, 4);
    }

    public Set<GridEntry> getNeighbors(){
        return NEIGHBORS;
    }


    public int getCellType(){
       return containsCellType;
    }

    public Cell getCell(){
        return containedCell;
    }

    public int getRow(){
        return ROW;
    }

    public int getColumn() { return COLUMN; }

}