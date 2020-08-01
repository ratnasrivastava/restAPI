package com.ratnasrivastava.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startRestAPI();
    }

    private void startRestAPI() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://api.github.com/");
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestProperty("User-Agent","rest-api-ratna");
                    if(httpsURLConnection.getResponseCode() == 200){
                        Log.d("unique7","Successfully getting response");
                        InputStream responseBody = httpsURLConnection.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader);
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()){
                            String key = jsonReader.nextName();
                            String value = jsonReader.nextString();
                            Log.d("unique7","key = "+key+" value= "+value);
//                            if(key.equals("organization_url")){
//                                Log.d("unique7", jsonReader.nextString());
//                            }
//                            else{
//                                jsonReader.skipValue();
//                            }
                        }
                    }else{
                        Log.d("unique7","Error in getting response");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}