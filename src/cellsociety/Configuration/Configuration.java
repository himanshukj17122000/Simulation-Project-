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
     * get the random number for RPS
     * @return the random number
     */
    public int getRandomNumber() {
        return randomNumber;
    }

    /**
     * sets the random number for RPS
     * @param randomNumber the random number
     */
    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    /**
     * maximum value of the random number
     * @return the int for the maximum random number possible
     */
    public int getMaxRandomNumber() {
        return maxRandomNumber;
    }

    /**
     * sets the maximum random number
     * @param maxRandomNumber the maximum number from the XML file
     */
    public void setMaxRandomNumber(int maxRandomNumber) {
        this.maxRandomNumber = maxRandomNumber;
    }

    /**
     * returns the shape that could be hexagon, rectange
     * @return the string that represents the shape
     */
    public String getShape() {
        return shape;
    }

    /**
     * the title of the XML file
     * @return the String that is the name of the file
     */
    public String getTitle(){return myTitle;}

    /**
     * returns the boundary which could be finite or toroidal
     * @return teh string representing the boundary
     */
    public String getMyboundary(){return Myboundary;}

    /**
     * the max types of cells in a file
     * @return the int representing the number of states
     */
    public int getMaxStates(){return maxStates;}

    /**
     * the number of rows
     * @return the int that is the number of rows
     */
    public int getRows(){return rows;}

    /**
     * the number of columns
     * @return the int that is the number of columns
     */
    public int getColumns(){return columns;}

    /**
     * the left boundary
     * @return the int representing if there are cells on the left boundary
     */
    public int getLeft(){return left;}

    /**
     * the right boundary
     * @return the int representing if there are cells on the right boundary
     */
    public int getRight(){return right;}

    /**
     * the top boundary
     * @return the int representing if there are cells on the top boundary
     */
    public int getTop(){return top;}

    /**
     * the bottom boundary
     * @return the int representing if there are cells on the bottom boundary
     */
    public int getBottom(){return bottom;}

    /**
     * the number of neighbours for each cell
     * @return the int that is the number of neighbours
     */
    public int getNeighbours(){return neighbours;}

    /**
     * the type of cell 1
     * @return the int that is the cell 1
     */
    public String getType1(){return type1;}

    /**
     * the type of cell 2
     * @return the int that is the cell 2
     */
    public String getType2(){return type2;}

    /**
     * the type of cell 3
     * @return the int that is the cell 3
     */
    public String getType3(){return type3;}

    /**
     * the labels for all the sliders
     * @return the strings that represent the labels
     */
    public ArrayList<String> getProbCatchLabel(){return probCatchLabel;}

    /**
     * the constants in the file
     * @return the double that represent the values of each variable
     */
    public ArrayList<Double> getProbCatch(){return probCatch;}

    /**
     * the maximum value of all the variable numbers
     * @return the double values that represent the maximum for each slider
     */
    public ArrayList<Double> getMaxProb(){return maxProb;}

    /**
     * the neighbour pattern
     * @return the integer array represents the neighbour pattern for each cell
     */
    public int[] getNeighPattern(){return neighPattern;}

    /**
     * the concentration of each cell type
     * @return the percentage of cells representing a particular type
     */
    public Double[] getConcentration(){return concentration;}

    /**
     * sets the shape of the cells
     * @param readVal the value read from the file
     * @param initialShape the default value
     */
    public void setShape(String readVal, String initialShape) {
        try{if(hasNumbers(readVal)){
            throw new Exception();
        }this.shape=readVal;} catch (Exception e) {
            this.shape=initialShape;
        }
    }

    /**
     * sets the maximum value of prob catch
     * @param readVal the value read from the file
     * @param s1 the default value
     */
    public void setProbMax(String readVal, String s1) {
        try{if(isStringOnlyAlphabet(readVal)){
            throw new NumberFormatException();
        }maxProb.add(Double.parseDouble(readVal));} catch (NumberFormatException e) {
            maxProb.add(Double.parseDouble(s1));
        }
    }

    /**
     * the concentration of each cell as a string
     * @return the string representing the concentration of each cell type
     */
    public String getConcentrate() {
        return concentrate;
    }

    /**
     * sets the value of the string
     * @param concentrate the value that has to be set
     */
    public void setConcentrate(String concentrate) {
        this.concentrate = concentrate;
    }

    /**
     * sets the initial concentration for each cell type
     * @param readVal the value read in from the XML file
     * @param iniConc1 the default value
     */
    public void setConcentration(String readVal, String iniConc1) {
        try{ if(isStringOnlyAlphabet(readVal)){
            throw new Exception();
        }String[] concen= readVal.split(",");
            concentrate=readVal;
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


    /**
     * the neighbour pattern for the cell
     * @param readVal the value read from the XML
     * @param neighbourPattern the default value
     */
    public void setNeighPattern(String readVal, String neighbourPattern) {
        try{ if(hasNumbers(readVal)){
            throw new Exception();
        }char[] pattArray= readVal.toCharArray();
            neighbourPatterninString=readVal;
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

    /**
     * get the neighbour pattern in string
     * @return the string representing the neighbour pattern
     */
    public String getNeighPatterninString(){
        return neighbourPatterninString;
    }

    /**
     * sets the colour of the cells
     * @param readVal the value read from the file
     * @param def the default value
     */
    public void setColors(String readVal, String def) {
        try{if(hasNumbers(readVal)){
            throw new Exception();
        }String[] separateColors= readVal.split(",");
            for (String colour:separateColors){
                colors.add(colour);
            }}catch (Exception e){
            String[] separateColors= def.split(",");
            for (String colour:separateColors){
                colors.add(colour);
            }
        }
    }

    /**
     * sets the starting configuration as random or given
     * @param readVal the value read from the file
     * @param configRandom the default value
     */
    public void setStartingConfig(String readVal, String configRandom) {
        try{if(hasNumbers(readVal)){
            throw new Exception();
        }   this.startingConfig = readVal;}catch (Exception e){
            this.startingConfig = configRandom;
        }
    }

    /**
     * sets the title
     * @param myTitle the value read from the file
     */
    public void setTitle(String myTitle) { this.myTitle = myTitle; }


    /**
     * the string for the slider
     * @param readVal value read from the file
     * @param def default value
     */
    public void setProbCatchLabel(String readVal, String def) {
        try{if(hasNumbers(readVal)){
            throw new Exception();
        }probCatchLabel.add(readVal);} catch (Exception e) {
            probCatchLabel.add(def);;
        }
    }

    /**
     * the third type of cell
     * @param s value read in from the file
     * @param tree the default value
     */
    public void setType3(String s, String tree) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }this.type3=s;} catch (Exception e) {
            this.type3=tree;
        }
    }

    /**
     * the second type of cell
     * @param s the value read in
     * @param tree the default value
     */

    public void setType2(String s, String tree) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }this.type2=s;} catch (Exception e) {
            this.type2=tree;
        }
    }

    /**
     * the first type of cell
     * @param s the value read in
     * @param tree the default value
     */
    public void setType1(String s, String tree) {
        try{if(hasNumbers(s)){
            throw new Exception();
        }
            this.type1=s;} catch (Exception e) {
            this.type1=tree;
        }
    }

    /**
     * sets the maximum number of states
     * @param s the value in the xml file
     * @param def the default value
     */
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

    /**
     * sets the prob catch value
     * @param s the value in the file
     * @param defaultProb the default value
     */
    public void setProbCatch(String s, double defaultProb) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }probCatch.add(Double.parseDouble(s));} catch (NumberFormatException e) {
            probCatch.add(defaultProb);
        }
    }

    /**
     * sets the dimensions of the grid
     * @param columns the number of columns
     * @param rows the number of rows
     */
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

    /**
     * the top boundary condition
     * @param s the value passed in
     * @param def the default value
     */
    public void setTop(String s, int def) {
        try{
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }this.top=Integer.parseInt(s);} catch (NumberFormatException e) {
            this.top=def;
        }
    }

    /**
     * sets the number of neighbours for each cell
     * @param s the value read in from the XML
     * @param def the default value
     */
    public void setNeighbours(String s, int def) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }this.neighbours=Integer.parseInt(s);} catch (NumberFormatException e) {
            this.neighbours=def;
        }
    }

    /**
     * the bottom boundary condition
     * @param s the value read in
     * @param def the default value
     */
    public void setBottom(String s, int def) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }this.bottom=Integer.parseInt(s);} catch (NumberFormatException e) {
            this.bottom=def;
        }
    }

    /**
     * sets the right boundary condition
     * @param s the string that is read from the file
     */
    public void setRight(String s) {
        try{
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }
            this.right= Integer.parseInt(s);} catch (NumberFormatException e) {
            this.right=noRow;
        }
    }

    /**
     * sets the left boundary condition
     * @param s the string that is read from the file
     */
    public void setLeft(String s) {
        try{
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }
            this.left= Integer.parseInt(s);} catch (NumberFormatException e) {
            this.left=noRow;
        }
    }

    /**
     * represents the boundary of each cell
     * @param readVal sets the boundary as finite or infinitie
     * @param def the default value
     */
    public void setBoundary(String readVal, String def){
        try{
            if(hasNumbers(readVal)){
                throw new NumberFormatException();
            }
            this.Myboundary=readVal;} catch (NumberFormatException e) {
            this.Myboundary=def;
        }
    }

    /**
     * the method that is overloaded
     * @param title the string passed in
     */
    public abstract void paraTitle(String title);

    /**
     * to check if the string has an alphabet
     * @param str the string read from the file
     * @return the boolean representing whether an alphabet is there or not
     */
    public boolean isStringOnlyAlphabet(String str)
    {
        return ((str != null)
                && (!str.equals(""))
                && str.matches(".*[a-zA-Z]+.*"));
    }

    /**
     * checks if the string has a number
     * @param str the string given
     * @return the boolean to check if the string has a number
     */
    public boolean hasNumbers(String str){
        return str.matches(".*\\d.*");
    }
}

