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
public class MoveBadRequestTest {

    public static void main(String [] args) throws Exception {
        startGame();
    }
    
    private static void startGame() throws Exception {
        // Initialise a session
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URI uri = new URIBuilder()
            .setScheme("http")
            .setHost("localhost:8080")
            .setPath("/ttt/istart")
            .build();
        HttpPost httpPost = new HttpPost(uri);
        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            //Send a move request
            testMove(httpclient, response);
        }
    } 

    private static void testMove(CloseableHttpClient httpclient, CloseableHttpResponse response) throws URISyntaxException, IOException {
        URI uri2 = new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8080")
                .setPath("/ttt/move/x4y4")
                .build();
        HttpPost httpPost2 = new HttpPost(uri2);
        CloseableHttpResponse response2 = httpclient.execute(httpPost2);
        try {
            assertEquals(HttpStatus.SC_BAD_REQUEST, response2.getStatusLine().getStatusCode());
        } finally {
            response.close();
        }
    }
}
