package demo.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class AdsService {
    public boolean getStatus(String countryCode) throws IOException {
        // Set up
        String urlParameter = "countryCode=" + countryCode;
        byte[] postData = urlParameter.getBytes(StandardCharsets.UTF_8);
        String authHeaderValue = "Basic ZnVuN3VzZXI6ZnVuN3Bhc3M=";
        URL url = new URL("https://us-central1-o7tools.cloudfunctions.net/fun7-ad-partner?" + urlParameter);

        // Open HTTP connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", authHeaderValue);

        int responseCode = connection.getResponseCode();
        InputStream inputStream = getInputStream(connection, responseCode);
        String response = getStringFromResponse(inputStream).toString();

        System.out.println(response);
        return response.equals("{\"ads\": \"sure, why not!\"}");
    }

    private InputStream getInputStream(HttpURLConnection connection, int responseCode) throws IOException {
        InputStream inputStream;
        if (200 <= responseCode && responseCode <= 299) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
            // Return error?
        }
        return inputStream;
    }

    private StringBuilder getStringFromResponse(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String currentLine;

        while ((currentLine = in.readLine()) != null)
            response.append(currentLine);

        in.close();
        return response;
    }

}
