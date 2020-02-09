package cellsociety.Configuration;

import java.util.List;
import java.util.Map;

public class Percolation extends CommonMethods {
    public static final String DATA_TYPE = "Percolation";
    // field names expected to appear in data file holding values for this object
    // NOTE: simple way to create an immutable list
    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","type1","type2","type3","neighPattern","shape","colors");

    /**
     * Create game data from given data.
     */
    @Override
    public void paraTitle (String title) {
        setMyTitle(title);
    }

    /**
     * Create game data from data structure of Strings.
     *
     * @param dataValues map of field names to their values
     */
    public Percolation (Map<String, String> dataValues) throws NumberFormatException {
        setMyTitle(dataValues.get(DATA_FIELDS.get(0)));
        setMyStates(dataValues.get(DATA_FIELDS.get(1)));
        setMyDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
        setMyLeft(dataValues.get(DATA_FIELDS.get(4)));
        setMyRight(dataValues.get(DATA_FIELDS.get(5)));
        setMyTop(dataValues.get(DATA_FIELDS.get(6)));
        setMyBottom(dataValues.get(DATA_FIELDS.get(7)));
        setMyNeighbours(dataValues.get(DATA_FIELDS.get(8)));

        try{setType1(dataValues.get(DATA_FIELDS.get(9)));} catch (Exception e) {
            setType1("Percolated");
        }
        try{setType2(dataValues.get(DATA_FIELDS.get(10)));} catch (Exception e) {
            setType2("Open");
        }
        try{setType3(dataValues.get(DATA_FIELDS.get(11)));} catch (Exception e) {
            setType3("Block");
        }
        try{setNeighPattern(dataValues.get(DATA_FIELDS.get(12)));} catch (Exception e) {
            setNeighPattern("11111111");
        }
        try{setShape(dataValues.get(DATA_FIELDS.get(13)));} catch (Exception e) {
            setShape(initialShape);
        }
        setStartingConfig(configRandom);
        try{setColors(dataValues.get(DATA_FIELDS.get(14)));}catch (Exception e){
            setColors("Blue,Black");
        }
    }

    @Override
    public void setMyStates(String s) {
        try {
            if(isStringOnlyAlphabet(s)){
                throw new NumberFormatException();
            }
            setMaxStates(Integer.parseInt(s));

        } catch (NumberFormatException e) {
            setMaxStates(2);
        }
    }
}
