package cellsociety.Configuration;

import java.util.List;
import java.util.Map;

public class Rps extends CommonMethods {
    public static final String DATA_TYPE = "Rps";
    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","probCatch","type1","type2","type3","probCatchLabel","maxProbCatch","neighPattern","shape", "randomNumber", "maxRandomNumber","randomLabel","initial","colors");

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
        setMyStates(dataValues.get(DATA_FIELDS.get(1)));
        setMyDimensions(dataValues.get(DATA_FIELDS.get(3)),dataValues.get(DATA_FIELDS.get(2)));
        setMyLeft(dataValues.get(DATA_FIELDS.get(4)));
        setMyRight(dataValues.get(DATA_FIELDS.get(5)));
        setMyTop(dataValues.get(DATA_FIELDS.get(6)));
        setMyBottom(dataValues.get(DATA_FIELDS.get(7)));
        setMyNeighbours(dataValues.get(DATA_FIELDS.get(8)));

        try{if(isStringOnlyAlphabet(dataValues.get(DATA_FIELDS.get(9)))){
            throw new NumberFormatException();
        }setProbCatch(Double.parseDouble(dataValues.get(DATA_FIELDS.get(9))));} catch (NumberFormatException e) {
            setProbCatch((double) 4);
        }
        try{setType1(dataValues.get(DATA_FIELDS.get(10)));} catch (Exception e) {
            setType1("Rock");
        }
        try{setType2(dataValues.get(DATA_FIELDS.get(11)));} catch (Exception e) {
            setType2("Paper");
        }
        try{ setType3(dataValues.get(DATA_FIELDS.get(12)));} catch (Exception e) {
            setType3("Scissor");
        }
        try{setProbCatchLabel(dataValues.get(DATA_FIELDS.get(13)));} catch (Exception e) {
            setProbCatchLabel("Threshold for winning");
        }
        try{if(isStringOnlyAlphabet(dataValues.get(DATA_FIELDS.get(14)))){
            throw new NumberFormatException();
        }setMaxProb(Double.parseDouble(dataValues.get(DATA_FIELDS.get(14))));} catch (NumberFormatException e) {
            setMaxProb((double) 8);
        }
        try{setNeighPattern(dataValues.get(DATA_FIELDS.get(15)));} catch (Exception e) {
            setNeighPattern(neighbourPattern);
        }
        try{setShape(dataValues.get(DATA_FIELDS.get(16)));} catch (Exception e) {
            setShape(initialShape);
        }
        try{if(isStringOnlyAlphabet(dataValues.get(DATA_FIELDS.get(17)))){
            throw new NumberFormatException();
        }setProbCatch(Double.parseDouble(dataValues.get(DATA_FIELDS.get(17))));} catch (NumberFormatException e) {
            setProbCatch((double) 2);
        }
        try{if(isStringOnlyAlphabet(dataValues.get(DATA_FIELDS.get(18)))){
            throw new NumberFormatException();
        }setMaxProb(Double.parseDouble(dataValues.get(DATA_FIELDS.get(18))));} catch (NumberFormatException e) {
            setMaxProb((double) 4);
        }
        try{setProbCatchLabel(dataValues.get(DATA_FIELDS.get(19)));} catch (Exception e) {
            setProbCatchLabel("Value of the Random Number");
        }
        try{setConcentration(dataValues.get(DATA_FIELDS.get(20)));} catch (Exception e) {
            setConcentration(iniConc2);
        }
        try{setStartingConfig(dataValues.get(DATA_FIELDS.get(21)));} catch (Exception e) {
            setStartingConfig(configRandom);
        }
        try{setColors(dataValues.get(DATA_FIELDS.get(22)));}catch (Exception e){
            setColors("Red,Green,White");
        }
    }

}
