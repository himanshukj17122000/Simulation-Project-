package cellsociety.Configuration;

import cellsociety.GridEntry;
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
    private static final String prey = "getPrey";
    private static final String percolation = "getPercolation";
    private static final String game="getGame";
    private static final String segregation="getSegregation";
    private static final String fire="getFire";


    public static void config(Map<String, String> maps) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];//maybe this number needs to be corrected
        String methodName = e.getMethodName();
        switch (methodName) {
            case prey:
                preyClass = new Prey(maps);
                break;
            case game:
                gameClass = new Game(maps);
                break;
            case fire:
                fireClass = new Fire(maps);
                break;
            case percolation:
                perClass = new Percolation(maps);
                break;
            case segregation:
                segClass = new Segregation(maps);
                break;
        }
   }

    protected String myTitle,type1,type2,type3,probCatchLabel;
    protected int maxStates,rows,columns,left,right,top,bottom, neighbours,fishBreed,sharkBreed;
    protected double probCatch;
    public abstract void paraTitle(String title);

    private String getMyTitle(){
        return myTitle;
    }

    public static Prey getPreyClass(){ return preyClass; }
    public static Game getGameClass(){ return gameClass; }
    public static Fire getFireClass(){ return fireClass; }
    public static Percolation getPerClass(){ return perClass; }
    public static Segregation getSegClass(){ return segClass; }

    public List<List<GridEntry>> makeCellGrid() {  // initialization of a grid of empty cells
        List<List<GridEntry>> grid = new ArrayList<>();
        for (int r = 0; r < this.getRows(); r++) {
            List<GridEntry> insertRow = new ArrayList<>();
            for (int c = 0; c < this.getColumns(); c++) {
                GridEntry insertGridEntry = createBorderGridEntry(c, r, this.getTitle());
                if (insertGridEntry == null) {
                    insertGridEntry = randomizeGridEntry(r, c, this.getTitle());
                }
                insertRow.add(insertGridEntry);
            }
            grid.add(insertRow);
        }
        initializeGridNeighbors(grid, this.getTitle());
        return grid;
    }

    public void initializeGridNeighbors(List<List<GridEntry>> grid, String simulation) {
        for (int r = 0; r < this.getRows(); r++) {
            for (int c = 0; c < this.getColumns(); c++) {
                GridEntry currentGridEntry = grid.get(r).get(c);
                currentGridEntry.setNeighbors(grid, simulation, getRows(), getColumns());
            }
        }
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

    private int getRandomNumberInRange() {
        Random r = new Random();
        return r.nextInt(this.getMaxStates())+1;
    }

    private GridEntry randomizeGridEntry(int row, int col, String simulation) {
        int randomType = getRandomNumberInRange();
        return new GridEntry(row, col, simulation, randomType);
    }

    public String getTitle(){return myTitle;}
    public int getMaxStates(){return maxStates;}
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
    public int getFishBreed(){return fishBreed;}
    public int getSharkBreed(){return sharkBreed;}
    public String getProbCatchLabel(){return probCatchLabel;}

    public void setProbCatch(Double newVal){
        probCatch=newVal;
    }
}
