package cellsociety;

import cellsociety.FireSimulation.FireCell;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


//When making a Grid Entry instance, be sure to first initialize the entire grid and then using a second pass use methods
//to fill in all the paramters
public abstract class GridEntry {

    private int containsCellType;
    private Cell containedCell;
    private Boolean isOccupied;
    private static Set<GridEntry> NEIGHBORS= new HashSet<GridEntry>();
    private static int[] ID = new int[2];

    public GridEntry(int row, int col, int type){
        initializeGridEntryID(row, col);
        createCell(type);
    }

    private void initializeGridEntryID(int row, int col){
        ID[0] = row;
        ID[1] = col;
    }

    private void setNeighbors(List<List<GridEntry>> grid){ //this is only applicable for simulations with 4 neighbors. Will adjust in a bit
        int numRows = XMLReader.getRows();
        int numCols = XMLReader.getColumns();

        if(ID[0]>1){
            GridEntry topNeighbor = grid.get(ID[0] -1).get(ID[1]);
            NEIGHBORS.add(topNeighbor);
        }if(ID[0]<numRows -1){
            GridEntry bottomNeighbor = grid.get(ID[0] +1).get(ID[1]);
            NEIGHBORS.add(bottomNeighbor);
        }if(ID[1]>1){
            GridEntry leftNeighbor = grid.get(ID[0]).get(ID[1]-1);
            NEIGHBORS.add(leftNeighbor);
        }if(ID[1]<numCols -1){
            GridEntry rightNeighbor = grid.get(ID[0]).get(ID[1]+1);
            NEIGHBORS.add(rightNeighbor);
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

    public abstract void createCell(int type);


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

    public int[] getID(){
        return ID;
    }


}