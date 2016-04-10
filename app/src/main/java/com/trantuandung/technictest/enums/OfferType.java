package com.trantuandung.technictest.enums;

import java.util.HashMap;

public enum OfferType {
    UNKNOWN("unknown"),
    PERCENTAGE("percentage"),
    MINUS("minus"),
    SLICE("slice");

    static final HashMap<String, OfferType> values;

    static {
        values = new HashMap<String, OfferType>();
        for (OfferType type : OfferType.values()) {
            values.put(type.value, type);
        }
    }

    private final String value;

    OfferType(String value) {
        this.value = value;
    }

    public static OfferType matchValue(String value) {
        return values.get(value);
    }

    public String getValue() {
        return value;
    }

}
