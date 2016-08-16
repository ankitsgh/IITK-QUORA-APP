package com.example.hp.appy;

/**
 * Created by HP on 19-06-2015.
 */


import android.view.View;
import android.widget.Button;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.app.ListActivity;

public class mcomActivity1 extends Activity {




    private ProgressDialog pDialog;
    Button btnlike,btndlike;
    int success,str,str1;String likes="",dislike="";



    JSONParser jsonParser = new JSONParser();
    ArrayList<HashMap<String, String>> productsList;
    String aid;

    private static final String url_product_detials = "http://172.24.16.61/chelsea/get_product.php";
    private static final String url_update_detials = "http://172.24.16.61/chelsea/update_product1.php";


    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ANS = "answ";
    private static final String TAG_AID = "aid";
    private static final String TAG_LIKE="likes";
    private static final String TAG_DLIKE="dislike";
    private static final String TAG_NAME = "name";
    private static final String TAG_PID="pid";
    private static final String TAG_QUES="ques";

    JSONArray products = null;String name,pid,ques;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com);
        productsList = new ArrayList<HashMap<String, String>>();

        Intent i = getIntent();

        aid = i.getStringExtra(TAG_AID);

        name = i.getStringExtra(TAG_NAME);


        new GetProductDetails().execute();


    }


    class GetProductDetails extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(mcomActivity1.this);
            pDialog.setMessage("Loading product details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        protected String doInBackground(String... args) {





            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("aid", aid));


            JSONObject json = jsonParser.makeHttpRequest(
                    url_product_detials, "GET", params);


            Log.d("Single Product Details", json.toString());
            try {


                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {

                    JSONArray products = json
                            .getJSONArray(TAG_ANS);


                    JSONObject c = products.getJSONObject(0);

                    str = c.getInt(TAG_DLIKE);


                    str = str + 1;
                    dislike= Integer.toString(str);


                    Log.d("Sjh", likes);




                    List<NameValuePair> param = new ArrayList<NameValuePair>();
                    param.add(new BasicNameValuePair(TAG_AID, aid));
                    param.add(new BasicNameValuePair(TAG_DLIKE, dislike));



                    JSONObject jsons = jsonParser.makeHttpRequest(url_update_detials,
                            "POST", param);
                    try {
                        success = jsons.getInt(TAG_SUCCESS);

                        Log.d("Sh",jsons.toString());

                        if (success == 1) {

                            Intent in = new Intent(getApplicationContext(), MainActivity.class);
                            in.putExtra(TAG_NAME,name);


                            startActivity(in);
                        } else {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }












                }
                else{
                }

            }
            catch (JSONException e) {
                e.printStackTrace();
            }









            return null;
        }




        protected void onPostExecute(String file_url) {

            pDialog.dismiss();

        }}


}
