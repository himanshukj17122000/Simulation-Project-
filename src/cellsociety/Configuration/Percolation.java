package cellsociety.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @Author-Himanshu Jain
 * This class is for the Percolation Configuration. It sets all the values for the Percolation Simulation using the setter methods
 * in the Configuration file
 */
public class Percolation extends Configuration {
    public static final String DATA_TYPE = "Percolation";
    // field names expected to appear in data file holding values for this object
    // NOTE: simple way to create an immutable list
    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","type1","type2","type3","neighPattern","shape","colors","boundary");

    /**
     * Create Percolation data from given data.
     */
    @Override
    public void paraTitle (String title) {
        setTitle(title);
    }

    /**
     * Create Percolation data from data structure of Strings.
     *
     * @param dataValues map of field names to their values
     */
    public Percolation (Map<String, String> dataValues) throws NumberFormatException {
        setTitle(dataValues.get(DATA_FIELDS.get(0)));
        setStates(dataValues.get(DATA_FIELDS.get(1)),2);
        setDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
        setLeft(dataValues.get(DATA_FIELDS.get(4)));
        setRight(dataValues.get(DATA_FIELDS.get(5)));
        setTop(dataValues.get(DATA_FIELDS.get(6)),3);
        setBottom(dataValues.get(DATA_FIELDS.get(7)),2);
        setNeighbours(dataValues.get(DATA_FIELDS.get(8)),8);
        setType1(dataValues.get(DATA_FIELDS.get(9)),"Percolated");
        setType2(dataValues.get(DATA_FIELDS.get(10)),"Open");
        setType3(dataValues.get(DATA_FIELDS.get(11)),"Block");
        setNeighPattern(dataValues.get(DATA_FIELDS.get(12)),"11111111");
        setShape(dataValues.get(DATA_FIELDS.get(13)),initialShape);
        setColors(dataValues.get(DATA_FIELDS.get(14)),"Blue,Black");
        setStartingConfig(configRandom,configRandom);
        setBoundary(dataValues.get(DATA_FIELDS.get(15)),"NotToroidal");

    }
}
