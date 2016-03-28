package com.trantuandung.technictest.database;

import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ProviderInfos {
    private static final String TAG = ProviderInfos.class.getSimpleName();
    public static final String AUTHORITY = "com.trantuandung.technictest.ContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    private static final String dateString = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat(dateString, Locale.FRENCH);
    private static final String dateStringDay = "yyyy-MM-dd";
    public static final SimpleDateFormat dateDayFormat = new SimpleDateFormat(dateStringDay, Locale.FRENCH);
    private static final String dateStringNoSpace = "yyyy-MM-dd_HH:mm:ss";
    public static final SimpleDateFormat dateNoSpaceFormat = new SimpleDateFormat(dateStringNoSpace, Locale.FRENCH);

}
