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

    public void setMyTop(String s) {
        try{
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }setTop(Integer.parseInt(s));} catch (NumberFormatException e) {
            setTop(noRow);
        }
    }

    public void setMyNeighbours(String s) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }setNeighbours(Integer.parseInt(s));} catch (NumberFormatException e) {
            setNeighbours(8);
        }
    }

    public void setMyBottom(String s) {
        try{if(isStringOnlyAlphabet(s)){
            throw new NumberFormatException();
        }setBottom(Integer.parseInt(s));} catch (NumberFormatException e) {
            setBottom(noRow);
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
    public void setMyStates(String s) {
        try {
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }
            setMaxStates(Integer.parseInt(s));

        } catch (NumberFormatException e) {
            setMaxStates(3);
        }
    }
}
