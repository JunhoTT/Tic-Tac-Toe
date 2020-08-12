/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_test_17138916;

import java.net.URI;
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
public class uStartTest {
    public static void main(String [] args) throws Exception {
        startGame();
    }
    /**
     * @param args the command line arguments
     */
    private static void startGame() throws Exception {
        // Initialise game session
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URI uri = new URIBuilder()
            .setScheme("http")
            .setHost("localhost:8080")
            .setPath("/ttt/ustart")
            .build();
        HttpPost httpPost = new HttpPost(uri);
        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            String contentLength = response.getHeaders("Content-Length")[0].toString();
            // Make sure that the status code recieved is 200 OK
            assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
            // Make sure that no content is recieved back 
            assertEquals("Content-Length: 0", contentLength);
        }
    }
}

