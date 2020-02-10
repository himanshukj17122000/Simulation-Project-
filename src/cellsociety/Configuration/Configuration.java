package cellsociety.Configuration;

import java.util.ArrayList;
/**
 * @Author-Himanshu Jain
 * This is the abstract class for Configuration. It mostly holds the values that are required for making the simulation
 * and also sets the values for them. It is inherited by all the other simulations that call it to set their value
 */
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

    /**
     * used to get the starting connfiguration whether it is random or given
     * @return the starting configuration of the simulation
     */
    public String getStartingConfig() {
        return startingConfig;
    }


    private ArrayList<String> probCatchLabel= new ArrayList<>();
    private ArrayList<Double> maxProb=new ArrayList<>();
    private ArrayList<Double> probCatch=new ArrayList<>();
    private int[] neighPattern= new int[8];

    /**
     * used to get the colors for the simulation
     * @return the different colours that are read from the XML file
     */
    public ArrayList<String> getColors() {
        return colors;
    }



    private ArrayList<String> colors= new ArrayList<>();
    private Double[] concentration= new Double[3];
    private int maxStates,rows,columns,left,right,top,bottom, neighbours,randomNumber,maxRandomNumber;

    /**
     * the label for the slider
     * @return the text that is above the slider
     */
    public String getRandomLabel() {
        return randomLabel;
    }

    /**
     * set the label for the slider
     * @param randomLabel the text that has to be above the slider
     */
    public void setRandomLabel(String randomLabel) {
        this.randomLabel = randomLabel;
    }

    /**
     *
     * @return
     */
    public int getRandomNumber() {
        return randomNumber;
    }

    /**
     *
     * @param randomNumber
     */
    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    /**
     *
     * @return
     */
    public int getMaxRandomNumber() {
        return maxRandomNumber;
    }

    /**
     *
     * @param maxRandomNumber
     */
    public void setMaxRandomNumber(int maxRandomNumber) {
        this.maxRandomNumber = maxRandomNumber;
    }

    /**
     *
     * @return
     */
    public String getShape() {
        return shape;
    }

    /**
     *
     * @return
     */
    public String getTitle(){return myTitle;}

    /**
     *
     * @return
     */
    public String getMyboundary(){return Myboundary;}

    /**
     *
     * @return
     */
    public int getMaxStates(){return maxStates;}

    /**
     *
     * @return
     */
    public int getRows(){return rows;}

    /**
     *
     * @return
     */
    public int getColumns(){return columns;}

    /**
     *
     * @return
     */
    public int getLeft(){return left;}

    /**
     *
     * @return
     */
    public int getRight(){return right;}

    /**
     *
     * @return
     */
    public int getTop(){return top;}

    /**
     *
     * @return
     */
    public int getBottom(){return bottom;}

    /**
     *
     * @return
     */
    public int getNeighbours(){return neighbours;}

    /**
     *
     * @return
     */
    public String getType1(){return type1;}

    /**
     *
     * @return
     */
    public String getType2(){return type2;}

    /**
     *
     * @return
     */
    public String getType3(){return type3;}

    /**
     *
     * @return
     */
    public ArrayList<String> getProbCatchLabel(){return probCatchLabel;}

    /**
     *
     * @return
     */
    public ArrayList<Double> getProbCatch(){return probCatch;}

    /**
     *
     * @return
     */
    public ArrayList<Double> getMaxProb(){return maxProb;}

    /**
     *
     * @return
     */
    public int[] getNeighPattern(){return neighPattern;}

    /**
     *
     * @return
     */
    public Double[] getConcentration(){return concentration;}

    /**
     *
     * @param s
     * @param initialShape
     */
    public void setShape(String s, String initialShape) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }this.shape=s;} catch (Exception e) {
            this.shape=initialShape;
        }
    }

    public void setProbMax(String s, String s1) {
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

    public void setConcentration(String s, String iniConc1) {
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



    public void setNeighPattern(String s, String neighbourPattern) {
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


    public void setColors(String s, String s1) {
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

    public void setStartingConfig(String s, String configRandom) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }   this.startingConfig = s;}catch (Exception e){
            this.startingConfig = configRandom;
        }
    }


    public void setTitle(String myTitle) { this.myTitle = myTitle; }



    public void setProbCatchLabel(String s, String probability_of_catching_on_fire) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }probCatchLabel.add(s);} catch (Exception e) {
            probCatchLabel.add(probability_of_catching_on_fire);;
        }
    }

    public void setType3(String s, String tree) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }this.type3=s;} catch (Exception e) {
            this.type3=tree;
        }
    }

    public void setType2(String s, String tree) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }this.type2=s;} catch (Exception e) {
            this.type2=tree;
        }
    }
    public void setType1(String s, String tree) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }
            this.type1=s;} catch (Exception e) {
            this.type1=tree;
        }
    }

    public void setStates(String s, int def) {
        try {
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }
            this.maxStates=Integer.parseInt(s);

        } catch (NumberFormatException e) {
            this.maxStates=def;
        }
    }

    public void setProbCatch(String s, double defaultProb) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }probCatch.add(Double.parseDouble(s));} catch (NumberFormatException e) {
            probCatch.add(defaultProb);
        }
    }

    public void setDimensions(String columns, String rows) {
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

    public void setTop(String s, int def) {
        try{
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }this.top=Integer.parseInt(s);} catch (NumberFormatException e) {
            this.top=def;
        }
    }

    public void setNeighbours(String s, int def) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }this.neighbours=Integer.parseInt(s);} catch (NumberFormatException e) {
            this.neighbours=def;
        }
    }

    public void setBottom(String s, int def) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }this.bottom=Integer.parseInt(s);} catch (NumberFormatException e) {
            this.bottom=def;
        }
    }

    public void setRight(String s) {
        try{
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }
            this.right= Integer.parseInt(s);} catch (NumberFormatException e) {
            this.right=noRow;
        }
    }

    public void setLeft(String s) {
        try{
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }
            this.left= Integer.parseInt(s);} catch (NumberFormatException e) {
            this.left=noRow;
        }
    }

    public void setBoundary(String s, String def){
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

