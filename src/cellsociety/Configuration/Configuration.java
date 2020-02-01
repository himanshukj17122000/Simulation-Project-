package cellsociety.Configuration;

import cellsociety.GridEntry;
import cellsociety.XMLReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Configuration {
//    private static final int mySize=400;
//    private static XMLReader myReader;
    private String myTitle,left,right,top,bottom,type1,type2,type3;
    private int maxStates,rows,columns,neighbours,probCatch;

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
    private int getRandomNumberInRange() {
        Random r = new Random();
        return r.nextInt(this.getMax() + 1);
    }

    public List<List<GridEntry>> makeCellGrid() {  // initialization of a grid of empty cells
        List<List<GridEntry>> grid = new ArrayList<>();
        for (int r = 0; r < this.getRows(); r++) {
            List<GridEntry> insertRow = new ArrayList<>();
            for (int c = 0; c < this.getColumns(); c++) {
                GridEntry insertGridEntry = createBorderGridEntry(r, c, this.getTitle());
                if (insertGridEntry == null) {
                    insertGridEntry = randomizeGridEntry(r, c, this.getTitle());
                }
                insertRow.add(insertGridEntry);
            }
            grid.add(insertRow);
        }
        return grid;
    }

    private GridEntry createBorderGridEntry(int row, int col, String simulation) {
        if (this.getBottom() != 0 && row == this.getRows() + 1) {
            return new GridEntry(row, col, simulation, this.getBottom());
        }
        else if (this.getTop() != 0 && row == 0) {
            return new GridEntry(row, col, simulation, this.getTop());
        }
        else if (this.getLeft() != 0 && col == 0) {
            return new GridEntry(row, col, simulation, this.getLeft());
        }
        else if (this.getRight() != 0 && col == this.getColumns() + 1) {
            return new GridEntry(row, col, simulation, this.getRight());
        }
        return null;
    }

    private static GridEntry randomizeGridEntry(int row, int col, String simulation) {
        int randomType = getRandomNumberInRange();
        return new GridEntry(row, col, simulation, randomType);
    }

    public String getTitle(){return myTitle;}
    public int getMax(){return maxStates;}
    public int getRows(){return rows;}
    public int getColumns(){return columns;}
    public int getLeft(){return left;}
    public int getRight(){return right;}
    public int getTop(){return top;}
    public int getBottom(){return bottom;}
    public int getNeighbours(){return neighbours;}
    public int getProbCatch(){return probCatch;}
    public String getType1(){return type1;}
    public String getType2(){return type2;}
    public String getType3(){return type3;}
}
