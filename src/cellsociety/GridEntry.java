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

import java.util.HashSet;
import java.util.List;
import java.util.Set;


//When making a Grid Entry instance, be sure to first initialize the entire grid and then using a second pass use methods
//to fill in all the parameters
//Also maybe change ID/ location thing to just be
public class GridEntry {

    private int containsCellType;
    private Cell containedCell;
    private boolean isOccupied;
    private static Set<GridEntry> NEIGHBORS= new HashSet<GridEntry>();
    private static int ROW;
    private static int COLUMN;
    private static final Paint FIREFILL = Color.WHITE; //fills for empty cells, put these variables somewhere elseeee
    private static final Paint PERCOLATIONFILL = Color.DARKGRAY;
    private static final Paint PREYFILL = Color.PALEGREEN;
    private static final Paint SEGREGATIONFILL = Color.WHITE;

    public GridEntry(int row, int col, String simulation, int type){
        initializeGridEntryID(row, col);
        createCell(simulation, type);
    }

    private void initializeGridEntryID(int row, int col){
        ROW = row;
        COLUMN = col;
    }

    private void setNeighbors(List<List<GridEntry>> grid, String simulation){ //refactor to be 20 lines
        int numRows = XMLReader.getRows();
        int numCols = XMLReader.getColumns();

        if(ROW>1){
            GridEntry topNeighbor = grid.get(ROW -1).get(COLUMN);
            NEIGHBORS.add(topNeighbor);
        }if(ROW<numRows -1){
            GridEntry bottomNeighbor = grid.get(ROW +1).get(COLUMN);
            NEIGHBORS.add(bottomNeighbor);
        }if(COLUMN>1){
            GridEntry leftNeighbor = grid.get(ROW).get(COLUMN-1);
            NEIGHBORS.add(leftNeighbor);
        }if(COLUMN<numCols -1){
            GridEntry rightNeighbor = grid.get(ROW).get(COLUMN+1);
            NEIGHBORS.add(rightNeighbor);
        } if(simulation.equals("FIRE") || simulation.equals("PERCOLATION") || simulation.equals("GAME")){
            if(ROW>1){
                if(COLUMN>1){
                    GridEntry topLeftNeighbor = grid.get(ROW-1).get(COLUMN-1);
                    NEIGHBORS.add(topLeftNeighbor);
                } if(COLUMN< numCols -1){
                    GridEntry topRightNeighbor = grid.get(ROW-1).get(COLUMN+1);
                    NEIGHBORS.add(topRightNeighbor);
                }
            }if(ROW < numRows -1){
                if(COLUMN>1){
                    GridEntry bottomLeftNeighbor = grid.get(ROW+1).get(COLUMN-1);
                    NEIGHBORS.add(bottomLeftNeighbor);
                } if(COLUMN < numCols -1){
                    GridEntry bottomRightNeighbor = grid.get(ROW+1).get(COLUMN+1);
                    NEIGHBORS.add(bottomRightNeighbor);
                }
            }

        }
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
            case "Prey-Predator":
                cellToSet = preySimulationCell(type);
                break;
            case "Segregation":
                cellToSet = segregationSimulationCell(type);
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
            return new TreeCell(this);
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
            return new PersonCell(this, type);
        }
    }

    private Cell preySimulationCell(int type){
        if(type == 3){
            return new PredatorCell(this);
        }else if(type == 2){
            return new AnimalCell(this);
        }else{
            return new EmptyCell(this, PREYFILL);
        }
    }

    public Set<GridEntry> getNeighbors(){
        return NEIGHBORS;
    }

    public Boolean getIsOccupied(){
        return isOccupied;
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