package com.example.valiutos;

import static com.example.valiutos.Constants.METEOLT_API_URL;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class DataLoader {
    public static String getValuesFromApi(String apiCode) throws IOException {
        InputStream apiContentStream = null;
        String result = "";
        try{
            apiContentStream = downloadUrlContent(METEOLT_API_URL);
            result = Parser.getKaunasWeatherForecast(apiContentStream);
        }
        finally {
            if (apiContentStream != null) {
                apiContentStream.close(); }
        }
        return result;
    }
    private static InputStream downloadUrlContent (String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
}