package com.example.loveforhealth;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchData extends AsyncTask<Void, Void, Void> {
    String data = "";
    String dataParsed = "";

    @Override
    protected Void doInBackground(Void... voids) {

            try {
                URL url = new URL("http://api.weatherunlocked.com/api/current/dk.8700?app_id=0c1b9fcb&app_key=0383d8cfadd17b11f486d0175f6075bf");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    data += line;
                }

                JSONObject JO = new JSONObject(data);

                 dataParsed = "Description: " + JO.get("wx_desc") + "\n" +
                         "Temperature: " + JO.get("temp_c") + "°C" + "\n" +
                         "Sensible temperature: " + JO.get("feelslike_c") + "°C\n" +
                         "Wind speed: " + JO.get("windspd_kmh") + "km/h\n" +
                         "Cloud percentage: " + JO.get("cloudtotal_pct") + "%\n";
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        GetData.tv.setText("HORSENS, DENMARK\n" + this.dataParsed);
    }
}
