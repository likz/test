package com.trantuandung.technictest;

import com.trantuandung.technictest.server.configure.ServerConfiguration;
import com.trantuandung.technictest.exception.RequestErrorException;
import com.trantuandung.technictest.exception.UrlEmptyErrorException;

import org.junit.Test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ServerInterfaceUnitTest {
    @Test
    public void testMakeRequestCorrect() throws Exception{

        Response response = makeRequest(ServerConfiguration.DEFAULT_HOST_BOOKS);
        assertNotNull(response);
        assertEquals(response.code(), 200);
    }

    @Test
    public void testMakeNewRequestCorrect() throws Exception{

        String json = makeNewRequest(ServerConfiguration.DEFAULT_HOST_BOOKS);
        assertNotNull(json);
    }

    @Test
    public void testMakeRequestNotCorrect() throws Exception{

        Response response = makeRequest(ServerConfiguration.DEFAULT_HOST_BOOKS + "s");
        assertNotNull(response);
        assertEquals(response.code(), 404);
    }

    @Test
    public void testMakeNewRequestNotCorrect() throws Exception{

        String json = makeNewRequest(ServerConfiguration.DEFAULT_HOST_BOOKS + "s");
        assertNotNull(json);
    }

    @Test
    public void testMakeRequestWithUrlEmpty() throws Exception {

        Response response = makeRequest("");
        assertNotNull(response);
        assertEquals(response.code(), 404);
    }

    @Test
    public void testMakeNewRequestWithUrlEmpty() throws Exception {

        Response response = makeRequest("");
        assertNotNull(response);
    }

    private Response makeRequest(String url) throws UrlEmptyErrorException, IOException {
        if(url == null || url.equalsIgnoreCase("")){
            throw new UrlEmptyErrorException("url empty");
        }
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        return client.newCall(request).execute();
    }

    private String makeNewRequest(String url) throws IOException, UrlEmptyErrorException, RequestErrorException {
        if(url == null || url.equalsIgnoreCase("")){
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