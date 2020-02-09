package cellsociety.Configuration;

import java.util.List;
import java.util.Map;

public class Game extends Configuration {
    public static final String DATA_TYPE = "Game of Life";
    // field names expected to appear in data file holding values for this object
    // NOTE: simple way to create an immutable list
    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","type1","type2","type3","neighPattern","shape","concentration","initial");

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

    public Game (Map<String, String> dataValues) throws NumberFormatException {
            setMyTitle(dataValues.get(DATA_FIELDS.get(0)));

        try{setMaxStates(Integer.parseInt(dataValues.get(DATA_FIELDS.get(1))));} catch (NumberFormatException e) {
            setMaxStates(3);
        }
        try{ setRows(Integer.parseInt(dataValues.get(DATA_FIELDS.get(2))));} catch (NumberFormatException e) {
            setRows(iniRows);
        }
        try{setColumns(Integer.parseInt(dataValues.get(DATA_FIELDS.get(3))));} catch (NumberFormatException e) {
            setColumns(iniCols);
        }
        try{setLeft(Integer.parseInt(dataValues.get(DATA_FIELDS.get(4))));} catch (NumberFormatException e) {
            setLeft(noRow);
        }
        try{setRight(Integer.parseInt(dataValues.get(DATA_FIELDS.get(5))));} catch (NumberFormatException e) {
            setRight(noRow);
        }
        try{setTop(Integer.parseInt(dataValues.get(DATA_FIELDS.get(6))));} catch (NumberFormatException e) {
            setTop(noRow);
        }
        try{setBottom(Integer.parseInt(dataValues.get(DATA_FIELDS.get(7))));} catch (NumberFormatException e) {
            setBottom(noRow);
        }
        try{setNeighbours(Integer.parseInt(dataValues.get(DATA_FIELDS.get(8))));} catch (NumberFormatException e) {
            setNeighbours(8);
        }
        try{setType1(dataValues.get(DATA_FIELDS.get(9)));} catch (Exception e) {
            setType1("Empty");
        }
        try{setType2(dataValues.get(DATA_FIELDS.get(10)));} catch (Exception e) {
            setType2("Live");
        }
        try{setType3(dataValues.get(DATA_FIELDS.get(11)));} catch (Exception e) {
            setType3("Dead");
        }
        try{setNeighPattern(dataValues.get(DATA_FIELDS.get(12)));} catch (Exception e) {
            setNeighPattern(neighbourPattern);
        }
        try{setShape(dataValues.get(DATA_FIELDS.get(13)));} catch (Exception e) {
            setShape(initialShape);
        }
        try{setConcentration(dataValues.get(DATA_FIELDS.get(14)));} catch (Exception e) {
            setConcentration(iniConcLife);
        } try{setConcentration(dataValues.get(DATA_FIELDS.get(15)));} catch(Exception e){
            setStartingConfig(configRandom);
        }
        try{setColors(dataValues.get(DATA_FIELDS.get(16)));}catch (Exception e){
            setColors("Red,Green,White");
        }
    }
}


