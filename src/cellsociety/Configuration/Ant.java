package cellsociety.Configuration;

import java.util.List;
import java.util.Map;

public class Ant extends Configuration {
    public static final String DATA_TYPE = "Ant";
    // field names expected to appear in data file holding values for this object
    // NOTE: simple way to create an immutable list

    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","type1","type2","type3","neighPattern","shape","concentration","initial","boundary");

    /**
     * Create game data from given data.
     */
    @Override
    public void paraTitle (String title) {
        setTitle(title);
    }

    /**
     * Create game data from data structure of Strings.
     *
     * @param dataValues map of field names to their values
     */

    public Ant (Map<String, String> dataValues) throws NumberFormatException{
        setTitle(dataValues.get(DATA_FIELDS.get(0)));
        setStates(dataValues.get(DATA_FIELDS.get(1)),3);
        setDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
        setLeft(dataValues.get(DATA_FIELDS.get(4)));
        setRight(dataValues.get(DATA_FIELDS.get(5)));
        setTop(dataValues.get(DATA_FIELDS.get(6)),noRow);
        setBottom(dataValues.get(DATA_FIELDS.get(7)),noRow);
        setNeighbours(dataValues.get(DATA_FIELDS.get(8)),4);
        setType1(dataValues.get(DATA_FIELDS.get(9)),"Empty");
        setType2(dataValues.get(DATA_FIELDS.get(10)),"Food");
        setType2(dataValues.get(DATA_FIELDS.get(11)),"Nest");
        setNeighPattern(dataValues.get(DATA_FIELDS.get(12)),neighbourPattern);
        setShape(dataValues.get(DATA_FIELDS.get(13)),initialShape);
        setConcentration(dataValues.get(DATA_FIELDS.get(14)),iniConc1);
        setStartingConfig(dataValues.get(DATA_FIELDS.get(15)),"Given");
        setBoundary(dataValues.get(DATA_FIELDS.get(16)),"toroidal");
    }
}
