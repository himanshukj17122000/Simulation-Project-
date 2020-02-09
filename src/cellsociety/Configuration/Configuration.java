package cellsociety.Configuration;

import cellsociety.GridEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Configuration {
    private String myTitle;
    private String type1;
    private String type2;
    private String type3;
    private String shape;
    private String randomLabel;
    private String startingConfig;
    public final static String configRandom= "Random";
    public final static String initialShape="Rectangle";
    public final static int iniRows=50;
    public final static int iniCols=50;
    public final static String iniConc1="0.5,0.3";
    public final static String iniConcLife="0.5,0.5";
    public final static String iniConc2="0.3,0.3,0.4";
    public final static int noRow=0;
    public final static String neighbourPattern="10101111";
    public final static double defaultMaxProb=1;
    public final static double defaultProb=0.15;

    public String getStartingConfig() {
        return startingConfig;
    }

    public void setStartingConfig(String startingConfig) {
        this.startingConfig = startingConfig;

    }

    private ArrayList<String> probCatchLabel= new ArrayList<>();
    private ArrayList<Double> maxProb=new ArrayList<>();
    private ArrayList<Double> probCatch=new ArrayList<>();
    private int[] neighPattern= new int[8];
    private Double[] concentration= new Double[3];
    private int maxStates,rows,columns,left,right,top,bottom, neighbours,randomNumber,maxRandomNumber;


    public String getRandomLabel() {
        return randomLabel;
    }

    public void setRandomLabel(String randomLabel) {
        this.randomLabel = randomLabel;
    }
    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public int getMaxRandomNumber() {
        return maxRandomNumber;
    }

    public void setMaxRandomNumber(int maxRandomNumber) {
        this.maxRandomNumber = maxRandomNumber;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
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
    public Double[] getConcentration(){return concentration;}

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
            neighPattern[i]= (int) pattArray[i] - 48;
        }
    }
    public void setConcentration(String typeConcentration){
        String[] concen= typeConcentration.split(",");
        for(int i=0;i<concen.length;i++){
            concentration[i] = Double.parseDouble(concen[i]);
        }
    }

    public abstract void paraTitle(String title);
}
