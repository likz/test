package com.trantuandung.technictest.database.contract;

import android.content.ContentResolver;
import android.provider.BaseColumns;

import com.trantuandung.technictest.database.ProviderInfos;
import com.trantuandung.technictest.database.enums.DBTable;

public class BookContract implements BaseColumns {
    public static final String COL_ISBN = "isbn";
    public static final String COL_TITLE = "title";
    public static final String COL_PRICE = "price";
    public static final String COL_COVER = "cover";

    public static final String SORT_ORDER_TITLE = "lower(" + COL_TITLE + ") ASC";
    public static final String SORT_ORDER_PRICE = COL_PRICE + " ASC, " + "lower(" + COL_TITLE + ") ASC";

    public static final String TABLE_NAME = DBTable.BOOK.getValue();

    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + ProviderInfos.AUTHORITY + "." + TABLE_NAME;

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_ISBN + " TEXT NOT NULL, "
            + COL_TITLE + " TEXT NOT NULL, "
            + COL_PRICE + " INTEGER DEFAULT 0, "
            + COL_COVER + " TEXT DEFAULT NULL);"
            + String.format("CREATE INDEX %s_index on %s (%s)", TABLE_NAME, TABLE_NAME, COL_ISBN) + ";";
    //
    private static final String[] projection = new String[]{
            _ID,
            COL_ISBN,
            COL_TITLE,
            COL_PRICE,
            COL_COVER
    };

    public static String[] getProjection() {
        return projection;
    }
}
