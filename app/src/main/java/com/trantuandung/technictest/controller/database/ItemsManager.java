package com.trantuandung.technictest.controller.database;

import android.content.ContentResolver;
import android.content.Context;

/**
 * Manager private items
 * <ul>
 * <li>Make all item's request (insert, delete, update, query) to Database</li>
 * </ul>
 */
public class ItemsManager {
    private final String TAG = ItemsManager.class.getName();

    private final Context context;
    ContentResolver contentResolver;

    public ItemsManager(Context context) {
        this.context = context;
        contentResolver = context.getContentResolver();
    }
}
