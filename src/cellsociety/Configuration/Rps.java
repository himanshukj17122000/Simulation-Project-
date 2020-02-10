package cellsociety.Configuration;

import java.util.List;
import java.util.Map;

public class Rps extends CommonMethods {
    public static final String DATA_TYPE = "Rps";
    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","probCatch","type1","type2","type3","probCatchLabel","maxProbCatch","neighPattern","shape", "randomNumber", "maxRandomNumber","randomLabel","concentration","initial","colors");

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

    public Rps (Map<String, String> dataValues) throws NumberFormatException {
        setMyTitle(dataValues.get(DATA_FIELDS.get(0)));
        setMyStates(dataValues.get(DATA_FIELDS.get(1)),3);
        setMyDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
        setMyLeft(dataValues.get(DATA_FIELDS.get(4)));
        setMyRight(dataValues.get(DATA_FIELDS.get(5)));
        setMyTop(dataValues.get(DATA_FIELDS.get(6)),noRow);
        setMyBottom(dataValues.get(DATA_FIELDS.get(7)),noRow);
        setMyNeighbours(dataValues.get(DATA_FIELDS.get(8)),8);
        setMyProbCatch(dataValues.get(DATA_FIELDS.get(9)),4);
        setMyType1(dataValues.get(DATA_FIELDS.get(10)),"Rock");
        setMyType2(dataValues.get(DATA_FIELDS.get(11)),"Paper");
        setMyType3(dataValues.get(DATA_FIELDS.get(12)),"Scissor");
        setMyProbCatchLabel(dataValues.get(DATA_FIELDS.get(13)),"Threshold for winning");
        setMyProbMax(dataValues.get(DATA_FIELDS.get(14)),"8");
        setMyNeighPattern(dataValues.get(DATA_FIELDS.get(15)),"11111111");
        setMyShape(dataValues.get(DATA_FIELDS.get(16)),initialShape);
        setMyProbCatch(dataValues.get(DATA_FIELDS.get(17)),2);
        setMyProbMax(dataValues.get(DATA_FIELDS.get(18)),"4");
        setMyProbCatchLabel(dataValues.get(DATA_FIELDS.get(19)),"Value of the Random Number");
        setMyConcentration(dataValues.get(DATA_FIELDS.get(20)),iniConc2);
        setMyStartingConfig(dataValues.get(DATA_FIELDS.get(21)),configRandom);
        setMyColors(dataValues.get(DATA_FIELDS.get(22)),"Red,Green,White");
    }

}
