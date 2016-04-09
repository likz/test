package com.trantuandung.technictest.database.enums;

import java.util.HashMap;


/**
 * Enum des tables
 */
public enum DBTable {
    BOOK("book"),
    OFFER("offer");

    static final HashMap<String, DBTable> values;

    static {
        values = new HashMap<String, DBTable>();
        for (DBTable table : DBTable.values()) {
            values.put(table.value, table);
        }
    }

    private final String value;

    DBTable(String value) {
        this.value = value;
    }

    public static DBTable matchValue(String value) {
        return values.get(value);
    }

    public String getValue() {
        return this.value;
    }
}
