package cellsociety;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.HashSet;
import java.util.Set;

public class TreeCell extends Cell{
    private static final String TYPE = "TREE";
    public static final Paint FILL = Color.CHARTREUSE;
    private Boolean CANUPDATE = true;
    private int[] Location = new int[2];


    public TreeCell(GridEntry entry){
        super(FILL, entry);
    }

    @Override
    public String getType(){
        return TYPE;
    }


}
