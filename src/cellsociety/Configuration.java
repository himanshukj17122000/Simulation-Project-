package cellsociety;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Configuration {
    private static final int mySize=400;
    private static XMLReader myReader;
    private static int myNumRows, myNumColumns, myProbCatch, myNumStates,myIsLeftPresent, myIsRightPresent, myIsTopPresent, myIsBottomPresent;
    private static ArrayList<Integer> myParameters;

//    private static Cell[] myGridArray;

    public Configuration(){
        checkprob();
        myNumRows = myReader.getRows();
        myNumStates = myReader.levels();
        myNumColumns = myReader.getColumns();
        myIsLeftPresent= myReader.getLeft();
        myIsRightPresent = myReader.getRight();
        myIsTopPresent = myReader.getTop();
        myIsBottomPresent = myReader.getBottom();
        myParameters.add(myNumRows);
        myParameters.add(myNumColumns);
        myParameters.add(myIsBottomPresent);
        myParameters.add(myIsLeftPresent);
        myParameters.add(myIsTopPresent);
        myParameters.add(myIsRightPresent);
//        makeCells();
    }

    private void checkprob() {
        if(myReader.getProbCatch()!=-1){
            myProbCatch = myReader.getProbCatch();
            myParameters.add(myProbCatch);
        }
    }

    public ArrayList<Integer> getParams(){
        return myParameters;
    }

//    public void makeCells(){
//        Cell newCell= new Cell(getRandomNumberInRange());
//        List<Cell> arrlist= new ArrayList<Cell>(Arrays.asList(myGridArray));
//        arrlist.add(newCell);
//        myGridArray = arrlist.toArray(myGridArray);
//        parameters.add(myGridArray);
//    }

    private static int getRandomNumberInRange() {
        Random r = new Random();
        return r.nextInt(myNumStates + 1);
    }

    public static List<List<GridEntry>> makeCellGrid(){  // initialization of a grid of empty cells
        List<List<GridEntry>> grid = new ArrayList<>();
        for(int r = 0; r<myNumRows; r++){
            List<GridEntry> insertRow = new ArrayList<>();
            for(int c = 0; c<myNumColumns; c++){
                GridEntry insertGridEntry = new GridEntry(r, c);
                //checkBorderConfig(insertGridEntry);
                EmptyCell insertCell = new EmptyCell(insertGridEntry);
                insertGridEntry.setCell(insertCell);
                insertRow.add(insertGridEntry);
            }
            grid.add(insertRow);
        }
        return grid;
    }

    private void checkBorderConfig(int row, int col, GridEntry gridEntry) {
        if (myIsBottomPresent != 0 && row == myNumRows + 1) {
            gridEntry.createCell(myIsBottomPresent);
        }
        if (myIsTopPresent != 0 && row == 0) {
            gridEntry.createCell(myIsTopPresent);
        }
        if (myIsLeftPresent != 0 && col == 0) {
            gridEntry.createCell(myIsRightPresent);
        }
        if (myIsRightPresent != 0 && col = myNumColumns + 1) {
            gridEntry.createCell(myIsRightPresent);
        }
    }