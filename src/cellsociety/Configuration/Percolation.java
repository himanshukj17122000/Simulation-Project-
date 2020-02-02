package cellsociety.Configuration;

import java.util.List;
import java.util.Map;

public class Percolation extends Configuration {
    public static final String DATA_TYPE = "Percolation";
    // field names expected to appear in data file holding values for this object
    // NOTE: simple way to create an immutable list
    public static final List<String> DATA_FIELDS = List.of("title", "maxStates", "rows","columns","left","right",
            "top","bottom","neighbours","type1","type2","type3");
    /**
     * Create game data from given data.
     */
    @Override
    public void paraTitle (String title) {
        myTitle = title;
    }

    /**
     * Create game data from data structure of Strings.
     *
     * @param dataValues map of field names to their values
     */
    public Percolation (Map<String, String> dataValues) {
        myTitle=dataValues.get(DATA_FIELDS.get(0));
        maxStates=Integer.parseInt(dataValues.get(DATA_FIELDS.get(1)));
        rows=Integer.parseInt(dataValues.get(DATA_FIELDS.get(2)));
        columns=Integer.parseInt(dataValues.get(DATA_FIELDS.get(3)));
        left=Integer.parseInt(dataValues.get(DATA_FIELDS.get(4)));
        right=Integer.parseInt(dataValues.get(DATA_FIELDS.get(5)));
        top=Integer.parseInt(dataValues.get(DATA_FIELDS.get(6)));
        bottom=Integer.parseInt(dataValues.get(DATA_FIELDS.get(7)));
        neighbours=Integer.parseInt(dataValues.get(DATA_FIELDS.get(8)));
        type1=dataValues.get(DATA_FIELDS.get(9));
        type2=dataValues.get(DATA_FIELDS.get(10));
        type3=dataValues.get(DATA_FIELDS.get(11));
    }
}
