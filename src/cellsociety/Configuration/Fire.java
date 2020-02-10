package cellsociety.Configuration;

import javafx.scene.control.Alert;

import java.awt.*;
import java.util.List;
import java.util.Map;


public class Fire extends CommonMethods {
        public static final String DATA_TYPE = "Fire";
        // field names expected to appear in data file holding values for this object
        // NOTE: simple way to create an immutable list

        public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
                "top","bottom","neighbours","probCatch","type1","type2","type3","probCatchLabel","maxProbCatch","neighPattern","shape","concentration","initial","colors");

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

        public Fire (Map<String, String> dataValues) throws NumberFormatException{
            setMyTitle(dataValues.get(DATA_FIELDS.get(0)));
            setMyStates(dataValues.get(DATA_FIELDS.get(1)),3);
            setMyDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
            setMyLeft(dataValues.get(DATA_FIELDS.get(4)));
            setMyRight(dataValues.get(DATA_FIELDS.get(5)));
            setMyTop(dataValues.get(DATA_FIELDS.get(6)),noRow);
            setMyBottom(dataValues.get(DATA_FIELDS.get(7)),noRow);
            setMyNeighbours(dataValues.get(DATA_FIELDS.get(8)),4);
            setMyProbCatch(dataValues.get(DATA_FIELDS.get(9)),defaultProb);
            setMyType1(dataValues.get(DATA_FIELDS.get(10)),"Empty");
            setMyType2(dataValues.get(DATA_FIELDS.get(11)),"Burnt");
            setMyType3(dataValues.get(DATA_FIELDS.get(12)),"Tree");
            setMyProbCatchLabel(dataValues.get(DATA_FIELDS.get(13)),"Probability of Catching on Fire");
            setMyProbMax(dataValues.get(DATA_FIELDS.get(14)),"1");
            setMyNeighPattern(dataValues.get(DATA_FIELDS.get(15)),neighbourPattern);
            setMyShape(dataValues.get(DATA_FIELDS.get(16)),initialShape);
            setMyConcentration(dataValues.get(DATA_FIELDS.get(17)),iniConc1);
            setMyStartingConfig(dataValues.get(DATA_FIELDS.get(18)),configRandom);
            setMyColors(dataValues.get(DATA_FIELDS.get(19)),"ffffff,69e23f,e2563f");
        }
}



