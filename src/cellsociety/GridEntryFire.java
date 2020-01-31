package cellsociety;

import cellsociety.FireSimulation.FireCell;
import cellsociety.FireSimulation.TreeCell;

public class GridEntryFire extends GridEntry {

    public GridEntryFire(int row, int col, int type){
        super(row, col, type);
    }

    @Override
    public void createCell(int type) {
        Cell cellToSet;
        if(type == 3){
            cellToSet = new FireCell(this);
        }else if(type == 2){
            cellToSet = new TreeCell(this);
        }else{
            cellToSet = new EmptyCell(this);
        }

        setCell(cellToSet);
    }
}
