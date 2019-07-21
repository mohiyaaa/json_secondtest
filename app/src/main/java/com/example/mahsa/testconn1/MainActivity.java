package com.example.mahsa.testconn1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pd;
    JSONParser jparser = new JSONParser();
    JSONObject json;
    JSONArray s = null;
    String companyname;
    HttpResponse response;
    String Json;
    private static String url = "http://192.168.233.1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //travel();
        new my().execute();
    }
    class my extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("login babyyyyyyyyyy:)");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpClient myClient = new DefaultHttpClient();
            HttpPost myConnection = new HttpPost("http://192.168.233.1");

            try {
                response = myClient.execute(myConnection);
                Log.e("Buffer Error", "Error response " + response);
                Json = EntityUtils.toString(response.getEntity(), "UTF-8");
                Log.e("Buffer Error", "Error Json " + Json);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                Log.e("Buffer Error", "Error company 1" );
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Buffer Error", "Error company 2" );
            }
            try {
                JSONObject j=new JSONObject(Json);
                int t = j.getInt("t");
                Log.e("Buffer Error", "Error t "+t );
                if (t==1){
                    s=j.getJSONArray("travel");
                    for (int i=0;i<s.length();i++){
                        JSONObject c=s.getJSONObject(i);
                         companyname=c.getString("name");

                    }


                }else {
                    Toast.makeText(MainActivity.this,"لطفا اظلعاددد",Toast.LENGTH_SHORT).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this,companyname,Toast.LENGTH_LONG).show();

                }
            });
        }
    }

}
