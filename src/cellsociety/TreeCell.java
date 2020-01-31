package cellsociety;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.HashSet;
import java.util.Set;

public class TreeCell extends Cell{
    private static String TYPE = "TREE";
    public static final Paint FILL = Color.CHARTREUSE;
    private Boolean CANUPDATE = true;
    private Set<int[]> NEIGHBORS= new HashSet<int[]>();
    private int[] ID = new int[2];


    public TreeCell(double startX, double startY, int width, int height){
        super(startX, startY, width, height, FILL);
    }

    private void initializeNeighbors(){
        int[] cellToAdd = new int[2];
        if(ID[0] > 1){
            cellToAdd[0] = ID[0] -1;
            cellToAdd[1] = ID[1];
            NEIGHBORS.add(cellToAdd);
        }if(ID[0])
    }


}
