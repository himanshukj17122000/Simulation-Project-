package cellsociety.Configuration;

import java.util.List;
import java.util.Map;

public class Rps extends Configuration {
    public static final String DATA_TYPE = "Rps";
    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","probCatch","type1","type2","type3","probCatchLabel","maxProbCatch","neighPattern","shape",
            "randomNumber", "maxRandomNumber","randomLabel","concentration","initial","colors","boundary");

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

    public Rps (Map<String, String> dataValues) throws NumberFormatException {
        setTitle(dataValues.get(DATA_FIELDS.get(0)));
        setStates(dataValues.get(DATA_FIELDS.get(1)),3);
        setDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
        setLeft(dataValues.get(DATA_FIELDS.get(4)));
        setRight(dataValues.get(DATA_FIELDS.get(5)));
        setTop(dataValues.get(DATA_FIELDS.get(6)),noRow);
        setBottom(dataValues.get(DATA_FIELDS.get(7)),noRow);
        setNeighbours(dataValues.get(DATA_FIELDS.get(8)),8);
        setProbCatch(dataValues.get(DATA_FIELDS.get(9)),4);
        setType1(dataValues.get(DATA_FIELDS.get(10)),"Rock");
        setType2(dataValues.get(DATA_FIELDS.get(11)),"Paper");
        setType3(dataValues.get(DATA_FIELDS.get(12)),"Scissor");
        setProbCatchLabel(dataValues.get(DATA_FIELDS.get(13)),"Threshold for winning");
        setProbMax(dataValues.get(DATA_FIELDS.get(14)),"8");
        setNeighPattern(dataValues.get(DATA_FIELDS.get(15)),"11111111");
        setShape(dataValues.get(DATA_FIELDS.get(16)),initialShape);
        setProbCatch(dataValues.get(DATA_FIELDS.get(17)),2);
        setProbMax(dataValues.get(DATA_FIELDS.get(18)),"4");
        setProbCatchLabel(dataValues.get(DATA_FIELDS.get(19)),"Value of the Random Number");
        setConcentration(dataValues.get(DATA_FIELDS.get(20)),iniConc2);
        setStartingConfig(dataValues.get(DATA_FIELDS.get(21)),configRandom);
        setColors(dataValues.get(DATA_FIELDS.get(22)),"Red,Green,White");
        setBoundary(dataValues.get(DATA_FIELDS.get(23)),"NotToroidal");

    }

}
