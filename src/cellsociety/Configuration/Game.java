package cellsociety.Configuration;

import java.util.List;
import java.util.Map;

public class Game extends Configuration{
    public static final String DATA_TYPE = "Game of Life";
    // field names expected to appear in data file holding values for this object
    // NOTE: simple way to create an immutable list
    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","type1","type2","type3","neighPattern","shape","concentration","initial","colors");

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
        setMyStates(dataValues.get(DATA_FIELDS.get(1)),3);
        setMyDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
        setMyLeft(dataValues.get(DATA_FIELDS.get(4)));
        setMyRight(dataValues.get(DATA_FIELDS.get(5)));
        setMyTop(dataValues.get(DATA_FIELDS.get(6)),noRow);
        setMyBottom(dataValues.get(DATA_FIELDS.get(7)),noRow);
        setMyNeighbours(dataValues.get(DATA_FIELDS.get(8)),8);
        setMyType1(dataValues.get(DATA_FIELDS.get(9)),"Empty");
        setMyType2(dataValues.get(DATA_FIELDS.get(10)),"Live");
        setMyType3(dataValues.get(DATA_FIELDS.get(11)),"Dead");
        setMyNeighPattern(dataValues.get(DATA_FIELDS.get(12)),neighbourPattern);
        setMyShape(dataValues.get(DATA_FIELDS.get(13)),initialShape);
        setMyConcentration(dataValues.get(DATA_FIELDS.get(14)),iniConcLife);
        setMyStartingConfig(dataValues.get(DATA_FIELDS.get(15)),configRandom);
        setMyColors(dataValues.get(DATA_FIELDS.get(16)),"Red,Green,White");
    }



}


