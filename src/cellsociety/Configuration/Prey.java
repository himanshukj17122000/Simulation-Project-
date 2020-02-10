package cellsociety.Configuration;

import java.util.List;
import java.util.Map;
/**
 * @Author-Himanshu Jain
 * This class is for the Prey Configuration. It sets all the values for the Prey Simulation using the setter methods
 * in the Configuration file
 */
public class Prey extends Configuration {
    public static final String DATA_TYPE = "Prey";

    // field names expected to appear in data file holding values for this object
    // NOTE: simple way to create an immutable list
    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","type1","type2","type3","fishBreed","sharkBreed","fishLabel","sharkLabel","numLabel","maxFishBreed"
    ,"maxSharkBreed","numWithoutFood","maxDays","neighPattern","shape","concentration","initial","colors","boundary");


    /**
     * Create Prey data from given data.
     */
    @Override
    public void paraTitle (String title) {
        setTitle(title);
    }

    /**
     * Create Prey data from data structure of Strings.
     *
     * @param dataValues map of field names to their values
     */

    public Prey(Map<String, String> dataValues) throws NumberFormatException {
        setTitle(dataValues.get(DATA_FIELDS.get(0)));
        setStates(dataValues.get(DATA_FIELDS.get(1)),3);
        setDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
        setLeft(dataValues.get(DATA_FIELDS.get(4)));
        setRight(dataValues.get(DATA_FIELDS.get(5)));
        setTop(dataValues.get(DATA_FIELDS.get(6)),noRow);
        setBottom(dataValues.get(DATA_FIELDS.get(7)),noRow);
        setNeighbours(dataValues.get(DATA_FIELDS.get(8)),4);
        setType1(dataValues.get(DATA_FIELDS.get(9)),"Empty");
        setType2(dataValues.get(DATA_FIELDS.get(10)),"Fish");
        setType3(dataValues.get(DATA_FIELDS.get(11)),"Shark");
        setNeighPattern(dataValues.get(DATA_FIELDS.get(21)),"11111111");
        setShape(dataValues.get(DATA_FIELDS.get(22)),initialShape);
        setProbCatch(dataValues.get(DATA_FIELDS.get(12)),8);
        setProbCatch(dataValues.get(DATA_FIELDS.get(13)),8);
        setProbCatchLabel(dataValues.get(DATA_FIELDS.get(14)),"Fish Breeding Time");
        setProbCatchLabel(dataValues.get(DATA_FIELDS.get(15)),"Shark Breeding Time");
        setProbCatchLabel(dataValues.get(DATA_FIELDS.get(16)),"Number Of Days Without Food");
        setProbMax(dataValues.get(DATA_FIELDS.get(17)),"15");
        setProbMax(dataValues.get(DATA_FIELDS.get(18)),"20");
        setProbMax(dataValues.get(DATA_FIELDS.get(20)),"20");
        setProbCatch(dataValues.get(DATA_FIELDS.get(19)),10);
        setColors(dataValues.get(DATA_FIELDS.get(25)),"Red,Green,White");
        setStartingConfig(dataValues.get(DATA_FIELDS.get(24)),configRandom);
        setConcentration(dataValues.get(DATA_FIELDS.get(23)),"0.8,0.1");
        setBoundary(dataValues.get(DATA_FIELDS.get(24)),"NotToroidal");

    }



}

