package com.example.hands.database;

import android.provider.BaseColumns;

public class User {
    public User() {
    }
    public static class UserDetails implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COL_ID = "id";
        public static final String COL_NAME = "name";
        public static final String COL_IDADE = "idade";
        public static final String COL_TEMP = "temperatura";
        public static final String COL_TOSSE = "tosse";
        public static final String COL_DOR = "dor";
        public static final String COL_PAIS= "pais";
        public static final String COL_SEMANA = "semana";
        public static final String Col_STATUS = "status";


    }

}
