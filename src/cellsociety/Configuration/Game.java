package cellsociety.Configuration;

import cellsociety.Configuration.ESRBRating;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Game {


        // name in data file that will indicate it represents data for this type of object
        private static String DATA_TYPE = "";
        // field names expected to appear in data file holding values for this object
        // NOTE: simple way to create an immutable list
        public static final List<String> DATA_FIELDS = List.of(
                "title",
                "maxstates",
                "rows",
                "columns","left","Right","Top","Bottom","neighbours","probCatch","type1","type2","type3"
        );

        // specific data values for this instance
        private String myTitle;

        // NOTE: keep just as an example for converting toString(), otherwise not used
        private Map<String, String> myDataValues;


        /**
         * Create game data from given data.
         */
        public Game (String title, String publisher, ESRBRating rating, int year) {
            myTitle = title;
           
            // NOTE: this is useful so our code does not fail due to a NullPointerException
            myDataValues = new HashMap<>();
        }

        /**
         * Create game data from data structure of Strings.
         *
         * @param dataValues map of field names to their values
         */
        public Game (Map<String, String> dataValues) {
            this(dataValues.get(DATA_FIELDS.get(0)),
                    dataValues.get(DATA_FIELDS.get(1)),
                    ESRBRating.of(dataValues.get(DATA_FIELDS.get(2))),
                    Integer.parseInt(dataValues.get(DATA_FIELDS.get(3))));
            myDataValues = dataValues;
        }

        // NOTE: provides getters, but not setters
        /**
         * Returns title of this game.
         */
        public String getTitle () {
            return myTitle;
        }

        /**
         * Returns publisher of this game.
         */
        public String getPublisher () {
            return myPublisher;
        }

        /**
         * Returns rating of this game.
         */
        public ESRBRating getRating () {
            return myRating;
        }

        /**
         * Returns year this game was published.
         */
        public int getYear () {
            return myYear;
        }

        /**
         * @see Object#toString()
         */
        @Override
        public String toString () {
            StringBuilder result = new StringBuilder();
            result.append(DATA_TYPE + " = [\n");
            for (Map.Entry<String, String> e : myDataValues.entrySet()) {
                result.append("  ").append(e.getKey()).append(" = '").append(e.getValue()).append("',\n");
            }
            result.append("]\n");
            return result.toString();
        }
    }


