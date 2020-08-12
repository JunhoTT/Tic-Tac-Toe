/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_test_17138916;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import static org.junit.Assert.*;
/**
 *
 * @author hasee
 */
public class WonTest {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
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
            // Send won request to game session
            testWin(httpclient);
        }
    }

    private static void testWin(CloseableHttpClient httpclient) throws Exception {
        URI uri2 = new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8080")
                .setPath("/ttt/won")
                .build();
        HttpGet httpGet = new HttpGet(uri2);
        try (CloseableHttpResponse response2 = httpclient.execute(httpGet)) {
            // Take content and convert to string
            HttpEntity entity = response2.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            String replaceAll = responseString.replaceAll("\\s+","");
            List possibleOutputs = Arrays.asList("user", "computer", "draw", "none");
            boolean contains = possibleOutputs.contains(replaceAll);
            //Make sure that body content belongs to "user", "computer", "draw", or "none"
            assertTrue(contains);
            String contentType = response2.getHeaders("Content-Type")[0].toString();
            //Make sure that the correct status code is recieved 200 OK
            assertEquals(HttpStatus.SC_OK, response2.getStatusLine().getStatusCode());
            // Make sure that the correct content type is recieved
            assertEquals("Content-Type: text/plain;charset=UTF-8", contentType);
        } 
    }
}

