package com.trantuandung.technictest.controller.server;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trantuandung.technictest.controller.server.configure.ServerConfiguration;
import com.trantuandung.technictest.controller.server.connected.ServerInterface;
import com.trantuandung.technictest.database.model.Book;

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
}
