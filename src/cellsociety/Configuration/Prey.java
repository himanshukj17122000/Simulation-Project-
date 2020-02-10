package cellsociety.Configuration;

import java.util.List;
import java.util.Map;

public class Prey extends Configuration {
    public static final String DATA_TYPE = "Prey";

    // field names expected to appear in data file holding values for this object
    // NOTE: simple way to create an immutable list
    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","type1","type2","type3","fishBreed","sharkBreed","fishLabel","sharkLabel","numLabel","maxFishBreed"
    ,"maxSharkBreed","numWithoutFood","maxDays","neighPattern","shape","concentration","initial","colors");


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

    public Prey(Map<String, String> dataValues) throws NumberFormatException {
        setMyTitle(dataValues.get(DATA_FIELDS.get(0)));
        setMyStates(dataValues.get(DATA_FIELDS.get(1)),3);
        setMyDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
        setMyLeft(dataValues.get(DATA_FIELDS.get(4)));
        setMyRight(dataValues.get(DATA_FIELDS.get(5)));
        setMyTop(dataValues.get(DATA_FIELDS.get(6)),noRow);
        setMyBottom(dataValues.get(DATA_FIELDS.get(7)),noRow);
        setMyNeighbours(dataValues.get(DATA_FIELDS.get(8)),4);
        setMyType1(dataValues.get(DATA_FIELDS.get(9)),"Empty");
        setMyType2(dataValues.get(DATA_FIELDS.get(10)),"Fish");
        setMyType3(dataValues.get(DATA_FIELDS.get(11)),"Shark");
        setMyNeighPattern(dataValues.get(DATA_FIELDS.get(21)),"11111111");
        setMyShape(dataValues.get(DATA_FIELDS.get(22)),initialShape);
        setMyProbCatch(dataValues.get(DATA_FIELDS.get(12)),8);
        setMyProbCatch(dataValues.get(DATA_FIELDS.get(13)),8);
        setMyProbCatchLabel(dataValues.get(DATA_FIELDS.get(14)),"Fish Breeding Time");
        setMyProbCatchLabel(dataValues.get(DATA_FIELDS.get(15)),"Shark Breeding Time");
        setMyProbCatchLabel(dataValues.get(DATA_FIELDS.get(16)),"Number Of Days Without Food");
        setMyProbMax(dataValues.get(DATA_FIELDS.get(17)),"15");
        setMyProbMax(dataValues.get(DATA_FIELDS.get(18)),"20");
        setMyProbMax(dataValues.get(DATA_FIELDS.get(20)),"20");
        setMyProbCatch(dataValues.get(DATA_FIELDS.get(19)),10);
        setMyColors(dataValues.get(DATA_FIELDS.get(25)),"Red,Green,White");
        setMyStartingConfig(dataValues.get(DATA_FIELDS.get(24)),configRandom);
        setMyConcentration(dataValues.get(DATA_FIELDS.get(23)),"0.8,0.1");
    }



}

