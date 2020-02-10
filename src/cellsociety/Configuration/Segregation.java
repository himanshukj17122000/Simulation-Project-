package cellsociety.Configuration;

import java.util.List;
import java.util.Map;
/**
 * @Author-Himanshu Jain
 * This class is for the Segregation Configuration. It sets all the values for the Segregation Simulation using the setter methods
 * in the Configuration file
 */
public class Segregation extends Configuration {
    public static final String DATA_TYPE = "Segregation";
    // field names expected to appear in data file holding values for this object
    // NOTE: simple way to create an immutable list
    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","probCatch","type1","type2","type3","probCatchLabel","maxProbCatch","neighPattern","shape","concentration","initial","colors","boundary");

    /**
     * Create Segregation data from given data.
     */
    @Override
    public void paraTitle (String title) {
        setTitle(title);
    }

    /**
     * Create Segregation data from data structure of Strings.
     *
     * @param dataValues map of field names to their values
     */

    public Segregation (Map<String, String> dataValues) throws NumberFormatException{
        setTitle(dataValues.get(DATA_FIELDS.get(0)));
        setStates(dataValues.get(DATA_FIELDS.get(1)),3);
        setDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
        setLeft(dataValues.get(DATA_FIELDS.get(4)));
        setRight(dataValues.get(DATA_FIELDS.get(5)));
        setTop(dataValues.get(DATA_FIELDS.get(6)),noRow);
        setBottom(dataValues.get(DATA_FIELDS.get(7)),noRow);
        setNeighbours(dataValues.get(DATA_FIELDS.get(8)),8);
        setProbCatch(dataValues.get(DATA_FIELDS.get(9)),defaultProb);
        setType1(dataValues.get(DATA_FIELDS.get(10)),"Empty");
        setType2(dataValues.get(DATA_FIELDS.get(11)),"Race1");
        setType3(dataValues.get(DATA_FIELDS.get(12)),"Race2");
        setProbCatchLabel(dataValues.get(DATA_FIELDS.get(13)),"Threshold Value");
        setProbMax(dataValues.get(DATA_FIELDS.get(14)),"1");
        setNeighPattern(dataValues.get(DATA_FIELDS.get(15)),neighbourPattern);
        setShape(dataValues.get(DATA_FIELDS.get(16)),initialShape);
        setConcentration(dataValues.get(DATA_FIELDS.get(17)),iniConc2);
        setStartingConfig(dataValues.get(DATA_FIELDS.get(18)),configRandom);
        setColors(dataValues.get(DATA_FIELDS.get(19)),"Red,Green,White");
        setBoundary(dataValues.get(DATA_FIELDS.get(20)),"NotToroidal");

    }
}


