package com.trantuandung.technictest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trantuandung.technictest.controller.server.configure.ServerConfiguration;
import com.trantuandung.technictest.controller.server.connected.ServerInterface;
import com.trantuandung.technictest.database.model.Book;

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
}