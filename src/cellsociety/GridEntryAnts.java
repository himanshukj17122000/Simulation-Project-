package cellsociety;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GridEntryAnts extends GridEntry {
    private List<Integer> ContainsCellType = new ArrayList<Integer>();
    private List<Cell> ContainedCell = new ArrayList<Cell>();
    private List<Cell> NextStepCell = new ArrayList<Cell>();
    private Set<GridEntry> NEIGHBORS;
    private int ROW;
    private int COLUMN;
    private int totROW;
    private int totCOLUMN;

    //method constructor for GridEntryAnts
    public GridEntryAnts(int row, int col, String simulation, int type) {
        super(row, col, simulation, type);
        ContainsCellType.add(1);
        Cell newEmpty = new EmptyCell(this, Color.WHITE);
        ContainedCell.add(newEmpty);
        System.out.println(ContainedCell.size());
        NextStepCell.add(newEmpty);
    }

// add a cell to the next step of the grid entry
    public void setNextStepCell(Cell nextCell) {
        NextStepCell.add(nextCell);
    }

    //get the last next step cell for the grid entry
    public Cell getNextStepCell() {
        return NextStepCell.get(NextStepCell.size()-1);
    }

    //get the next step cells for grid entry
    public List<Cell> getNextStepCellList(){
        return NextStepCell;
    }

    //update the entry to the next time step
    public void updateGridEntry() {
        if (getNextStepCell() == null) {
            setCell(getCell());
        } else {
            setCell(getNextStepCell());
        }
    }

    //add a cell to the grid entry
    @Override
    public void setCell(Cell cell){
        if(ContainsCellType == null){
            ContainsCellType = new ArrayList<>();
        }
        if(ContainedCell == null){
            ContainedCell = new ArrayList<>();
        }
        ContainsCellType.add(cell.getType());
        ContainedCell.add(cell);

    }



    //get the last cell type for this grid entry
    public int getCellType(){
        if(ContainsCellType.size() == 0){
            return 0;
        }else{
            return ContainsCellType.get(ContainsCellType.size()-1);
        }

    }

    //get a list of the cell types at this index
    public List<Integer> getCellTypeList(){
        return ContainsCellType;
    }

    //get last cell for this class
    public Cell getCell(){
        if(ContainsCellType.size() == 0){
            return null;
        }else{
            return ContainedCell.get(ContainedCell.size()-1);
        }
    }

    //get list method for gridentryant class
    public List<Cell> getCellList(){
        if(ContainedCell == null){
            ContainedCell = new ArrayList<>();
            return new ArrayList<>();
        }
        System.out.println(ContainedCell.size());
        return ContainedCell;
    }

}
