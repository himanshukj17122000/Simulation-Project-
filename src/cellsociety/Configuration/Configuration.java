package cellsociety.Configuration;

import cellsociety.XMLReader;

import java.util.ArrayList;
import java.util.Random;

public class Configuration {
//    private static final int mySize=400;
//    private static XMLReader myReader;
//    private static int myNumRows, myNumColumns, myProbCatch, myNumStates,myIsLeftPresent, myIsRightPresent, myIsTopPresent, myIsBottomPresent;;
//    private static ArrayList<Integer> myParameters;
    Object simulation;

//    private static Cell[] myGridArray;

    public Configuration(Object file){
       simulation=file;
    }
    public Object getSimulation(){
        return simulation;
    }

//    private void checkprob() {
//        if(myReader.getProbCatch()!=-1){
//            myProbCatch = myReader.getProbCatch();
//            myParameters.add(myProbCatch);
//        }
//    }
//
//    public ArrayList getParams(){
//        return myParameters;
//    }
//
////    public void makeCells(){
////        Cell newCell= new Cell(getRandomNumberInRange());
////        List<Cell> arrlist= new ArrayList<Cell>(Arrays.asList(myGridArray));
////        arrlist.add(newCell);
////        myGridArray = arrlist.toArray(myGridArray);
////        parameters.add(myGridArray);
////    }
//
//    private static int getRandomNumberInRange() {
//        Random r = new Random();
//        return r.nextInt(myNumStates + 1);
//    }


}

