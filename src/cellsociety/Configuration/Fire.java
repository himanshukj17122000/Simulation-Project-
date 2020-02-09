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





            try{if(isStringOnlyAlphabet(dataValues.get(DATA_FIELDS.get(14)))){
                throw new NumberFormatException();
            }setMaxProb(Double.parseDouble(dataValues.get(DATA_FIELDS.get(14))));} catch (NumberFormatException e) {
                setMaxProb(Double.parseDouble("1"));
            }
            try{setNeighPattern(dataValues.get(DATA_FIELDS.get(15)));} catch (Exception e) {
                setNeighPattern(neighbourPattern);
            }
            try{setShape(dataValues.get(DATA_FIELDS.get(16)));} catch (Exception e) {
                setShape(initialShape);
            }
            try{setConcentration(dataValues.get(DATA_FIELDS.get(17)));} catch (Exception e) {
                setConcentration(iniConc1);
            }
            try{setStartingConfig(dataValues.get(DATA_FIELDS.get(18)));}catch (Exception e){
                setStartingConfig(configRandom);
            }
            try{setColors(dataValues.get(DATA_FIELDS.get(19)));}catch (Exception e){
                setColors("ffffff,69e23f,e2563f");
            }
        }

}



