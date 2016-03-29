package com.trantuandung.technictest.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.trantuandung.technictest.database.contract.BookContract;
import com.trantuandung.technictest.database.contract.OfferContract;

public class DBHelper extends SQLiteOpenHelper {
    private final String TAG = DBHelper.class.getName();

    // DB Name, same is used to name the sqlite DB file
    private static final String DB_NAME = "dbTestTechnic";
    // If you change the database schema, you must increment the database version.
    private static final int DB_VERSION = 1;

    //###############################################################
    // TABLE LIST AND CREATION SCRIPT LIST
    //###############################################################
    // TABLE NAME LIST
    private static final String[] TABLE_NAMES = new String[]{
            BookContract.TABLE_NAME,
            OfferContract.TABLE_NAME
    };
    //
    // TABLE CREATION SCRIPT LIST
    private static final String[] TABLE_SCRIPTS = new String[]{
            BookContract.CREATE_TABLE,
            OfferContract.CREATE_TABLE
    };

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Called when the database is created for the
        // first time. This is where the creation of
        // tables and the initial population of the tables should happen.
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade: " + oldVersion + " > " + newVersion);
        if (oldVersion < newVersion) {
            Log.i(TAG, "onUpgrade reindex");
//            DebugTracer.trace("onUpgrade");
            // ADD INDEXES
            reindex(db);
        }
    }

    public void reindex(SQLiteDatabase db) {
        try {
            // INDEX DE LA TABLE DES FICHIERS
            db.execSQL(String.format("CREATE INDEX IF NOT EXIST %s_index on %s (%s)", BookContract.TABLE_NAME, BookContract.TABLE_NAME, BookContract.COL_ISBN));
            db.execSQL(String.format("CREATE INDEX IF NOT EXIST %s_index on %s (%s, %s)", OfferContract.TABLE_NAME, OfferContract.TABLE_NAME, OfferContract.COL_TYPE, OfferContract.COL_VALUE));
        } catch (Exception e) {
            Log.e(TAG, "dropTables ERROR creating indexes on: " + e.getMessage());
        }
    }

    public void createTables(SQLiteDatabase db) {
        Log.i(TAG, "createTables");
//        DebugTracer.trace("createTables");
        for (String script : TABLE_SCRIPTS) {
            try {
                db.execSQL(script);
            } catch (SQLException e) {
                Log.e(TAG, "createTables ERROR on: " + e.getMessage());
            }
        }
    }

    public void dropTables(SQLiteDatabase db) {
        Log.i(TAG, "dropTables");
        for (String tablename : TABLE_NAMES) {
            if (tablename.equalsIgnoreCase(BookContract.TABLE_NAME)) {
                continue;
            }
            try {
                db.execSQL("DROP TABLE IF EXISTS " + tablename);
                Log.i(TAG, "dropTables: " + tablename + " dropped");
            } catch (Exception e) {
                Log.e(TAG, "dropTables ERROR: " + tablename + " does not exists");
            }
        }
    }

    /**
     * Drop tables and recreate them.
     * <p>
     * Cleanup the tables just like
     * </p>
     */
    public void recreateTables(SQLiteDatabase db) {
        Log.i(TAG, "recreateTables");
        if (db != null) {
            db.beginTransaction();
            try {
                dropTables(db);
                createTables(db);
                db.setTransactionSuccessful();
                Log.i(TAG, "recreateTables | SQLiteDatabase transaction successful!");
            } catch (Exception e) {
                Log.e(TAG, "recreateTables | SQLiteDatabase transaction error: " + e.getMessage());
            } finally {
                db.endTransaction();
            }
        } else {
            Log.e(TAG, "recreateTables | SQLiteDatabase is null");
        }
    }
}
