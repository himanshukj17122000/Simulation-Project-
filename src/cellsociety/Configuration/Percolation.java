package cellsociety.Configuration;

import java.util.List;
import java.util.Map;

public class Percolation extends Configuration {
    public static final String DATA_TYPE = "Percolation";
    // field names expected to appear in data file holding values for this object
    // NOTE: simple way to create an immutable list
    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","type1","type2","type3");
    //,"neighPattern"
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
    public Percolation (Map<String, String> dataValues) {
        setMyTitle(dataValues.get(DATA_FIELDS.get(0)));
        setMaxStates(Integer.parseInt(dataValues.get(DATA_FIELDS.get(1))));
        setRows(Integer.parseInt(dataValues.get(DATA_FIELDS.get(2))));
        setColumns(Integer.parseInt(dataValues.get(DATA_FIELDS.get(3))));
        setLeft(Integer.parseInt(dataValues.get(DATA_FIELDS.get(4))));
        setRight(Integer.parseInt(dataValues.get(DATA_FIELDS.get(5))));
        setTop(Integer.parseInt(dataValues.get(DATA_FIELDS.get(6))));
        setBottom(Integer.parseInt(dataValues.get(DATA_FIELDS.get(7))));
        setNeighbours(Integer.parseInt(dataValues.get(DATA_FIELDS.get(8))));
        setType1(dataValues.get(DATA_FIELDS.get(9)));
        setType2(dataValues.get(DATA_FIELDS.get(10)));
        setType3(dataValues.get(DATA_FIELDS.get(11)));
//        setNeighPattern(dataValues.get(DATA_FIELDS.get(12)));
    }
}
