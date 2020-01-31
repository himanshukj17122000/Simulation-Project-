package cellsociety.FireSimulation;

import cellsociety.Cell;
import cellsociety.GridEntry;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.HashSet;
import java.util.Set;

public class TreeCell extends Cell {
    private static final int TYPE = 2;
    public static final Paint FILL = Color.CHARTREUSE;
    private Boolean CANUPDATE = true;



    public TreeCell(GridEntry entry){
        super(FILL, entry);
    }

    @Override
    public int getType(){
        return TYPE;
    }

    public Boolean checkIfBurn(GridEntry entry){
        Set<GridEntry> neighborSet = getNeighbors(entry);
        Boolean neighborFire = false;
        for(GridEntry neighbor : neighborSet) {
            if (neighbor.getCell().getType() == 3) {
                neighborFire = true;
            }
        }
        return neighborFire;
        }
        

    }



