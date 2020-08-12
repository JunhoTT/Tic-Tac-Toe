/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_test_17138916;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import static org.junit.Assert.*;
/**
 *
 * @author hasee
 */
public class StateTest {

    public static void main(String [] args) throws Exception {
        startGame();
    }

    private static void startGame() throws Exception {
        // Initialise game session
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URI uri = new URIBuilder()
            .setScheme("http")
            .setHost("localhost:8080")
            .setPath("/ttt/istart")
            .build();
        HttpPost httpPost = new HttpPost(uri);
        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            // Send request for both format=txt and format=png
            testStateTxt(httpclient);
            testStatePng(httpclient);
        }
    }

    private static void testStatePng(CloseableHttpClient httpclient) throws URISyntaxException, IOException {
        URI uri3 = new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8080")
                .setPath("/ttt/state")
                .setParameter("format", "png")
                .build();
        HttpGet httpGet3 = new HttpGet(uri3);
        try (CloseableHttpResponse response3 = httpclient.execute(httpGet3)) {
            String contentType = response3.getHeaders("Content-Type")[0].toString();
            //Make sure status code recieved is 200 OK
            assertEquals(HttpStatus.SC_OK, response3.getStatusLine().getStatusCode());
            //Make sure that the corrrect content type is recieved
            assertEquals("Content-Type: text/html;charset=UTF-8", contentType);
        }
    }

    private static void testStateTxt(CloseableHttpClient httpclient) throws URISyntaxException, IOException {
        URI uri2 = new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8080")
                .setPath("/ttt/state")
                .setParameter("format", "txt")
                .build();
        HttpGet httpGet = new HttpGet(uri2);
        try (CloseableHttpResponse response2 = httpclient.execute(httpGet)) {
            String contentType = response2.getHeaders("Content-Type")[0].toString();
            // Make sure status code recieved is 200 OK
            assertEquals(HttpStatus.SC_OK, response2.getStatusLine().getStatusCode());
            //Make sure that the corrrect content type is recieved
            assertEquals("Content-Type: text/plain;charset=UTF-8", contentType);
        }
    }
}

