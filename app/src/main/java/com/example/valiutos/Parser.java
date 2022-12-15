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
        String result1 = "";
        try {
            JSONObject jData = new JSONObject(data);


                JSONArray weather = jData.getJSONArray("forecastTimestamps");
            JSONObject placeNode = jData.getJSONObject("place");
            String administrativeDivision = placeNode.getString("administrativeDivision");
                result = String.format("location:%s \n",administrativeDivision);
                for (int i = 0; i < weather.length(); i++) {
                    JSONObject weather1 = weather.getJSONObject(i);

                    String time = weather1.getString("forecastTimeUtc");
                    String temp = weather1.getString("airTemperature");

                    result1 = String.format("\nTime: %s \nTemperature: %s \n", time, temp);
                    result = result + result1;
                }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}