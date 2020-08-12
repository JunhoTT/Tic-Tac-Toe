/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_test_17138916;

import java.net.URI;
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
public class PossibleMovesTest {

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
            // Send request for possible moves
            testPossiblemoves(httpclient);
        }
    }

    private static void testPossiblemoves(CloseableHttpClient httpclient) throws Exception {
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8080")
                .setPath("/ttt/possiblemoves")
                .build();
        HttpGet httpGet = new HttpGet(uri);
        try (CloseableHttpResponse response2 = httpclient.execute(httpGet)) {
            String contentType = response2.getHeaders("Content-Type")[0].toString();
            //Make sure that status code received is 200 OK
            assertEquals(HttpStatus.SC_OK, response2.getStatusLine().getStatusCode());
            //Make sure that the content type recieved is equal to txt/plain
            assertEquals("Content-Type: text/plain;charset=UTF-8", contentType);
        } 
    }
}

