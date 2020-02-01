package cellsociety.Configuration;

import cellsociety.GridEntry;
import cellsociety.XMLReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Configuration {
    private static Prey preyClass;
    private static Game gameClass;
    private static Fire fireClass;
    private static Percolation perClass;
    private static Segregation segClass;

//    private static final int mySize=400;
//    private static XMLReader myReader;

//    private static int myNumRows, myNumColumns, myProbCatch, myNumStates,myIsLeftPresent, myIsRightPresent, myIsTopPresent, myIsBottomPresent;;
//    private static ArrayList<Integer> myParameters;
//


  public static void config(Map<String, String> maps) {
       StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
       StackTraceElement e = stacktrace[2];//maybe this number needs to be corrected
       String methodName = e.getMethodName();
       if(methodName.equals("getPrey")){
          preyClass= new Prey(maps);
       }
       else if(methodName.equals("getGame")){
           gameClass= new Game(maps);
       }
       else if(methodName.equals("getFire")){
           fireClass= new Fire(maps);
       }
       else if(methodName.equals("getPercolation")){
           perClass= new Percolation(maps);
       }
       else if(methodName.equals("getSegregation")){
           segClass= new Segregation(maps);
       }
   }

    protected String myTitle,type1,type2,type3;
    protected int maxStates,rows,columns,left,right,top,bottom, neighbours;
    protected double probCatch;

    public abstract void paraTitle(String title);
    public Prey getPreyClass(){
        return preyClass;
    }

    public Game getGameClass(){
        return gameClass;
    }

    public Fire getFireClass(){
        return fireClass;
    }

    public Percolation getPerClass(){
        return perClass;
    }

    public Segregation getSegClass(){
        return segClass;
    }


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

    private GridEntry randomizeGridEntry(int row, int col, String simulation) {
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
    public double getProbCatch(){return probCatch;}
    public String getType1(){return type1;}
    public String getType2(){return type2;}
    public String getType3(){return type3;}
}
