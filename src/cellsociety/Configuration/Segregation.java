package cellsociety.Configuration;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.util.List;
import java.util.Map;

public class Segregation extends CommonMethods {
    public static final String DATA_TYPE = "Segregation";
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

    public Segregation (Map<String, String> dataValues) throws NumberFormatException{
        setMyTitle(dataValues.get(DATA_FIELDS.get(0)));
        setMyStates(dataValues.get(DATA_FIELDS.get(1)),3);
        setMyDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
        setMyLeft(dataValues.get(DATA_FIELDS.get(4)));
        setMyRight(dataValues.get(DATA_FIELDS.get(5)));
        setMyTop(dataValues.get(DATA_FIELDS.get(6)),noRow);
        setMyBottom(dataValues.get(DATA_FIELDS.get(7)),noRow);
        setMyNeighbours(dataValues.get(DATA_FIELDS.get(8)),8);

        try {if(isStringOnlyAlphabet(dataValues.get(DATA_FIELDS.get(9)))){
            throw new NumberFormatException();
        }setProbCatch(Double.parseDouble(dataValues.get(DATA_FIELDS.get(9))));} catch (NumberFormatException e) {
            setProbCatch(defaultProb);
        }
        try {setType1(dataValues.get(DATA_FIELDS.get(10)));} catch (Exception e) {
            setType1("Empty");
        }
        try {setType2(dataValues.get(DATA_FIELDS.get(11)));} catch (Exception e) {
            setType2("Race1");
        }
        try {setType3(dataValues.get(DATA_FIELDS.get(12)));} catch (Exception e) {
            setType3("Race2");
        }
        try {setProbCatchLabel(dataValues.get(DATA_FIELDS.get(13)));} catch (Exception e) {
            setProbCatchLabel("Threshold Value");
        }
        try {if(isStringOnlyAlphabet(dataValues.get(DATA_FIELDS.get(14)))){
            throw new NumberFormatException();
        }setMaxProb(Double.parseDouble(dataValues.get(DATA_FIELDS.get(14))));} catch (NumberFormatException e) {
            setMaxProb(defaultMaxProb);
        }
        try {setNeighPattern(dataValues.get(DATA_FIELDS.get(15)));} catch (Exception e) {
            setNeighPattern(neighbourPattern);
        }
        try {setShape(dataValues.get(DATA_FIELDS.get(16)));} catch (Exception e) {
            setShape(initialShape);
        }
        try {setConcentration(dataValues.get(DATA_FIELDS.get(17)));} catch (Exception e) {
            setConcentration(iniConc2);
        }
        try {setStartingConfig(dataValues.get(DATA_FIELDS.get(18)));} catch (Exception e) {
            setStartingConfig(configRandom);
        }
        try{setColors(dataValues.get(DATA_FIELDS.get(19)));}catch (Exception e){
            setColors("Red,Green,White");
        }
    }


    }


