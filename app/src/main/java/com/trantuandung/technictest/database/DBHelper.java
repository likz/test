package com.trantuandung.technictest.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.trantuandung.technictest.listener.BookListener;
import com.trantuandung.technictest.listener.PannierListener;
import com.trantuandung.technictest.model.Book;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper implements PannierListener,BookListener {
    private final String TAG = DBHelper.class.getName();

    // DB Name, same is used to name the sqlite DB file
    private static final String DB_NAME = "dbtest";
    // If you change the database schema, you must increment the database version.
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "createTables");

        try {
            db.execSQL(BookContract.CREATE_TABLE);
        } catch (SQLException e) {
            Log.e(TAG, "createTables ERROR on: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade: " + oldVersion + " > " + newVersion);
        if (oldVersion < newVersion) {
            Log.i(TAG, "onUpgrade reindex");
            // ADD INDEXES
            reindex(db);
        }
    }

    public void reindex(SQLiteDatabase db) {
        try {
            // INDEX DE LA TABLE DES FICHIERS
            db.execSQL(String.format("CREATE INDEX IF NOT EXIST %s_index on %s (%s)", BookContract.TABLE_NAME, BookContract.TABLE_NAME, BookContract.COL_ISBN));
        } catch (Exception e) {
            Log.e(TAG, "dropTables ERROR creating indexes on: " + e.getMessage());
        }
    }

    @Override
    public void addBook(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            Log.i(TAG, "addBook: " + book.getTitle());
            db.execSQL("INSERT INTO book (isbn, title, price, cover) VALUES (?, ?, ?, ?)",
                    new Object[]{book.getIsbn(), book.getTitle(), book.getPrice(), book.getCover()});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "addBook ERROR on: " + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void deleteBook(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            Log.i(TAG, "deleteBook: " + book.getTitle());
            db.execSQL("DELETE FROM book WHERE book._id = ?",
                    new Object[]{book.getId()});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "deleteBook ERROR on: " + e.getMessage());
        } finally {
            db.endTransaction();
        }

    }

    @Override
    public int totalCart() {
        int total;
        Cursor cursor = getReadableDatabase().rawQuery("SELECT SUM(price) FROM book", null);
        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            total = 0;
        } else {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }

    @Override
    public List<Book> getBookList() {
        List<Book> bookList = new ArrayList<>();
        Cursor bookCursor = getReadableDatabase().rawQuery("SELECT * FROM book", null);
        if (bookCursor != null && bookCursor.getCount() > 0) {
            boolean hasNext = bookCursor.moveToFirst();
            while (hasNext) {
                bookList.add(new Book(bookCursor));
                hasNext = bookCursor.moveToNext();
            }
        }

        if (bookCursor != null) {
            bookCursor.close();
        }

        return bookList;
    }
}
