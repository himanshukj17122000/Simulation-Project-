package cellsociety.Configuration;

import cellsociety.GridEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Configuration {
    private String myTitle,type1,type2,type3;
    private ArrayList<String> probCatchLabel= new ArrayList<>();
    private ArrayList<Double> maxProb=new ArrayList<>();
    private ArrayList<Double> probCatch=new ArrayList<>();
    private int[] neighPattern= new int[8];
    private int maxStates,rows,columns,left,right,top,bottom, neighbours;
    public abstract void paraTitle(String title);

    private String getMyTitle(){
        return myTitle;
    }


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
                currentGridEntry.setNeighbors(grid, simulation, getRows(), getColumns(), getNeighPattern());
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
    public String getType1(){return type1;}
    public String getType2(){return type2;}
    public String getType3(){return type3;}
    public ArrayList<String> getProbCatchLabel(){return probCatchLabel;}
    public ArrayList<Double> getProbCatch(){return probCatch;}
    public ArrayList<Double> getMaxProb(){return maxProb;}
    public int[] getNeighPattern(){return neighPattern;}

    public void setMyTitle(String myTitle) { this.myTitle = myTitle; }
    public void setType1(String type1) { this.type1 = type1; }
    public void setType2(String type2) { this.type2 = type2; }
    public void setType3(String type3) { this.type3 = type3; }
    public void setProbCatchLabel(String label) { probCatchLabel.add(label); }
    public void setMaxProb(Double maxProbability) { maxProb.add(maxProbability); }
    public void setProbCatch(Double probValue) {
    probCatch.add(probValue); }
    public void setMaxStates(int maxStates) { this.maxStates = maxStates; }
    public void setRows(int rows) { this.rows = rows; }
    public void setColumns(int columns) { this.columns = columns; }
    public void setLeft(int left) { this.left = left; }
    public void setRight(int right) { this.right = right; }
    public void setTop(int top) { this.top = top; }
    public void setBottom(int bottom) { this.bottom = bottom; }
    public void setNeighbours(int neighbours) { this.neighbours = neighbours; }
    public void setNeighPattern(String pattern){
        char[] pattArray= pattern.toCharArray();
        for(int i=0;i<pattArray.length;i++){
            neighPattern[i]= (int) pattArray[i];
        }
    }
}
