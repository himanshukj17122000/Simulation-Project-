package cellsociety.Configuration;

import java.util.List;
import java.util.Map;


public class Fire extends Configuration{
        public static final String DATA_TYPE = "Fire";
        // field names expected to appear in data file holding values for this object
        // NOTE: simple way to create an immutable list

        public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
                "top","bottom","neighbours","probCatch","type1","type2","type3","probCatchLabel","maxProbCatch","neighPattern","shape","concentration","initial","colors","boundary");

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

        public Fire (Map<String, String> dataValues) throws NumberFormatException{
            setTitle(dataValues.get(DATA_FIELDS.get(0)));
            setStates(dataValues.get(DATA_FIELDS.get(1)),3);
            setDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
            setLeft(dataValues.get(DATA_FIELDS.get(4)));
            setRight(dataValues.get(DATA_FIELDS.get(5)));
            setTop(dataValues.get(DATA_FIELDS.get(6)),noRow);
            setBottom(dataValues.get(DATA_FIELDS.get(7)),noRow);
            setNeighbours(dataValues.get(DATA_FIELDS.get(8)),4);
            setProbCatch(dataValues.get(DATA_FIELDS.get(9)),defaultProb);
            setType1(dataValues.get(DATA_FIELDS.get(10)),"Empty");
            setType2(dataValues.get(DATA_FIELDS.get(11)),"Burnt");
            setType3(dataValues.get(DATA_FIELDS.get(12)),"Tree");
            setProbCatchLabel(dataValues.get(DATA_FIELDS.get(13)),"Probability of Catching on Fire");
            setProbMax(dataValues.get(DATA_FIELDS.get(14)),"1");
            setNeighPattern(dataValues.get(DATA_FIELDS.get(15)),neighbourPattern);
            setShape(dataValues.get(DATA_FIELDS.get(16)),initialShape);
            setConcentration(dataValues.get(DATA_FIELDS.get(17)),iniConc1);
            setStartingConfig(dataValues.get(DATA_FIELDS.get(18)),configRandom);
            setColors(dataValues.get(DATA_FIELDS.get(19)),"ffffff,69e23f,e2563f");
            setBoundary(dataValues.get(DATA_FIELDS.get(20)),"NotToroidal");
        }
}



