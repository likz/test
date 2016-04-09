package com.trantuandung.technictest.controller.server.configure;

/**
 * Server configuration
 */
public class ServerConfiguration {
    //server
    private static final String DEFAULT_HOST = "http://henri-potier.xebia.fr";
    public static final String DEFAULT_HOST_BOOKS = DEFAULT_HOST + "/books";
    public static final String DEFAULT_HOST_COMMERCIAL_OFFERS = DEFAULT_HOST_BOOKS + "/%s/commercialOffers";
}
