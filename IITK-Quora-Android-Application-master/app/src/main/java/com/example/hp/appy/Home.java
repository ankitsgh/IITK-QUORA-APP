package com.example.hp.appy;

/**
 * Created by HP on 18-06-2015.
 */
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Home extends ActionBarActivity {
    private ListView listView;
    private NewAdapter2 newAdapter;
    DrawerLayout mdrawerlayout;
    private ProgressDialog pDialog;
    String name;
    String aid;
    String cicounter;
    JSONArray products = null;
    String[] Araid;
    String[] Arname;
    String[] Arcom;
    String[] Artime;String counter;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ANS = "answ";
    private static final String TAG_C = "C";
    private static final String TAG_AID = "aid";
    private static final String TAG_NAME = "name";
    private static final String TAG_COMMENT = "comment";
    private static final String TAG_CREATED = "created_at";
    private static final String TAG_CCOM = "CCOM";
    private static final String TAG_PID = "pid";
    private static final String TAG_QUES = "ques";
    private static final String TAG_CA = "CA";

    private static final String url_create_product = "http://172.24.16.61/atletico/create_product.php";
    private static String url_all_products = "http://172.24.16.61/atletico/get_all_product_details.php";

String pid,ques,aicounter;
    JSONParser jsonParser = new JSONParser();
    EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i = getIntent();
        mdrawerlayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        mdrawerlayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorMainDark));

        name = i.getStringExtra(TAG_NAME);
        aid = i.getStringExtra(TAG_AID);
        pid = i.getStringExtra(TAG_PID);
        ques=i.getStringExtra(TAG_QUES);
        aicounter=i.getStringExtra(TAG_CA);
        cicounter = i.getStringExtra(TAG_CCOM);
        counter = i.getStringExtra(TAG_C);
        Log.d("WHAAT THE",cicounter);
        Araid=new String[Integer.parseInt(cicounter)];

        Arcom=new String[Integer.parseInt(cicounter)];
        Arname=new String[Integer.parseInt(cicounter)];
        Artime=new String[Integer.parseInt(cicounter)];
        Log.d("Getgoing", aid);
        new abc().execute();

        Button btn1 = (Button) findViewById(R.id.done);
        comment = (EditText) findViewById(R.id.add);

        ImageButton back = (ImageButton) findViewById(R.id.back);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                new CreateNewProduct().execute();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent i = new Intent(view.getContext(), answer_Activity.class);
                i.putExtra(TAG_NAME, name);
                i.putExtra(TAG_C, (counter));
                i.putExtra(TAG_CA, aicounter);
                i.putExtra(TAG_PID, pid);
                i.putExtra(TAG_QUES, ques);





                startActivity(i);

            }
        });
        listView = (ListView) findViewById(R.id.listView);
        newAdapter = new NewAdapter2(this);
        listView.setAdapter(newAdapter);
    }

    class abc extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Home.this);
            pDialog.setMessage("Loading comments. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            JSONObject jsons = jsonParser.makeHttpRequest(url_all_products, "GET", params);


            Log.d("All Products: ", jsons.toString());

            try {

                int success = jsons.getInt(TAG_SUCCESS);

                if (success == 1) {
                    products = jsons.getJSONArray(TAG_ANS);

                    int k = 0;
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);


                        String id = c.getString(TAG_AID);


                        String com = c.getString(TAG_COMMENT);

                        String names = c.getString(TAG_NAME);
                        String created_at = c.getString(TAG_CREATED);


                        if (id.compareTo(aid) == 0) {
                            Log.d("Al: ", id);

                            Araid[k] = id;
                            Arcom[k] = com;
                            Arname[k] = names;
                            Artime[k]=created_at;

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

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();
        }

    }

    class CreateNewProduct extends AsyncTask<String, String, String> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Home.this);
            pDialog.setMessage("Adding comment..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        protected String doInBackground(String... args) {
            Log.d("Get laid",aid);

            String ans = comment.getText().toString();


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("aid", aid));

            params.add(new BasicNameValuePair("comment", ans));
            params.add(new BasicNameValuePair(TAG_NAME, name));


            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", params);


            Log.d("Create Response", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
int extract=Integer.parseInt(cicounter);
                    extract++;
                    Intent i = new Intent(getApplicationContext(), Home.class);
                    i.putExtra(TAG_AID,aid);
                    i.putExtra(TAG_NAME,name);
                    i.putExtra(TAG_CCOM,Integer.toString(extract));

                    startActivity(i);


                    finish();
                } else {
                    Log.d("failed to create user", json.toString());

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        protected void onPostExecute(String file_url) {

            pDialog.dismiss();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    class NewAdapter2 extends BaseAdapter {
        private Context context;
        String[] Comment;
        String[] Names;

        String Tim[];

        // int[] images={R.drawable.ic_action_search,R.drawable.ic_action_edit,R.drawable.ic_action_person,R.drawable.ic_action_settings};
        public NewAdapter2(Context context) {
            Log.d("Getfucked",aid);
            Comment = Arcom;
            //  answer=context.getResources().getStringArray(R.array.answers);
            Names = Arname;
            Tim=Artime;
            this.context = context;
        }

        @Override
        public int getCount() {
            return Comment.length;
        }

        @Override
        public Object getItem(int i) {
            return Comment[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View row = null;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.comment_list, viewGroup, false);
            } else {
                row = view;
            }
            TextView name_com = (TextView) row.findViewById(R.id.name);
            TextView com = (TextView) row.findViewById(R.id.comment);
            TextView com_time = (TextView) row.findViewById(R.id.time);
            //   ans.setText(answer[i]);
            com.setText(Comment[i]);
            name_com.setText(Names[i]);
            com_time.setText(Tim[i]);


            return row;
        }
    }
}
