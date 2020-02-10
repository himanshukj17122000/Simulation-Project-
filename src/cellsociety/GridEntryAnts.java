package cellsociety;

import java.util.List;
import java.util.Set;

public class GridEntryAnts extends GridEntry {
    private List<Integer> containsCellType;
    private List<Cell> containedCell;
    private List<Cell> nextStepCell = null;
    private Set<GridEntry> NEIGHBORS;
    private int ROW;
    private int COLUMN;
    private int totROW;
    private int totCOLUMN;

    public GridEntryAnts(int row, int col, String simulation, int type) {
        super(row, col, simulation, type);
    }

    private void initializeGridEntryID(int row, int col) {
        ROW = row;
        COLUMN = col;
    }

    public void setNextStepCell(Cell nextCell) {
        nextStepCell.add(nextCell);
    }

    public Cell getNextStepCell() {
        return nextStepCell.get(nextStepCell.size()-1);
    }

    public List<Cell> getNextStepCellList(){
        return nextStepCell;
    }

    public void updateGridEntry() {
        if (getNextStepCell() == null) {
            setCell(getCell());
        } else {
            setCell(getNextStepCell());
        }
    }


    public void setCell(Cell cell){
        containedCell.add(cell);
        containsCellType.add(cell.getType());
    }



    public int getCellType(){
        return containsCellType.get(containsCellType.size()-1);
    }

    public List<Integer> getCellTypeList(){
        return containsCellType;
    }

    public Cell getCell(){
        return containedCell.get(containedCell.size()-1);
    }

    public List<Cell> getCellList(){
        return containedCell;
    }

    public int getRow(){
        return ROW;
    }

    public int getColumn() { return COLUMN; }
}
