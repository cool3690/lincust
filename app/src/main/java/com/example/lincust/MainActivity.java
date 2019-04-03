package com.example.lincust;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private static final String DEBUG_TAG = "HttpExample";
    ArrayList<Team> teams = new ArrayList<Team>();
    ListView listview;
    Button btnDownload;
    EditText phone;
    TextView myname,birth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        btnDownload = (Button) findViewById(R.id.btnDownload);
        phone=(EditText)findViewById(R.id.phone);
        myname=(TextView)findViewById(R.id.myname);
        birth=(TextView)findViewById(R.id.birth);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            btnDownload.setEnabled(true);
            Team team = new Team("日期", "消費項目", "金額","消費尺寸","儲值金額");
            teams.add(team);
        } else {
            btnDownload.setEnabled(false);
        }
    }
    public void buttonClickHandler(View view) {
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson2(object);
                processJson(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=1oUp6GUXmBMM770oN1R3hGaemLYjUdOGbsgaVrvBGfKU");
    //https://spreadsheets.google.com/tq?key=1pIj08MUjTNZscHbKkbJJ2eNR1RYlhJLW7qDrcWRnJMM
        //https://spreadsheets.google.com/tq?key=1yyTcjWA6RAUwkI7sKOevWXAJfpITs__Zb0TwilihDCw
    }
    private void processJson2(JSONObject object) {

        try {
            JSONArray rows = object.getJSONArray("rows");
            String myphone=phone.getText().toString();
            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");
                String phone1 = columns.getJSONObject(3).getString("v");
                String name =columns.getJSONObject(1).getString("v");

                String mbirth = columns.getJSONObject(2).getString("v");
                mbirth=mbirth.substring(5,mbirth.indexOf(")"));
                if(phone1.equals(myphone)){
                    myname.setText("姓名:  "+name);
                    birth.setText("生日: " + mbirth);
                    break;
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void processJson(JSONObject object) {

        try {
            JSONArray rows = object.getJSONArray("rows");
            String myphone=phone.getText().toString();
            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");
                String phone1 = columns.getJSONObject(3).getString("v");
                String date =columns.getJSONObject(4).getString("v");
                date=date.substring(5,date.indexOf(")"));
                String item = columns.getJSONObject(5).getString("v");

                String money=  columns.getJSONObject(6).getString("v");
                String size=  columns.getJSONObject(7).getString("v");
                String save=  columns.getJSONObject(8).getString("v");

                if(phone1.equals(myphone)){

                    Team team = new Team(date, item, money,size,save);
                    teams.add(team);
               }

            }

            final TeamsAdapter adapter = new TeamsAdapter(this, R.layout.team, teams);
            listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
