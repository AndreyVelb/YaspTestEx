package com.velb.yasptestex.configuration;

public class SqlInitQuery {


    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS BOOKS AS SELECT * FROM CSVREAD('/files/booksFile.csv')";

    public final static String ADD_COLUMN_PUBLICATION_DATE_MOD = "ALTER TABLE BOOKS ADD PUBLICATION_DATE_MOD DATE;";

    public final static String ADD_COLUMN_RATING_MOD = "ALTER TABLE BOOKS ADD RATING_MOD DECIMAL(10,2);";

    public final static String ADD_COLUMN_NUMBER_OF_VOTERS = "ALTER TABLE BOOKS ADD NUMBER_OF_VOTERS NUMERIC;";

    public final static String ADD_COLUMN_NUM_PAGES_MOD = "ALTER TABLE BOOKS ADD NUM_PAGES_MOD NUMERIC;";

    public final static String CREATE_ALIAS_FOR_PARSE_DATE = "CREATE ALIAS IF NOT EXISTS PARSE_DATE_OR_NULL AS '\n" +
            "import java.text.SimpleDateFormat;\n" +
            "import java.util.Date;\n" +
            "import java.util.Locale;\n" +
            "    @CODE\n" +
            "    java.sql.Date parseDateOrNull(String s) {\n" +
            "        try {\n" +
            "            SimpleDateFormat parser = new SimpleDateFormat(\"MMMM d, yyyy\", Locale.ENGLISH);\n" +
            "            Date date = parser.parse(s);\n" +
            "            SimpleDateFormat formatter = new SimpleDateFormat(\"yyyy-MM-dd\");\n" +
            "            String formattedDate = formatter.format(date);\n" +
            "            return java.sql.Date.valueOf(formattedDate);\n" +
            "        } catch (Exception e) {\n" +
            "            return null;\n" +
            "        }\n" +
            "    }\n" +
            "';\n";

    public final static String CREATE_ALIAS_FOR_PARSE_NUMERIC = "CREATE ALIAS IF NOT EXISTS PARSE_NUMERIC_OR_NULL AS '\n" +
            "    @CODE\n" +
            "    Integer parseNumericOrNull(String string) { \n" +
            "       try { \n" +
            "           return Integer.parseInt(string); \n" +
            "       } catch (Exception e) { \n" +
            "           return null; \n" +
            "       } \n" +
            "   } \n" +
            "';\n";

    public final static String UPDATE_COLUMN_PUBLICATION_DATE_MOD = "UPDATE BOOKS SET PUBLICATION_DATE_MOD = PARSE_DATE_OR_NULL(PUBLICATION_DATE)WHERE 1=1;";

    public final static String UPDATE_COLUMN_NUM_PAGES_MOD = "UPDATE BOOKS SET NUM_PAGES_MOD = PARSE_NUMERIC_OR_NULL(NUM_PAGES)WHERE 1=1;";

    public final static String UPDATE_COLUMN_RATING_MOD = "UPDATE BOOKS SET RATING_MOD = CAST (RATING_SCORE AS DECIMAL(10,2)) WHERE 1=1;";

    public final static String UPDATE_COLUMN_NUMBER_OF_VOTERS = "UPDATE BOOKS SET NUMBER_OF_VOTERS = CAST (NUM_RATINGS AS NUMERIC) WHERE 1=1;";

    public final static String DROP_COLUMN_PUBLICATION_DATE = "ALTER TABLE BOOKS DROP COLUMN PUBLICATION_DATE";

    public final static String DROP_COLUMN_NUM_PAGES = "ALTER TABLE BOOKS DROP COLUMN NUM_PAGES";

    public final static String DROP_COLUMN_RATING_SCORE = "ALTER TABLE BOOKS DROP COLUMN RATING_SCORE";

    public final static String DROP_COLUMN_NUM_RATINGS = "ALTER TABLE BOOKS DROP COLUMN NUM_RATINGS";
}

