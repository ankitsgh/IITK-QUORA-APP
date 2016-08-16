package com.example.hp.appy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class search2 extends ActionBarActivity {
    private ListView listView;

    DrawerLayout mdrawerlayout;
    EditText search;
    String jh;
    int []karray;


    JSONParser jsonParser = new JSONParser();

    private ProgressDialog pDialog;
    private static String url_all_products = "http://172.24.16.61/barca/get_all_product_details.php";
    private static String url_all_products5 = "http://172.24.16.61/chelsea/get_all_product_details.php";
    private static final String url_update_detials2 = "http://172.24.16.61/barca/update_product.php";
    private static final String url_pro = "http://172.24.16.61/barca/get_product_details.php";
    JSONArray products = null;
    String[] Arid=new String [20];
    String[] Arname=new String [20];
    String[] Arques=new String [20];
    String[] Arviews=new String [20];
    String[] Artime=new String [20];
int k=0;
    String sname;


    private static final String TAG_C = "C";
    private static final String TAG_CCOM = "CCOM";
    private static final String TAG_CA = "CA";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";
    private static final String TAG_VIEWS = "views";
    private static final String TAG_QUES = "ques";
    private static final String TAG_TIME = "created_at";
    private static final String TAG_LIKE = "likes";
    private static final String TAG_DLIKE = "dislike";
    ArrayList<HashMap<String, String>> productsList;

    private static final String TAG_ANSWER = "ans";
    private static final String TAG_ANS = "answ";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ARRI = "ARRI";
    private static final String TAG_ARRQ = "ARRQ";
    private static final String TAG_ARRV = "ARRV";
    private static final String TAG_ARRT = "ARRT";
    private static final String TAG_LIST = "LIST";
    String counter;int icounter;String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search1);
        Intent i = getIntent();
        counter=i.getStringExtra(TAG_C);
        name=i.getStringExtra(TAG_NAME);





        Button btn1 = (Button) findViewById(R.id.done1);
        Button btn2=(Button) findViewById(R.id.done2);

        search = (EditText) findViewById(R.id.add1);
        sname=search.getText().toString();
        btn2.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View view) {
                                        new searching().execute();
                                    }
                                });

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                Intent i = new Intent(view.getContext(), com.example.hp.appy.searching.class);
                i.putExtra(TAG_NAME,name);
                i.putExtra(TAG_C,(counter));
                i.putExtra(TAG_ARRI,(Arid));
                i.putExtra(TAG_ARRQ,(Arques));
                i.putExtra(TAG_ARRT,(Artime));
                i.putExtra(TAG_ARRV,(Arviews));
                i.putExtra("len",Integer.toString(k));


                startActivity(i);

            }
        });

        mdrawerlayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        mdrawerlayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorMainDark));


    }
    class searching extends AsyncTask<String, String, String> {





        protected String doInBackground(String... args) {
            Log.d("Fueesiu2","");
            String namesh = search.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = jsonParser.makeHttpRequest(url_all_products, "GET", params);


            Log.d("All Products: ", json.toString());

            try {

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    products = json.getJSONArray(TAG_PRODUCTS);



                    for (int i = 0; i <products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        String names = c.getString(TAG_NAME);
                        if(names.compareTo(namesh)==0) {

                            String id = c.getString(TAG_PID);
                            Arid[i] = id;

                            Arname[i] = names;
                            String ques = c.getString(TAG_QUES);
                            Arques[i] = ques;
                            Log.d("ARE", Arques[i]);
                            String views = c.getString(TAG_VIEWS);
                            Arviews[i] = views;
                            String created_at = c.getString(TAG_TIME);
                            Artime[i] = created_at;
                            k++;


                        }


                    }
                } else {





                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

  ;





}
