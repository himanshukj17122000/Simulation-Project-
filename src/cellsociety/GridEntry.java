package cellsociety;

import cellsociety.FireSimulation.EmptyCell;
import cellsociety.FireSimulation.FireCell;
import cellsociety.FireSimulation.TreeCell;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


//When making a Grid Entry instance, be sure to first initialize the entire grid and then using a second pass use methods
//to fill in all the parameters
//Also maybe change ID/ location thing to just be
public class GridEntry {

    private int containsCellType;
    private Cell containedCell;
    private Boolean isOccupied;
    private static Set<GridEntry> NEIGHBORS= new HashSet<GridEntry>();
    private static int ROW;
    private static int COLUMN;

    public GridEntry(int row, int col, String simulation, int type){
        initializeGridEntryID(row, col);
        createCell(simulation, type);
    }

    private void initializeGridEntryID(int row, int col){
        ROW = row;
        COLUMN = col;
    }

    private void setNeighbors(List<List<GridEntry>> grid, String simulation){ //this is only applicable for simulations with 4 neighbors. Will adjust in a bit
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

    public void createCell(String simulation, int type) {
        Cell cellToSet;
        switch(simulation){
            case "FIRE":
                if(type == 3){
                cellToSet = new FireCell(this);
                }else if(type == 2){
                cellToSet = new TreeCell(this);
                }else{
                cellToSet = new EmptyCell(this);
                }
                break;

            default:
                cellToSet = new EmptyCell(this);
        }

        setCell(cellToSet);
    }


    public Set<GridEntry> getNeighbors(){
        return NEIGHBORS;
    }


    public Boolean getOccupancy(){
        return isOccupied;
    }

    public int getContainsCellType(){
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