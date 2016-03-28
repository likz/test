package com.trantuandung.technictest.database.enums;

import android.content.UriMatcher;
import android.net.Uri;
import android.util.SparseArray;

import com.trantuandung.technictest.database.ProviderInfos;

import java.io.Serializable;

/**
 * Enum des URI pour le provider principal
 * parametres:
 * integer : identifiant de l'uri
 * booleen : necessite un utilisateur logue ou pas
 * enum : table correspondante
 * string: suffixe pour les 'sous-uri'
 * string : type de la donnee passe en parametre
 * <p/>
 * fonctions utilitaires pour recuperer la table associee, l'uri de base si uri complexe
 */
public enum DBUri implements Serializable {
    //  /# > integer,  /* > string
    // fichiers de config generaus
    ALL_BOOK(100, false, DBTable.BOOK, "", ""),
    BOOK_BY_ID(101, true, DBTable.BOOK, "bookid", "/*");

    public static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    private static SparseArray<DBTable> mapIntTable = new SparseArray<DBTable>();
    private static SparseArray<DBUri> mapIntUri;

    // DBUri matchers initialization
    static {
        // fill the mapIntUri & mapIntTable, prepare the UriMatcher
        mapIntUri = new SparseArray<>();
        for (DBUri uri : DBUri.values()) {
            mapIntUri.put(uri.value, uri);
            mapIntTable.put(uri.value, uri.getTable());
            MATCHER.addURI(ProviderInfos.AUTHORITY, uri.getMatcherSuffix(), uri.getValue());
        }
    }

    private final boolean needLogin;
    private final String queryby;
    private final String suffix;
    private final DBTable table;
    // INT VALUES FOR URIMATCHER
    private final int value;

    DBUri(int value, boolean needLogin, DBTable table, String suffix, String queryby) {
        this.value = value;
        this.needLogin = needLogin;
        this.table = table;
        this.suffix = suffix;
        this.queryby = queryby;
    }

    public static DBTable getTableForUri(Uri uri) {
        int key = MATCHER.match(uri);
        return mapIntTable.get(key);
    }

    public static DBUri matchUri(Uri uri) {
        return matchInt(MATCHER.match(uri));
    }

    public static DBUri matchInt(int key) {
        return mapIntUri.get(key);
    }

    public int getValue() {
        return this.value;
    }

    public boolean getNeedLogin() {
        return this.needLogin;
    }

    public Uri getContentUri() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(ProviderInfos.AUTHORITY);
        builder.appendPath(table.getValue());
        if (!suffix.isEmpty())
            builder.appendPath(suffix);
        return builder.build();
    }

    public String getStringUri() {
        if (!suffix.isEmpty())
            return "content://" + ProviderInfos.AUTHORITY + "/" + table.getValue() + "/" + suffix + "/";
        else
            return "content://" + ProviderInfos.AUTHORITY + "/" + table.getValue() + "/";
    }

    public String getMatcherSuffix() {
        if (!suffix.isEmpty()) {
            return table.getValue() + "/" + suffix + queryby;
        }
        return table.getValue() + queryby;
    }

    public DBTable getTable() {
        return this.table;
    }
}
