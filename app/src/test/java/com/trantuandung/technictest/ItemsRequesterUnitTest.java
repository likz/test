package com.trantuandung.technictest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trantuandung.technictest.controller.server.configure.ServerConfiguration;
import com.trantuandung.technictest.controller.server.connected.ServerInterface;
import com.trantuandung.technictest.database.model.Book;
import com.trantuandung.technictest.database.model.Offer;

import org.json.JSONObject;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ItemsRequesterUnitTest {
    @Test
    public void testGetBody() throws Exception {
        ServerInterface serverInterface = new ServerInterface();
        try {
            String jsonBody = serverInterface.makeRequest(ServerConfiguration.DEFAULT_HOST_BOOKS);
            assertNotNull(jsonBody);
            System.out.print("testGetAllBook jsonBoby = " + jsonBody);
        } catch (Exception e) {
            System.err.print("testGetAllBook " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Test
    public void testGetBodyWithUrlNotCorrect() throws Exception {
        ServerInterface serverInterface = new ServerInterface();
        try {
            String jsonBody = serverInterface.makeRequest(ServerConfiguration.DEFAULT_HOST_BOOKS + "s");
            assertNotNull(jsonBody);
            System.out.print("testGetAllBookWithUrlNotCorrect jsonBoby = " + jsonBody);
        } catch (Exception e) {
            System.err.print("testGetAllBookWithUrlNotCorrect " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Test
    public void testGetAllBook() throws Exception {
        ServerInterface serverInterface = new ServerInterface();
        try {
            String jsonBody = serverInterface.makeRequest(ServerConfiguration.DEFAULT_HOST_BOOKS);
            assertNotNull(jsonBody);
            System.out.print("testGetAllBook jsonBoby = " + jsonBody);

            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Book>>() {}.getType();
            List<Book> booksList = gson.fromJson(jsonBody, collectionType);
            assertNotNull(booksList);
            System.out.print("testGetAllBook booksList = " + booksList.toString());
        } catch (Exception e) {
            System.err.print("testGetAllBook " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Test
    public void getAllOfferByABookCorrect() throws Exception {
        ServerInterface serverInterface = new ServerInterface();
        try {
            String isbn = "bbcee412-be64-4a0c-bf1e-315977acd924";
            String jsonBody = serverInterface.makeRequest(String.format(ServerConfiguration.DEFAULT_HOST_COMMERCIAL_OFFERS,""));
            assertNotNull(jsonBody);
            System.out.print("getAllOfferByABookCorrect jsonBoby = " + jsonBody +"\n");

            JSONObject offerts = new JSONObject(jsonBody);
            assertNotNull(offerts);
            System.out.print("getAllOfferByABookCorrect offerts = " + offerts +"\n");

            assertNotNull(offerts.getJSONArray("offers"));
            System.out.print("getAllOfferByABookCorrect offers = " + offerts.getJSONArray("offers") +"\n");

            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Offer>>() {}.getType();
            List<Offer> offersList = gson.fromJson(offerts.getJSONArray("offers").toString(), collectionType);
            assertNotNull(offersList);
            System.out.print("getAllOfferByABookCorrect offers = " + offersList.toString() +"\n");
        } catch (Exception e) {
            System.err.print("getAllOfferByABookCorrect " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Test
    public void getAllOfferByABookNotCorrectIsbnEmpty() throws Exception {
        ServerInterface serverInterface = new ServerInterface();
        try {
            String jsonBody = serverInterface.makeRequest(String.format(ServerConfiguration.DEFAULT_HOST_COMMERCIAL_OFFERS,""));
            assertNotNull(jsonBody);
            System.out.print("getAllOfferByABookCorrect jsonBoby = " + jsonBody +"\n");

            JSONObject offerts = new JSONObject(jsonBody);
            assertNotNull(offerts);
            System.out.print("getAllOfferByABookCorrect offerts = " + offerts +"\n");

            assertNotNull(offerts.getJSONArray("offers"));
            System.out.print("getAllOfferByABookCorrect offers = " + offerts.getJSONArray("offers") +"\n");

            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Offer>>() {}.getType();
            List<Offer> offersList = gson.fromJson(offerts.getJSONArray("offers").toString(), collectionType);
            assertNotNull(offersList);
            System.out.print("getAllOfferByABookCorrect offers = " + offersList.toString() +"\n");
        } catch (Exception e) {
            System.err.print("getAllOfferByABookCorrect " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }


    @Test
    public void getAllOfferByABookNotCorrect() throws Exception {
        ServerInterface serverInterface = new ServerInterface();
        try {
            String isbn = "c8fabf68-8374-48fe-a7ea-a00ccd07a000";
            String jsonBody = serverInterface.makeRequest(String.format(ServerConfiguration.DEFAULT_HOST_COMMERCIAL_OFFERS,isbn));
            assertNotNull(jsonBody);
            System.out.print("getAllOfferByABookNotCorrect jsonBoby = " + jsonBody);
        } catch (Exception e) {
            System.err.print("getAllOfferByABookNotCorrect " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Test
    public void getAllOfferByMoreBookCorrect() throws Exception {
        ServerInterface serverInterface = new ServerInterface();
        try {
            String isbn = "bbcee412-be64-4a0c-bf1e-315977acd924,a460afed-e5e7-4e39-a39d-c885c05db861,fcd1e6fa-a63f-4f75-9da4-b560020b6acc";
            String jsonBody = serverInterface.makeRequest(String.format(ServerConfiguration.DEFAULT_HOST_COMMERCIAL_OFFERS,isbn));
            assertNotNull(jsonBody);
            System.out.print("getAllOfferByMoreBookCorrect jsonBoby = " + jsonBody);

            JSONObject offerts = new JSONObject(jsonBody);
            assertNotNull(offerts);
            System.out.print("getAllOfferByABookCorrect offerts = " + offerts +"\n");

            assertNotNull(offerts.getJSONArray("offers"));
            System.out.print("getAllOfferByABookCorrect offers = " + offerts.getJSONArray("offers") +"\n");

            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Offer>>() {}.getType();
            List<Offer> offersList = gson.fromJson(offerts.getJSONArray("offers").toString(), collectionType);
            assertNotNull(offersList);
            System.out.print("getAllOfferByABookCorrect offers = " + offersList.toString() +"\n");

        } catch (Exception e) {
            System.err.print("getAllOfferByMoreBookCorrect " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Test
    public void getAllOfferByMoreBookButanyoneNotCorrect() throws Exception {
        ServerInterface serverInterface = new ServerInterface();
        try {
            String isbn = "00000000-be64-4a0c-bf1e-315977acd924,a460afed-e5e7-4e39-a39d-c885c05db861,fcd1e6fa-a63f-4f75-9da4-b560020b6acc";
            String jsonBody = serverInterface.makeRequest(String.format(ServerConfiguration.DEFAULT_HOST_COMMERCIAL_OFFERS,isbn));
            assertNotNull(jsonBody);
            System.out.print("getAllOfferByMoreBookButanyoneNoCorrect jsonBoby = " + jsonBody);
        } catch (Exception e) {
            System.err.print("getAllOfferByMoreBookButanyoneNoCorrect " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}