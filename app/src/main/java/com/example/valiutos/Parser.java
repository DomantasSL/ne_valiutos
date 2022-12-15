package com.example.valiutos;

import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;

public class Parser {
    public static String getKaunasWeatherForecast(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line = "";
        String data = "";
        while (line != null)
        {
            line = bufferedReader.readLine();
            data = data + line;
        }

        String result = "";
        try {
            JSONObject jData = new JSONObject(data);

            JSONArray weather = jData.getJSONArray("forecastTimestamps");
            JSONObject weather1 = weather.getJSONObject(0);

            JSONObject placeNode = jData.getJSONObject("place");

            String time = weather1.getString("forecastTimeUtc");
            String temp = weather1.getString("airTemperature");
            String administrativeDivision = placeNode.getString("administrativeDivision");
            result = String.format("Location name: %s \n\nTime: %s \nTemperature: %s", administrativeDivision, time, temp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}