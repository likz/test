package com.trantuandung.technictest.view.enums;

import java.util.HashMap;


/**
 * Enum des adapters of recycleview in UsersFragment
 */
public enum UserAdapterType {
    NORMAL("normal"),
    PANNIER("pannier");

    static final HashMap<String, UserAdapterType> values;

    static {
        values = new HashMap<String, UserAdapterType>();
        for (UserAdapterType table : UserAdapterType.values()) {
            values.put(table.value, table);
        }
    }

    private final String value;

    UserAdapterType(String value) {
        this.value = value;
    }

    public static UserAdapterType matchValue(String value) {
        return values.get(value);
    }

    public String getValue() {
        return this.value;
    }
}
