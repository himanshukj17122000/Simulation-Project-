package cellsociety.Configuration;

import java.util.List;
import java.util.Map;

public class Prey extends Configuration {

    // name in data file that will indicate it represents data for this type of object
    public static final String DATA_TYPE = "Game of Life";
    // field names expected to appear in data file holding values for this object
    // NOTE: simple way to create an immutable list
    public static final List<String> DATA_FIELDS = List.of("title", "maxstates", "rows","columns","left","Right","Top","Bottom","neighbours","type1","type2","type3");



//    // specific data values for this instance
    //private String myTitle,type1,type2,type3;
   //private int maxStates,rows,columns,neighbours,left,right,top,bottom;

//        // NOTE: keep just as an example for converting toString(), otherwise not used
//        private Map<String, String> myDataValues;


    /**
     * Create game data from given data.
     */
    @Override
    public void paraTitle (String title) {
        myTitle = title;
        // NOTE: this is useful so our code does not fail due to a NullPointerException
//            myDataValues = new HashMap<>();
    }

    /**
     * Create game data from data structure of Strings.
     *
     * @param dataValues map of field names to their values
     */
    @Override
    public void setParameters (Map<String, String> dataValues) {
        myTitle=dataValues.get(DATA_FIELDS.get(0));
        maxStates=Integer.parseInt(DATA_FIELDS.get(1));
        rows=Integer.parseInt(DATA_FIELDS.get(2));
        columns=Integer.parseInt(DATA_FIELDS.get(3));
        left=Integer.parseInt(DATA_FIELDS.get(4));
        right=Integer.parseInt(DATA_FIELDS.get(5));
        top=Integer.parseInt(DATA_FIELDS.get(6));
        bottom=Integer.parseInt(DATA_FIELDS.get(7));
        neighbours=Integer.parseInt(DATA_FIELDS.get(8));
        type1=dataValues.get(DATA_FIELDS.get(9));
        type2=dataValues.get(DATA_FIELDS.get(10));
        type3=dataValues.get(DATA_FIELDS.get(11));
    }

    // NOTE: provides getters, but not setters
//    /**
//     * Returns title of this game.
//     */
//    public String getTitle () {
//        return myTitle;
//    }
//    public int getMax(){return maxStates;}
//    public int getRows(){return rows;}
//    public int getColumns(){return columns;}
//    public String getLeft(){return left;}
//    public String getRight(){return right;}
//    public String getTop(){return top;}
//    public String getBottom(){return bottom;}
//    public int getNeighbours(){return neighbours;}
//    public String getType1(){return type1;}
//    public String getType2(){return type2;}
//    public String getType3(){return type3;}
}

