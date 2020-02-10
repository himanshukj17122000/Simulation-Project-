package cellsociety.Configuration;

import javax.print.attribute.standard.DateTimeAtCompleted;
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
        setMyTitle(title);
    }

    /**
     * Create Segregation data from data structure of Strings.
     *
     * @param dataValues map of field names to their values
     */

    public Segregation (Map<String, String> dataValues) throws NumberFormatException{
        setMyTitle(dataValues.get(DATA_FIELDS.get(0)));
        setMyStates(dataValues.get(DATA_FIELDS.get(1)),3);
        setMyDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
        setMyLeft(dataValues.get(DATA_FIELDS.get(4)));
        setMyRight(dataValues.get(DATA_FIELDS.get(5)));
        setMyTop(dataValues.get(DATA_FIELDS.get(6)),noRow);
        setMyBottom(dataValues.get(DATA_FIELDS.get(7)),noRow);
        setMyNeighbours(dataValues.get(DATA_FIELDS.get(8)),8);
        setMyProbCatch(dataValues.get(DATA_FIELDS.get(9)),defaultProb);
        setMyType1(dataValues.get(DATA_FIELDS.get(10)),"Empty");
        setMyType2(dataValues.get(DATA_FIELDS.get(11)),"Race1");
        setMyType3(dataValues.get(DATA_FIELDS.get(12)),"Race2");
        setMyProbCatchLabel(dataValues.get(DATA_FIELDS.get(13)),"Threshold Value");
        setMyProbMax(dataValues.get(DATA_FIELDS.get(14)),"1");
        setMyNeighPattern(dataValues.get(DATA_FIELDS.get(15)),neighbourPattern);
        setMyShape(dataValues.get(DATA_FIELDS.get(16)),initialShape);
        setMyConcentration(dataValues.get(DATA_FIELDS.get(17)),iniConc2);
        setMyStartingConfig(dataValues.get(DATA_FIELDS.get(18)),configRandom);
        setMyColors(dataValues.get(DATA_FIELDS.get(19)),"Red,Green,White");
        setMyBoundary(dataValues.get(DATA_FIELDS.get(20)),"NotToroidal");

    }
}


