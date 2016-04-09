package com.trantuandung.technictest.controller.server.connected;

import android.text.TextUtils;

import com.trantuandung.technictest.exception.RequestErrorException;
import com.trantuandung.technictest.exception.UrlEmptyErrorException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServerInterface {

    public String makeRequest(String url) throws IOException, UrlEmptyErrorException, RequestErrorException {
        if(TextUtils.isEmpty(url)){
            throw new UrlEmptyErrorException("url empty");
        }

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        int responseCode = response.code();

        if (responseCode >= 200 && responseCode < 300){
            return response.body().string();
        }else{
            throw new RequestErrorException("Request error, response code = " + responseCode);
        }
    }
}
