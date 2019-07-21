package com.example.mahsa.testconn1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pd;
    JSONParser jparser = new JSONParser();
    JSONArray s = null;
    String companyname;
    private static String url = "http://my-json-server.typicode.com/mohiyaaa/test/connection.php";
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
            List<NameValuePair> prm = new ArrayList<NameValuePair>(); // Building Parameters
            // getting JSON string from URL
            JSONObject json = jparser.makeHttpRequest(url, "GET", prm);


            try {
                int t = json.getInt("t");

                if (t==1){
                    s=json.getJSONArray("travel");
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
