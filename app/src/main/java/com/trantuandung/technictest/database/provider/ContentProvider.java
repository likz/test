package com.trantuandung.technictest.database.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.trantuandung.technictest.database.DBHelper;
import com.trantuandung.technictest.database.contract.BookContract;
import com.trantuandung.technictest.database.contract.OfferContract;
import com.trantuandung.technictest.database.enums.DBTable;
import com.trantuandung.technictest.database.enums.DBUri;
import com.trantuandung.technictest.database.model.Book;

import java.util.HashMap;
import java.util.Map;

public class ContentProvider extends android.content.ContentProvider{

    private static SQLiteDatabase db;
    static final Map<DBTable, String> tableTypes;
    static {
        tableTypes = new HashMap<>();
        tableTypes.put(DBTable.BOOK, BookContract.CONTENT_ITEM_TYPE);
        tableTypes.put(DBTable.OFFER, OfferContract.CONTENT_ITEM_TYPE);
    }

    @Override
    public boolean onCreate() {
        DBHelper dbHelper = new DBHelper(getContext());
        db = dbHelper.getWritableDatabase();
        return db != null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        DBUri sourceUri = DBUri.matchUri(uri);
        if (sourceUri != null) {
            return tableTypes.get(sourceUri.getTable());
        }
        return null;
    }


    //TODO implement all request (query, delete, insert, update)
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }



    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
