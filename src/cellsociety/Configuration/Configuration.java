package cellsociety.Configuration;

import java.util.ArrayList;

public abstract class Configuration {
    private String myTitle;
    private String type1;
    private String type2;
    private String type3;
    private String shape;
    private String randomLabel;
    private ArrayList<String> probCatchLabel= new ArrayList<>();
    private ArrayList<Double> maxProb=new ArrayList<>();
    private ArrayList<Double> probCatch=new ArrayList<>();
    private int[] neighPattern= new int[8];
    private int maxStates,rows,columns,left,right,top,bottom, neighbours;

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public abstract void paraTitle(String title);

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
    public void setProbCatch(Double probValue) { probCatch.add(probValue); }
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
            neighPattern[i]= (int) pattArray[i] - 48;
        }
    }
}
