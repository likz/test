package com.trantuandung.technictest.database.contract;

import android.content.ContentResolver;
import android.provider.BaseColumns;

import com.trantuandung.technictest.database.ProviderInfos;
import com.trantuandung.technictest.database.enums.DBTable;

public class OfferContract implements BaseColumns{
    public static final String COL_TYPE = "type";
    public static final String COL_SLICE_VALUE = "sliceValue";
    public static final String COL_VALUE = "value";

    public static final String SORT_ORDER_TITLE = "lower(" + COL_TYPE + ") ASC";
    public static final String SORT_ORDER_VALUE = COL_VALUE + " ASC, " + "lower(" + COL_TYPE + ") ASC";
    public static final String SORT_ORDER_SLICE_VALUE = COL_SLICE_VALUE + " ASC, " + "lower(" + COL_TYPE + ") ASC";

    public static final String TABLE_NAME = DBTable.OFFER.getValue();

    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + ProviderInfos.AUTHORITY + "." + TABLE_NAME;

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TYPE + " TEXT NOT NULL, "
            + COL_SLICE_VALUE + " TEXT DEFAULT NULL, "
            + COL_VALUE + " INTEGER DEFAULT 0);"
            + String.format("CREATE INDEX %s_index on %s (%s,%s)", TABLE_NAME, TABLE_NAME, COL_TYPE, COL_VALUE) + ";";
    //
    private static final String[] projection = new String[]{
            _ID,
            COL_TYPE,
            COL_SLICE_VALUE,
            COL_VALUE
    };

    public static String[] getProjection() {
        return projection;
    }
}
