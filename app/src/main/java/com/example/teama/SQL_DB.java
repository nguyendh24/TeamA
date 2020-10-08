package com.example.teama;

import android.provider.BaseColumns;

public class SQL_DB {

        private SQL_DB(){}

        public static class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "Recipe";
            public static final String COLUMN_NAME_TITLE = "Type";
            public static final String COLUMN_NAME_SUBTITLE = "Subtitle";
        }

        private static final String SQL_CREATE_ENTRIES =
                " CREATE TABLE " + FeedEntry.TABLE_NAME + " (" + FeedEntry.COLUMN_NAME_TITLE + " TEXT," + FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";
        private static final String SQL_DELETE_ENTRIES=
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
}
