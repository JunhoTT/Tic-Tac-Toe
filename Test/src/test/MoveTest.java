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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import static org.junit.Assert.*;
/**
 *
 * @author hasee
 */
public class MoveTest {

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
            // Send a move request
            testMove(httpclient, response);
        }
    } 

    private static void testMove(CloseableHttpClient httpclient, CloseableHttpResponse response) throws URISyntaxException, IOException {
        URI uri2 = new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8080")
                .setPath("/ttt/move/x1y1")
                .build();
        HttpPost httpPost2 = new HttpPost(uri2);
        try (CloseableHttpResponse response2 = httpclient.execute(httpPost2)) {
            String contentLength = response2.getHeaders("Content-Length")[0].toString();
            //Make sure that status code recieved is 200 OK
            assertEquals(HttpStatus.SC_OK, response2.getStatusLine().getStatusCode());
            //Make sure that the request is not recieving any content from the server
            assertEquals("Content-Length: 0", contentLength);
        }
    }
}
