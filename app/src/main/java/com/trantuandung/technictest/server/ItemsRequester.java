package com.trantuandung.technictest.server;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trantuandung.technictest.server.configure.ServerConfiguration;
import com.trantuandung.technictest.server.connected.ServerInterface;
import com.trantuandung.technictest.model.Book;
import com.trantuandung.technictest.model.Offer;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

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
        try {
            ServerInterface serverInterface = new ServerInterface();
            String jsonBody = serverInterface.makeRequest(String.format(ServerConfiguration.DEFAULT_HOST_COMMERCIAL_OFFERS,isbn));
            Log.i(TAG, "getAllOfferByBooks jsonBoby = " + jsonBody +"\n");

            JSONObject offerts = new JSONObject(jsonBody);

            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Offer>>() {}.getType();
            return gson.fromJson(offerts.getJSONArray("offers").toString(), collectionType);
        } catch (Exception e) {
            Log.e(TAG, "getAllOfferByABookCorrect " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Load Image from item's source data in database or from cloud
     * @param context context
     * @param url image's url
     * @param imageView item's thumbnail view
     */
    public static void loadImageIntoView(Context context, String url, ImageView imageView){
        if(context == null){
            Log.e(TAG, "loadImageIntoView: context not found");
            return;
        }

        if(TextUtils.isEmpty(url)){
            Log.e(TAG, "loadImageIntoView: url not found");
            return;
        }

        if(imageView == null){
            Log.e(TAG, "loadImageIntoView: view not found");
            return;
        }

        //load image's file from cloud
        Glide.with(context)
                .load(url)
                .skipMemoryCache(false)
                .priority(Priority.HIGH)
                .centerCrop()
                .into(imageView);
    }
}
