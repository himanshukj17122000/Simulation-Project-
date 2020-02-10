package cellsociety.Configuration;

import java.util.ArrayList;
public abstract class Configuration {
    private String myTitle;
    private String type1;
    private String type2;
    private String type3;
    private String shape;
    private String randomLabel;
    private String startingConfig;
    private String Myboundary;
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
    private String neighbourPatterninString;
    private String concentrate;

    public String getStartingConfig() {
        return startingConfig;
    }


    private ArrayList<String> probCatchLabel= new ArrayList<>();
    private ArrayList<Double> maxProb=new ArrayList<>();
    private ArrayList<Double> probCatch=new ArrayList<>();
    private int[] neighPattern= new int[8];

    public ArrayList<String> getColors() {
        return colors;
    }



    private ArrayList<String> colors= new ArrayList<>();
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


    public String getTitle(){return myTitle;}
    public String getMyboundary(){return Myboundary;}
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


    public void setMyShape(String s, String initialShape) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }this.shape=s;} catch (Exception e) {
            this.shape=initialShape;
        }
    }
    public void setMyProbMax(String s, String s1) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }maxProb.add(Double.parseDouble(s));} catch (NumberFormatException e) {
            maxProb.add(Double.parseDouble(s1));
        }
    }


    public String getConcentrate() {
        return concentrate;
    }

    public void setConcentrate(String concentrate) {
        this.concentrate = concentrate;
    }

    public void setMyConcentration(String s, String iniConc1) {
        try{ if(isStringOnlyAlphabet(s)){
            throw new Exception();
        }String[] concen= s.split(",");
            concentrate=s;
            for(int i=0;i<concen.length;i++){
                concentration[i] = Double.parseDouble(concen[i]);
            }} catch (Exception e) {
            String[] concen= iniConc1.split(",");
            concentrate=iniConc1;
            for(int i=0;i<concen.length;i++){
                concentration[i] = Double.parseDouble(concen[i]);
            }
        }
    }



    public void setMyNeighPattern(String s, String neighbourPattern) {
        try{ if(hasNumbers(s)){
            throw new Exception();
        }char[] pattArray= s.toCharArray();
            neighbourPatterninString=s;
            for(int i=0;i<pattArray.length;i++){
                neighPattern[i]= (int) pattArray[i] - 48;
            }} catch (Exception e) {
            char[] pattArray= neighbourPattern.toCharArray();
            neighbourPatterninString=neighbourPattern;
            for(int i=0;i<pattArray.length;i++){
                neighPattern[i]= (int) pattArray[i] - 48;
            }
        }
    }

    public String getNeighPatterninString(){
        return neighbourPatterninString;
    }


    public void setMyColors(String s, String s1) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }String[] separateColors= s.split(",");
            for (String colour:separateColors){
                colors.add(colour);
            }}catch (Exception e){
            String[] separateColors= s1.split(",");
            for (String colour:separateColors){
                colors.add(colour);
            }
        }
    }

    public void setMyStartingConfig(String s, String configRandom) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }   this.startingConfig = s;}catch (Exception e){
            this.startingConfig = configRandom;
        }
    }


    public void setMyTitle(String myTitle) { this.myTitle = myTitle; }



    public void setMyProbCatchLabel(String s, String probability_of_catching_on_fire) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }probCatchLabel.add(s);} catch (Exception e) {
            probCatchLabel.add(probability_of_catching_on_fire);;
        }
    }

    public void setMyType3(String s, String tree) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }this.type3=s;} catch (Exception e) {
            this.type3=tree;
        }
    }

    public void setMyType2(String s, String tree) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }this.type2=s;} catch (Exception e) {
            this.type2=tree;
        }
    }
    public void setMyType1(String s, String tree) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }
            this.type1=s;} catch (Exception e) {
            this.type1=tree;
        }
    }

    public void setMyStates(String s, int def) {
        try {
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }
            this.maxStates=Integer.parseInt(s);

        } catch (NumberFormatException e) {
            this.maxStates=def;
        }
    }

    public void setMyProbCatch(String s, double defaultProb) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }probCatch.add(Double.parseDouble(s));} catch (NumberFormatException e) {
            probCatch.add(defaultProb);
        }
    }

    public void setMyDimensions(String columns, String rows) {
        try {
            if(isStringOnlyAlphabet(columns) || isStringOnlyAlphabet(rows)){
                throw new NumberFormatException();
            }
            var i = Integer.parseInt(rows) / Integer.parseInt(columns);
            if(i <0.83 || i >1.2 ){
                throw new NumberFormatException();
            }
            this.rows= Integer.parseInt(rows);
            this.columns= Integer.parseInt(rows);
        } catch (NumberFormatException e) {
            this.rows= iniRows;
            this.columns= iniCols;
        }
    }

    public void setMyTop(String s, int def) {
        try{
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }this.top=Integer.parseInt(s);} catch (NumberFormatException e) {
            this.top=def;
        }
    }

    public void setMyNeighbours(String s, int def) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }this.neighbours=Integer.parseInt(s);} catch (NumberFormatException e) {
            this.neighbours=def;
        }
    }

    public void setMyBottom(String s, int def) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }this.bottom=Integer.parseInt(s);} catch (NumberFormatException e) {
            this.bottom=def;
        }
    }

    public void setMyRight(String s) {
        try{
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }
            this.right= Integer.parseInt(s);} catch (NumberFormatException e) {
            this.right=noRow;
        }
    }

    public void setMyLeft(String s) {
        try{
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }
            this.left= Integer.parseInt(s);} catch (NumberFormatException e) {
            this.left=noRow;
        }
    }

    public void setMyBoundary(String s, String def){
        try{
            if(hasNumbers(s)){
                throw new NumberFormatException();
            }
            this.Myboundary=s;} catch (NumberFormatException e) {
            this.Myboundary=def;
        }
    }

    public abstract void paraTitle(String title);
    public static boolean isStringOnlyAlphabet(String str)
    {
        return ((str != null)
                && (!str.equals(""))
                && str.matches(".*[a-zA-Z]+.*"));
    }

    public static boolean hasNumbers(String str){
        return str.matches(".*\\d.*");
    }
}

