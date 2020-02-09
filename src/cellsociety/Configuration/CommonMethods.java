package cellsociety.Configuration;

public class CommonMethods extends Configuration{

    @Override
    public void paraTitle(String title) {

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
            setRows(Integer.parseInt(columns));
            setColumns(Integer.parseInt(rows));
        } catch (NumberFormatException e) {
            setColumns(iniCols);
            setRows(iniRows);
        }
    }
    public static boolean isStringOnlyAlphabet(String str)
    {
        return ((str != null)
                && (!str.equals(""))
                && str.matches(".*[a-zA-Z]+.*"));
    }

    public void setMyTop(String s, int def) {
        try{
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }setTop(Integer.parseInt(s));} catch (NumberFormatException e) {
            setTop(def);
        }
    }

    public void setMyNeighbours(String s, int def) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }setNeighbours(Integer.parseInt(s));} catch (NumberFormatException e) {
            setNeighbours(def);
        }
    }

    public void setMyBottom(String s, int def) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }setBottom(Integer.parseInt(s));} catch (NumberFormatException e) {
            setBottom(def);
        }
    }

    public void setMyRight(String s) {
        try{
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }
            setRight(Integer.parseInt(s));} catch (NumberFormatException e) {
            setRight(noRow);
        }
    }

    public void setMyLeft(String s) {
        try{
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }
            setLeft(Integer.parseInt(s));} catch (NumberFormatException e) {
            setLeft(noRow);
        }
    }
    public void setMyStates(String s, int def) {
        try {
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }
            setMaxStates(Integer.parseInt(s));

        } catch (NumberFormatException e) {
            setMaxStates(def);
        }
    }
    public void setMyProbCatch(String s, double defaultProb) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }setProbCatch(Double.parseDouble(s));} catch (NumberFormatException e) {
            setProbCatch(defaultProb);
        }
    }
    public void setMyProbCatchLabel(String s, String probability_of_catching_on_fire) {
        try{setProbCatchLabel(s);} catch (Exception e) {
            setProbCatchLabel(probability_of_catching_on_fire);
        }
    }

    public void setMyType3(String s, String tree) {
        try{setType3(s);} catch (Exception e) {
            setType3(tree);
        }
    }

    public void setMyType2(String s, String tree) {
        try{setType2(s);} catch (Exception e) {
            setType2(tree);
        }
    }
    public void setMyType1(String s, String tree) {
        try{setType1(s);} catch (Exception e) {
            setType1(tree);
        }
    }
}
