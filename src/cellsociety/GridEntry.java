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
import cellsociety.antSimulation.foodCell;
import cellsociety.antSimulation.nestCell;
import cellsociety.antSimulation.pCell;
import cellsociety.rPSSimulation.rpsCell;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//When making a Grid Entry instance, be sure to first initialize the entire grid and then using a second pass use methods
//to fill in all the parameters
//Also maybe change ID/ location thing to just be

/**
 * GridEntry is a class which is the container that contains cells
 * @author Olga Suchankova
 */
public class GridEntry {

    private int containsCellType;
    private Cell containedCell;
    private Cell nextStepCell = null;
    private Set<GridEntry> NEIGHBORS;
    private int ROW;
    private int COLUMN;
    private int totROW;
    private int totCOLUMN;
    private static final Paint FIREFILL = Color.WHITE; //fills for empty cells, put these variables somewhere elseeee
    private static final Paint PERCOLATIONFILL = Color.DARKGRAY;
    private static final Paint PREYFILL = Color.web("71add3");
    private static final Paint SEGREGATIONFILL = Color.WHITE;

    //method constructor for GridEntry
    public GridEntry(int row, int col, String simulation, int type) {
        initializeGridEntryID(row, col);
        createCell(simulation, type);
    }

    //initializer for the grid entry location
    private void initializeGridEntryID(int row, int col) {
        ROW = row;
        COLUMN = col;
    }

    //sets the next step of the simulation
    public void setNextStepCell(Cell nextCell) {
        nextStepCell = nextCell;
    }

    //gets the cell at the next step of the simulation
    public Cell getNextStepCell() {
        return nextStepCell;
    }

    //updates the grid entry to the next step of the simulation
    public void updateGridEntry() {
        if (getNextStepCell() == null) {
            setCell(getCell());
        } else {
            setCell(getNextStepCell());
            setNextStepCell(null);
        }
    }

    //initializes the neighbors of the grid entry
    public void setNeighbors(List<List<GridEntry>> grid, int numRows, int numCols, int[] neighborBool, String shape, String boundary) {
        totROW = numRows;
        totCOLUMN = numCols;
        Set<GridEntry> NSET = new HashSet<GridEntry>();

        System.out.println(neighborBool[1]);
        if (shape.equals("Rectangle")) {
            rectangleNeighbors(grid, neighborBool, NSET, boundary);
        } else if (shape.equals("Hexagon")) {
            hexagonNeighbors(grid, neighborBool, NSET);
        }
    }

    private void hexagonNeighbors(List<List<GridEntry>> grid, int[] neighborBool, Set<GridEntry> NSET) {
        if (getRow() > 0) {
            if (neighborBool[0] == 1) {
                if (getColumn() >  0 && getRow() % 2 == 0) {
                    NSET.add(grid.get(getRow()-1).get(getColumn()-1));
                }else{
                    NSET.add(grid.get(getRow()-1).get(getColumn()));
                }
            }
            if (neighborBool[1] == 1) {
                if (getColumn() < totCOLUMN - 1 && getRow() % 2 == 1) {
                    NSET.add(grid.get(getRow()-1).get(getColumn()+1));
                }else{
                    NSET.add(grid.get(getRow()-1).get(getColumn()));
                }
            }
        }

        if (getRow() < totROW - 1) {
            if (neighborBool[4] == 1) {
                if (getColumn() >  0 && getRow() % 2 == 0) {
                    NSET.add(grid.get(getRow()+1).get(getColumn()-1));
                }else{
                    NSET.add(grid.get(getRow()+1).get(getColumn()));
                }
            }
            if (neighborBool[3] == 1) {
                if (getColumn() < totCOLUMN - 1 && getRow() % 2 == 1) {
                    NSET.add(grid.get(getRow()+1).get(getColumn()+1));
                }else{
                    NSET.add(grid.get(getRow()+1).get(getColumn()));
                }
            }
        }
        if (getColumn() > 0 && neighborBool[2] == 1) {
            NSET.add(grid.get(getRow()).get(getColumn()-1));
        }
        if (getColumn() < totCOLUMN - 1 && neighborBool[5] == 1) {
            NSET.add(grid.get(getRow()).get(getColumn()+1));
        }
        NEIGHBORS = NSET;
    }

    private void rectangleNeighbors(List<List<GridEntry>> grid, int[] neighborBool, Set<GridEntry> NSET, String boundary){
        if(neighborBool[1] == 1) {
            if (getRow() > 0) {
                NSET.add(grid.get(getRow() - 1).get(getColumn()));
            }else if(boundary.equals("toroidal")){
                NSET.add(grid.get(totROW -1).get(getColumn()));
            }
        }
        if(neighborBool[5] == 1){
            if (getRow() < totROW - 1) {
                NSET.add(grid.get(getRow() + 1).get(getColumn()));
            }else if(boundary.equals("toroidal")){
                NSET.add(grid.get(0).get(getColumn()));
            }
        }
        if(neighborBool[3] == 1) {
            if (getColumn() > 0) {
                NSET.add(grid.get(getRow()).get(getColumn() - 1));
            }else if(boundary.equals("toroidal")){
                NSET.add(grid.get(getRow()).get(totCOLUMN-1));
            }
        }
        if(neighborBool[7] == 1) {
            if (getColumn() < totCOLUMN - 1) {
                NSET.add(grid.get(getRow()).get(getColumn() + 1));
            }else if(boundary.equals("toroidal")){
                NSET.add(grid.get(getRow()).get(0));
            }
        }
        if(neighborBool[0] == 1){
            if (getColumn() > 0 && getRow() > 0) {
                NSET.add(grid.get(getRow() - 1).get(getColumn() - 1));
            }else if(boundary.equals("toroidal")){
                NSET.add(grid.get(totROW-1).get(totCOLUMN-1));
            }
        }
        if(neighborBool[2] == 1) {
            if (getColumn() < totCOLUMN -1 && getRow()>0) {
                NSET.add(grid.get(getRow() - 1).get(getColumn() + 1));
            }else if(boundary.equals("toroidal")){
                NSET.add(grid.get(totROW-1).get(0));
            }
        }
        if(neighborBool[6] == 1){
            if (getColumn() > 0 && getRow() < totROW - 1) {
                NSET.add(grid.get(getRow() + 1).get(getColumn() - 1));
            }else if(boundary.equals("toroidal")){
                NSET.add(grid.get(0).get(totCOLUMN-1));
            }
        }
        if(neighborBool[6] == 1){
            if (getColumn() < totCOLUMN - 1 && getRow() < totROW - 1) {
                NSET.add(grid.get(getRow() + 1).get(getColumn() + 1));
            }else if(boundary.equals("toroidal")){
                NSET.add(grid.get(0).get(0));
            }
        }
        NEIGHBORS = NSET;
    }


//set the cell of the grid entry
    public void setCell(Cell cell){
        containedCell = cell;
        containsCellType = cell.getType();
    }
//createaCell to go in the grid entry for initialization
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
                break;
            case "Ant":
                cellToSet = antSimulationCell(type);
                break;
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
        return new rpsCell(this, type, 2, 1);
    }

    private Cell antSimulationCell(int type){
        if(type == 3){
            return new nestCell(this);
        }else if(type == 2){
            return new foodCell(this);

        }else{
            return new pCell(this);

        }
    }
//get the neighbors of the grid entry
    public Set<GridEntry> getNeighbors(){
        return NEIGHBORS;
    }

    //get the cell type of the grid entry
    public int getCellType(){
       return containsCellType;
    }

    //get the cell of the grid entry
    public Cell getCell(){
        return containedCell;
    }

    //get the row of the grid entry
    public int getRow(){
        return ROW;
    }

    //get the column of the grid entry
    public int getColumn() { return COLUMN; }

    //method to override by subclass
    public List<Cell> getCellList(){
        return null;
    }
    //method to override by subclass
    public List<Integer> getCellTypeList(){
        return null;
    }
    //method to override by subclass
    public List<Cell> getNextStepCellList(){
        return null;
    }


}