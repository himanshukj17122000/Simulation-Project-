package cellsociety.Configuration;

import java.util.List;
import java.util.Map;
/**
 * @Author-Himanshu Jain
 * This class is for the Game of Life Configuration. It sets all the values for the Game of Life Simulation using the setter methods
 * in the Configuration file
 */
public class Game extends Configuration{
    public static final String DATA_TYPE = "Game of Life";
    // field names expected to appear in data file holding values for this object
    // NOTE: simple way to create an immutable list
    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","type1","type2","type3","neighPattern","shape","concentration","initial","colors","boundary");

    /**
     * Create Game of Life data from given data.
     */
    @Override
    public void paraTitle (String title) {
        setTitle(title);
    }

    /**
     * Create Game of Life data from data structure of Strings.
     *
     * @param dataValues map of field names to their values
     */

    public Game (Map<String, String> dataValues) throws NumberFormatException {
        setTitle(dataValues.get(DATA_FIELDS.get(0)));
        setStates(dataValues.get(DATA_FIELDS.get(1)),3);
        setDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
        setLeft(dataValues.get(DATA_FIELDS.get(4)));
        setRight(dataValues.get(DATA_FIELDS.get(5)));
        setTop(dataValues.get(DATA_FIELDS.get(6)),noRow);
        setBottom(dataValues.get(DATA_FIELDS.get(7)),noRow);
        setNeighbours(dataValues.get(DATA_FIELDS.get(8)),8);
        setType1(dataValues.get(DATA_FIELDS.get(9)),"Empty");
        setType2(dataValues.get(DATA_FIELDS.get(10)),"Live");
        setType3(dataValues.get(DATA_FIELDS.get(11)),"Dead");
        setNeighPattern(dataValues.get(DATA_FIELDS.get(12)),neighbourPattern);
        setShape(dataValues.get(DATA_FIELDS.get(13)),initialShape);
        setConcentration(dataValues.get(DATA_FIELDS.get(14)),iniConcLife);
        setStartingConfig(dataValues.get(DATA_FIELDS.get(15)),configRandom);
        setColors(dataValues.get(DATA_FIELDS.get(16)),"Red,Green,White");
        setBoundary(dataValues.get(DATA_FIELDS.get(17)),"NotToroidal");

    }



}


