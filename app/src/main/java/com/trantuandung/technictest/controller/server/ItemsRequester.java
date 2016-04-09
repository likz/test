package com.trantuandung.technictest.controller.server;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trantuandung.technictest.controller.server.configure.ServerConfiguration;
import com.trantuandung.technictest.controller.server.connected.ServerInterface;
import com.trantuandung.technictest.database.model.Book;
import com.trantuandung.technictest.database.model.Offer;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by likz on 09/04/16.
 */
public class ItemsRequester {
    private final static String TAG = ItemsRequester.class.getSimpleName();

    public List<Book> getAllBook() throws Exception {
        ServerInterface serverInterface = new ServerInterface();
        try {
            String jsonBody = serverInterface.makeRequest(ServerConfiguration.DEFAULT_HOST_BOOKS);
            Log.i(TAG, "testGetAllBook jsonBoby = " + jsonBody);

            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Book>>() {}.getType();
            return gson.fromJson(jsonBody, collectionType);
        } catch (Exception e) {
            System.err.print("testGetAllBook " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public List<Offer> getAllOfferByBooks(String isbn) throws Exception {
        ServerInterface serverInterface = new ServerInterface();
        try {
            String jsonBody = serverInterface.makeRequest(String.format(ServerConfiguration.DEFAULT_HOST_COMMERCIAL_OFFERS,isbn));
            Log.i(TAG, "getAllOfferByBooks jsonBoby = " + jsonBody +"\n");

            JSONObject offerts = new JSONObject(jsonBody);

            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Offer>>() {}.getType();
            return gson.fromJson(offerts.getJSONArray("offers").toString(), collectionType);
        } catch (Exception e) {
            System.err.print("getAllOfferByABookCorrect " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
