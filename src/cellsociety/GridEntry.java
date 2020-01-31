package cellsociety;

import java.util.HashSet;
import java.util.Set;

public class GridEntry {
    private String containsCellType;
    private Cell containedCell;
    private Boolean isOccupied;
    private static Set<int[]> NEIGHBORS= new HashSet<int[]>();
    private static int[] ID = new int[2];

    public GridEntry(int row, int col){
        initializeGridEntryID(row, col);
        initializeNeighbors();
    }

    private void initializeGridEntryID(int row, int col){
        ID[0] = row;
        ID[1] = col;
    }

    private void initializeNeighbors(){ //this is only applicable for simulations with 4 neighbors. Will adjust in a bit
        int numRows = XMLReader.getRows();
        int numCols = XMLReader.getColumns();

        if(ID[0]>1){
            int[] topNeighbor = {ID[0]-1, ID[1]};
            NEIGHBORS.add(topNeighbor);
        }if(ID[0]<numRows -1){
            int[] bottomNeighbor = {ID[0]+1, ID[1]};
            NEIGHBORS.add(bottomNeighbor);
        }if(ID[1]>1){
            int[] leftNeighbor = {ID[0], ID[1]-1};
            NEIGHBORS.add(leftNeighbor);
        }if(ID[1]<numCols -1){
            int[] rightNeighbor = {ID[0], ID[1]+1};
            NEIGHBORS.add(rightNeighbor);
        }
    }


    private void setOccupancy(Boolean set){
        isOccupied = set;
    }

    public void setCell(Cell cell){
        containedCell = cell;
        containsCellType = cell.getType();
        if(cell.getType().equals("EMPTY")) {
            setOccupancy(false);
        }else{
            setOccupancy(true);
        }
    }

    public Set<int[]> getNeighbors(){
        return NEIGHBORS;
    }

    public Boolean getOccupancy(){
        return isOccupied;
    }

    public String getContainsCellType(){
       return containsCellType;
    }

    public Cell getCell(){
        return containedCell;
    }
}