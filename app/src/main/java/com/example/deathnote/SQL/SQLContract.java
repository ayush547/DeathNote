package com.example.deathnote.SQL;

import android.provider.BaseColumns;

public class SQLContract {
    private SQLContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String Persistent_key = "persistent_id";
        public static final String Location_type = "location_type";
        public static final String Month = "month";
        public static final String Outcome_date ="outcome_date";
        public static final String Outcome_status = "outcome_category";
        public static final String Category = "category";
        public static final String Latitude = "latitude";
        public static final String Longitude = "longitude";
        public static final String Id = "id";

        public static final String TableName = "CrimeFavourites";

        public static final String CREATE_QUERY = "CREATE TABLE " + TableName + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                Id + " TEXT, " +
                Persistent_key + " TEXT, " +
                Location_type + " TEXT, " +
                Month + " TEXT, " +
                Outcome_status + " TEXT, " +
                Outcome_date + " TEXT, " +
                Category + " TEXT," +
                Latitude + " REAL," +
                Longitude + " REAL)";

        public static final String DELETE_QUERY = "DROP TABLE IF EXISTS " + TableName + ";";
        public static final String GET_QUERY = "SELECT * FROM "+TableName;
    }
}
