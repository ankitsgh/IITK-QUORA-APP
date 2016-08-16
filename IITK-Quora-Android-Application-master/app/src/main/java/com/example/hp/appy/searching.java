package com.example.hp.appy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import com.example.hp.appy.SlidingTabs.SlidingTabLayout;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class searching extends ActionBarActivity  {

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    SlidingTabLayout mSlidingTabLayout;
    ViewPager mViewPager;
    private ListView listView;
    private NewAdapter4 newAdapter;
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
    String[] Arid;
    String[] Arname;
    String[] Arques;
    String[] Arviews;
    String[] Artime;
    String[] arrid;



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

    private static final String TAG_ANSWER = "ans";
    private static final String TAG_ANS = "answ";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ARRI = "ARRI";
    private static final String TAG_ARRQ = "ARRQ";
    private static final String TAG_ARRV = "ARRV";
    private static final String TAG_ARRT = "ARRT";
    String counter;int icounter;String name;int len;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        Intent i = getIntent();
        counter=i.getStringExtra(TAG_C);
        name=i.getStringExtra(TAG_NAME);
        len=Integer.parseInt(i.getStringExtra("len"));
        Arid=new String[len];
        Arques=new String[len];
        Arviews=new String[len];
        Artime=new String[len];
        karray=new int[len];


        for(int y=0;y<len;y++)
        {

            Arid[y] =( i.getStringArrayExtra(TAG_ARRI))[y];

            Arques[y]=(i.getStringArrayExtra(TAG_ARRQ))[y];

            Arviews[y]=(i.getStringArrayExtra(TAG_ARRV))[y];
            Artime[y]=(i.getStringArrayExtra(TAG_ARRT))[y];}

       ImageButton btn1 = (ImageButton) findViewById(R.id.back);
        new sam().execute();

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent i = new Intent(view.getContext(), MainActivity.class);
                i.putExtra(TAG_NAME, name);
                i.putExtra(TAG_C, (counter));




                startActivity(i);

            }
        });



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorMainDark));
        listView= (ListView)findViewById(R.id.listView);
        newAdapter= new NewAdapter4(this);
        listView.setAdapter(newAdapter);



    }
    class sam extends AsyncTask<String, String, String> {


        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            JSONObject jsons = jsonParser.makeHttpRequest(url_all_products5, "GET", params);


            Log.d("All Products: ", jsons.toString());

            try {

                int success = jsons.getInt(TAG_SUCCESS);

                if (success == 1) {
                    products = jsons.getJSONArray(TAG_ANS);

                    for (int j = 0; j <len; j++) {
                        for (int i = 0; i < products.length(); i++) {
                            JSONObject c = products.getJSONObject(i);


                            String id = c.getString(TAG_PID);


                            int iid = Integer.parseInt(id);

                            if (id.compareTo(Arid[j])==0) {

                                karray[j] = karray[j] + 1;
                            }

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }


class NewAdapter4 extends BaseAdapter {
    private Context context;
    String[] Question;
    String[] Views ;
    String[] Tim ;
    // int[] images={R.drawable.ic_action_search,R.drawable.ic_action_edit,R.drawable.ic_action_person,R.drawable.ic_action_settings};
    public NewAdapter4(Context context){
        Question=Arques;
        Views=Arviews;
        Tim=Artime;
        this.context =context;
    }
    @Override
    public int getCount() {
        return Question.length;
    }

    @Override
    public Object getItem(int i) {
        return Question[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row=null;
        if(view==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row= inflater.inflate(R.layout.search_list,viewGroup,false);
        }
        else{
            row=view;
        }
        TextView ques= (TextView) row.findViewById(R.id.ques);
        ques.setTag(new Integer(i));
        TextView viewed=(TextView)row.findViewById(R.id.viewed);
        TextView timed=(TextView)row.findViewById(R.id.timed);

        ques.setText(Question[i]);

        viewed.setText(Views[i]);
        timed.setText(Tim[i]);

        ques.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("RETYTSFH","");

               Integer pos;
                pos = (Integer) v.getTag();

                jh = Arid[pos];


                new jlm().execute();


                Intent intent = new Intent(context, answer_Activity.class);


                intent.putExtra(TAG_PID, Arid[pos]);
                intent.putExtra(TAG_QUES, Arques[pos]);
                intent.putExtra(TAG_NAME, name);

                intent.putExtra(TAG_C, (counter));
                intent.putExtra(TAG_CA, Integer.toString(karray[pos]));
                context.startActivity(intent);
            }


            class jlm extends AsyncTask<String, String, String> {


             /*   @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(MainActivity.this);
                    pDialog.setMessage("Loading product details. Please wait...");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                }*/


                protected String doInBackground(String... args) {


                    int success, str;
                    String views = "";


                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("pid", jh));


                    JSONObject json = jsonParser.makeHttpRequest(
                            url_pro, "GET", params);


                    Log.d("Single Product Details", json.toString());
                    try {


                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {

                            JSONArray products = json
                                    .getJSONArray("product");


                            JSONObject c = products.getJSONObject(0);

                            str = c.getInt(TAG_VIEWS);


                            str = str + 1;
                            views = Integer.toString(str);


                            List<NameValuePair> param = new ArrayList<NameValuePair>();
                            param.add(new BasicNameValuePair(TAG_PID, jh));
                            param.add(new BasicNameValuePair(TAG_VIEWS, views));


                            JSONObject jsons = jsonParser.makeHttpRequest(url_update_detials2,
                                    "POST", param);
                            try {
                                success = jsons.getInt(TAG_SUCCESS);

                                Log.d("Sh", jsons.toString());

                                if (success == 1) {


                                } else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        } else {
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    return null;
                }




              /*  protected void onPostExecute(String file_url) {
                    // dismiss the dialog after getting all products
                    pDialog.dismiss();

                }*/
            }
        });


        return row;
    }
}
}
