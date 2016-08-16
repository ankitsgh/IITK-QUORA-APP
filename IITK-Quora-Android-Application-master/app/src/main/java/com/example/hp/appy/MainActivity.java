package com.example.hp.appy;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.app.ProgressDialog;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.hp.appy.SlidingTabs.SlidingTabLayout;



public class MainActivity extends ActionBarActivity {

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    SlidingTabLayout mSlidingTabLayout;
    ViewPager mViewPager;
    EditText inputDesc;
    String name;
    String dk;
    String dk1;
    String jh;
    private ListView listView;
    private NewAdapter newAdapter;
    int aicounter;

    JSONArray products = null;
     String[] Arid;
    String[] Arname;
    String[] Arques;
    String[] Arans;String[] Arviews;
    String[] Arlike;
    String[] Ardlike;
    String[] Araid;String[] Artime;String[] arraid;
    int[]karray;
    int[]karray1;





    JSONParser jsonParser = new JSONParser();

    private ProgressDialog pDialog;

    private static final String url_create_product = "http://172.24.16.61/barca/create_product.php";
    private static String url_all_products = "http://172.24.16.61/barca/get_all_product_details.php";
    private static String url_product_detials = "http://172.24.16.61/chelsea/get_product_details.php";
    private static String url_cre = "http://172.24.16.61/realMadrid/create_product.php";
    private static String url_all_products_like = "http://172.24.16.61/realMadrid/get_all_product_details.php";
    private static String url_cre1 = "http://172.24.16.61/bayern/create_product.php";
    private static String url_all_products_dlike = "http://172.24.16.61/bayern/get_all_product_details.php";
    private static String url_all_products6 = "http://172.24.16.61/atletico/get_all_product_details.php";


    private static final String url_product = "http://172.24.16.61/chelsea/get_product.php";
    private static final String url_update_detials = "http://172.24.16.61/chelsea/update_product.php";
    private static final String url_update_detials1 = "http://172.24.16.61/chelsea/update_product1.php";
    private static final String url_update_detials2 = "http://172.24.16.61/barca/update_product.php";
    private static final String url_pro = "http://172.24.16.61/barca/get_product_details.php";
    private static String url_all_products5 = "http://172.24.16.61/chelsea/get_all_product_details.php";

    public static int i;
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
    private static final String TAG_AID = "aid";
    String counter;int icounter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();

        name = i.getStringExtra(TAG_NAME);
        Log.d("namesd", name);
        counter=i.getStringExtra(TAG_C);
        icounter= Integer.parseInt(counter);
        Arname=new String[icounter];
        Arques=new String[icounter];
        Araid=new String[icounter];
        Arid=new String[icounter];
        Artime=new String[icounter];
        Arlike=new String[icounter];
        Ardlike=new String[icounter];
        Arans=new String[icounter];
        Arviews=new String[icounter];
        karray=new int[icounter+1];
        karray1=new int[icounter+1];
        arraid=new String[icounter+1];
        karray[0]=0;
        karray1[0]=0;
        arraid[0]="";

        new sam().execute();
        new sam1().execute();




        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorMainDark));
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(7);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);


        mSlidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);

        Resources res = getResources();
        mSlidingTabLayout.setSelectedIndicatorColors(res.getColor(R.color.tab_indicator_color));
        mSlidingTabLayout.setDistributeEvenly(true);
        mViewPager.setAdapter(new MainTabs());

        mSlidingTabLayout.setViewPager(mViewPager);

        // Tab events
        if (mSlidingTabLayout != null) {
            mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset,
                                           int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }


        LinearLayout navButton = (LinearLayout) findViewById(R.id.txtNavButton);
        navButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), MainActivity.class);
                i.putExtra(TAG_NAME,name);
                i.putExtra(TAG_C,(counter));
                startActivity(i);
            }
        });
        LinearLayout navButton1 = (LinearLayout) findViewById(R.id.txtNavButton1);
        navButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(),search2.class);
                intent.putExtra(TAG_C, (counter));
                intent.putExtra(TAG_NAME,(name));

                startActivity(intent);
            }
        });

        LinearLayout navButton3 = (LinearLayout) findViewById(R.id.txtNavButton3);
        navButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), loginActivity.class);

                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                startActivity(i);
            }
        });
    }
    class sam1 extends AsyncTask<String, String, String> {



        protected String doInBackground(String... args) {
            List<NameValuePair> param = new ArrayList<NameValuePair>();

            JSONObject json = jsonParser.makeHttpRequest(url_all_products, "GET", param);


            Log.d("All Products: ", json.toString());

            try {

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    products = json.getJSONArray(TAG_PRODUCTS);



                    for (int i = 0; i <products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);


                        String id = c.getString(TAG_PID);


                        String aaid="";


                        try {

                            List<NameValuePair> para = new ArrayList<NameValuePair>();
                            para.add(new BasicNameValuePair("pid", id));


                            JSONObject jsons = jsonParser.makeHttpRequest(
                                    url_product_detials, "GET", para);


                            Log.d("Single Product Details", jsons.toString());


                            success = jsons.getInt(TAG_SUCCESS);
                            if (success == 1) {

                                JSONArray productObj = jsons
                                        .getJSONArray(TAG_ANS);


                                JSONObject product = productObj.getJSONObject(0);

                                aaid=product.getString(TAG_AID);
                            }else{

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        arraid[i+1]=aaid;

                    }
                } else {


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }




            List<NameValuePair> params = new ArrayList<NameValuePair>();

            JSONObject jsons = jsonParser.makeHttpRequest(url_all_products6, "GET", params);


            Log.d("All Products: ", jsons.toString());

            try {

                int success = jsons.getInt(TAG_SUCCESS);

                if (success == 1) {
                    products = jsons.getJSONArray(TAG_ANS);
                   int d=icounter;
                    for(int j=1;j<=icounter;j++) {
                        for (int i = 0; i < products.length(); i++) {
                            JSONObject c = products.getJSONObject(i);


                            String aid = c.getString(TAG_AID);


                            int iid = Integer.parseInt(aid);
                            Log.d("hhduih1",arraid[j]);


                            if (aid.compareTo(arraid[j])==0) {

                                karray1[d] = karray1[d] + 1;
                            }
                        }


                   d--; }
                } else {





                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }



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

               for(int j=1;j<=icounter;j++) {
                   for (int i = 0; i < products.length(); i++) {
                       JSONObject c = products.getJSONObject(i);


                       String id = c.getString(TAG_PID);


                       int iid = Integer.parseInt(id);

                       if (iid == j) {

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
  /*  class sam1 extends AsyncTask<String, String, String> {



        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            JSONObject jsons = jsonParser.makeHttpRequest(url_all_products6, "GET", params);


            Log.d("All Products: ", jsons.toString());

            try {

                int success = jsons.getInt(TAG_SUCCESS);

                if (success == 1) {
                    products = jsons.getJSONArray(TAG_ANS);

                    for(int j=1;j<=icounter;j++) {
                        for (int i = 0; i < products.length(); i++) {
                            JSONObject c = products.getJSONObject(i);


                            String aid = c.getString(TAG_AID);


                            int iid = Integer.parseInt(aid);

                            if (iid == j) {

                                karray1[j] = karray1[j] + 1;
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



    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            Intent intent = new Intent(this,loginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
            return true;
        }
        if (id == R.id.search) {
            Intent intent = new Intent(this,search2.class);
            intent.putExtra(TAG_C, (counter));
            intent.putExtra(TAG_NAME,(name));

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START | Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    class MainTabs extends PagerAdapter {

        SparseArray<View> views = new SparseArray<View>();


        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            //return "Page " + (position + 1);
            String string = null;
            if (position == 0)
                string = "Home";
            if (position == 1)
                string = "Write";

            return string;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = null;
            if (position == 0) {

                view = getLayoutInflater().inflate(R.layout.pager_item,
                        container, false);
                listView = (ListView) view.findViewById(R.id.listView);
                newAdapter = new NewAdapter(view.getContext());
                listView.setAdapter(newAdapter);

                container.addView(view);
                views.put(position, view);
                new LoadAllProducts().execute();
            }
            if (position == 1) {

                view = getLayoutInflater().inflate(R.layout.pager_item1,
                        container, false);
                inputDesc = (EditText) view.findViewById(R.id.editText);
                Button btn = (Button) view.findViewById(R.id.sub_qstn);
                btn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        new CreateNewProduct().execute();

                    }
                });
                ;
                container.addView(view);
                views.put(position, view);
            }

            return view;
        }

        class CreateNewProduct extends AsyncTask<String, String, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setMessage("Creating New Question..");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }


            protected String doInBackground(String... args) {


                String ques = inputDesc.getText().toString();


                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair(TAG_NAME, name));

                params.add(new BasicNameValuePair("inputDesc", ques));


                JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                        "POST", params);


                Log.d("Create Response", json.toString());

                try {
                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1) {
                        icounter=icounter+1;

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra(TAG_NAME,name);
                        i.putExtra(TAG_C,Integer.toString(icounter));

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

        class LoadAllProducts extends AsyncTask<String, String, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setMessage("Loading Questions. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
            }


            protected String doInBackground(String... args) {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                JSONObject json = jsonParser.makeHttpRequest(url_all_products, "GET", params);


                Log.d("All Products: ", json.toString());

                try {

                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1) {

                        products = json.getJSONArray(TAG_PRODUCTS);


                     int w=products.length()-1;
                        for (int i = 0; i <products.length(); i++) {
                            JSONObject c = products.getJSONObject(i);


                            String id = c.getString(TAG_PID);
                             Arid[w]=id;
                            String names = c.getString(TAG_NAME);
                             Arname[w]=names;
                            String ques = c.getString(TAG_QUES);
                            Arques[w] = ques;
                            Log.d("ARE",Arques[w]);
                            String views = c.getString(TAG_VIEWS);
                               Arviews[w]=views;
                            String created_at = c.getString(TAG_TIME);
                             Artime[w]=created_at;

                            String Answer="";String aaid="";String likes="";
                            String dlike="";


                            try {
                                // Building Parameters
                                List<NameValuePair> param = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("pid", id));

                                // getting product details by making HTTP request
                                // Note that product details url will use GET request
                                JSONObject jsons = jsonParser.makeHttpRequest(
                                        url_product_detials, "GET", params);

                                // check your log for json response
                                Log.d("Single Product Details", jsons.toString());

                                // json success tag
                                success = jsons.getInt(TAG_SUCCESS);
                                if (success == 1) {
                                    // successfully received product details
                                    JSONArray productObj = jsons
                                            .getJSONArray(TAG_ANS); // JSON Array

                                    // get first product object from JSON Array
                                    JSONObject product = productObj.getJSONObject(0);
                                    Answer= product.getString(TAG_ANSWER);
                                    likes=product.getString(TAG_LIKE);
                                            dlike=product.getString(TAG_DLIKE);
                                    aaid=product.getString(TAG_AID);
                                }else{
                                    Answer="";
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Arans[w]=Answer;Araid[w]=aaid;Arlike[w]=likes;Ardlike[w]=dlike;
                            w--;

                        }
                    } else {

                        Intent i = new Intent(getApplicationContext(),
                                MainActivity.class);


                        startActivity(i);
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







            /**
             * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
             * {@link View}.
             */
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
                views.remove(position);
            }

            @Override
            public void notifyDataSetChanged() {
                int position = 0;
                for (int i = 0; i < views.size(); i++) {
                    position = views.keyAt(i);
                    View view = views.get(position);

                }
                super.notifyDataSetChanged();
            }

        }




 class NewAdapter extends BaseAdapter  {

    private Context context;
    String[] Question;
    String[] answer;
     String[] Views;
     String[] Names;
     String[] Tim;
     String[] liked;
     String[] dliked;


    // int[] images={R.drawable.ic_action_search,R.drawable.ic_action_edit,R.drawable.ic_action_person,R.drawable.ic_action_settings};
    public NewAdapter(Context context) {
        answer=Arans;
        Question=Arques;
        Views=Arviews;
        Names=Arname;
        liked=Arlike;
        dliked=Ardlike;
        Tim=Artime;
       this.context = context;
        // answer = context.getResources().getStringArray(R.array.answers);

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
        View row = null;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_qstn, viewGroup, false);
        } else {
            row = view;
        }
        TextView ques = (TextView) row.findViewById(R.id.tv_qstn);
        ques.setTag(new Integer(i));
        TextView ans = (TextView) row.findViewById(R.id.tv_ans);
        TextView views = (TextView) row.findViewById(R.id.tv_views);
        final TextView names = (TextView) row.findViewById(R.id.tv_name);
        TextView ti = (TextView) row.findViewById(R.id.tv_post);
        TextView l = (TextView) row.findViewById(R.id.queslike);
        TextView d = (TextView) row.findViewById(R.id.quesdlike);
        Button btn1 = (Button) row.findViewById(R.id.btn1);
        btn1.setTag(new Integer(i));

        Button btn2 = (Button) row.findViewById(R.id.btn2);
        btn2.setTag(new Integer(i));
        Button btn3 = (Button) row.findViewById(R.id.btn3);
        btn3.setTag(new Integer(i));
        ans.setText(answer[i]);
        ques.setText(Question[i]);
        views.setText(Views[i]);
        names.setText(Names[i]);
        ti.setText(Tim[i]);
        l.setText(liked[i]);
        d.setText(dliked[i]);

        ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos;
                pos = (Integer) v.getTag();
                jh = Arid[pos];
                Log.d("RETYTSFH",jh);
                Log.d("eettffu", Integer.toString(icounter));

                new jlm().execute();


                Intent intent = new Intent(context, answer_Activity.class);
                intent.putExtra(TAG_PID, Arid[pos]);
                intent.putExtra(TAG_QUES, Arques[pos]);
                intent.putExtra(TAG_NAME, name);

                intent.putExtra(TAG_C, (counter));
                intent.putExtra(TAG_CA, Integer.toString(karray[Integer.parseInt(jh)]));
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
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos;
                pos = (Integer) v.getTag();
                dk = Araid[pos];
                new adb().execute();
               /* Intent intent = new Intent(context,mcomActivity.class);
                intent.putExtra(TAG_AID,Araid[pos]);
                intent.putExtra(TAG_NAME,name);


                context.startActivity(intent);*/
            }

            class adb extends AsyncTask<String, String, String> {
                int success, str;int k=0;
                String likes;


              /*  @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(MainActivity.this);
                    pDialog.setMessage("Loading product details. Please wait...");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                }*/


                protected String doInBackground(String... args) {
                    List<NameValuePair> p = new ArrayList<NameValuePair>();

                    JSONObject hbk = jsonParser.makeHttpRequest(url_all_products_like, "GET", p);


                    Log.d("All Products: ", hbk.toString());


                    try {
                        int success = hbk.getInt(TAG_SUCCESS);


                        if (success == 1) {
                            products = hbk.getJSONArray(TAG_ANS);
                            Log.d("Namesd2",name);

                            for (int i = 0; i < products.length(); i++) {
                                JSONObject c = products.getJSONObject(i);
                                String aid9 = c.getString(TAG_AID);

                                String name9 = c.getString(TAG_NAME);

                                if (aid9.compareTo(dk) == 0 && name9.compareTo(name) == 0) {
                                    k = 1;
                                }

                            }
                        } else {
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (k == 0) {

                        List<NameValuePair> para = new ArrayList<NameValuePair>();
                        para.add(new BasicNameValuePair("aid", dk));
                        para.add(new BasicNameValuePair("name", name));


                        JSONObject jsony = jsonParser.makeHttpRequest(
                                url_cre, "POST", para);


                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("aid", dk));


                        JSONObject json = jsonParser.makeHttpRequest(
                                url_product, "GET", params);


                        Log.d("Single Product Details", json.toString());
                        try {


                            success = json.getInt(TAG_SUCCESS);
                            if (success == 1) {

                                JSONArray products = json
                                        .getJSONArray(TAG_ANS);


                                JSONObject c = products.getJSONObject(0);

                                str = c.getInt(TAG_LIKE);


                                str = str + 1;
                                likes = Integer.toString(str);


                                Log.d("Sjh", likes);


                                List<NameValuePair> param = new ArrayList<NameValuePair>();
                                param.add(new BasicNameValuePair(TAG_AID, dk));
                                param.add(new BasicNameValuePair(TAG_LIKE, likes));


                                JSONObject jsons = jsonParser.makeHttpRequest(url_update_detials,
                                        "POST", param);
                                try {
                                    success = jsons.getInt(TAG_SUCCESS);

                                    Log.d("Sh", jsons.toString());

                                    if (success == 1) {

                                        Intent in = new Intent(getApplicationContext(), MainActivity.class);
                                        in.putExtra(TAG_NAME, name);
                                        in.putExtra(TAG_C,counter);
                                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                                        startActivity(in);
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
                    } else {
                    }


                    return null;
                }




              /*  protected void onPostExecute(String file_url) {

                    pDialog.dismiss();

                }*/
            }

        });



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos;
                pos = (Integer) v.getTag();
             /*   Intent intent = new Intent(context, mcomActivity1.class);
                intent.putExtra(TAG_AID,Araid[pos]);
                intent.putExtra(TAG_NAME,name);
                context.startActivity(intent);*/
                dk1 = Araid[pos];
                new def().execute();
            }

            class def extends AsyncTask<String, String, String> {
                int success, str;
                String dislike;int k=0;


                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(MainActivity.this);
                    pDialog.setMessage("Please wait...");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                }


                protected String doInBackground(String... args) {



                        List<NameValuePair> p = new ArrayList<NameValuePair>();

                        JSONObject hbk = jsonParser.makeHttpRequest(url_all_products_dlike, "GET", p);


                        Log.d("All Products: ", hbk.toString());


                        try {
                            int success = hbk.getInt(TAG_SUCCESS);


                            if (success == 1) {
                                products = hbk.getJSONArray(TAG_ANS);


                                for (int i = 0; i < products.length(); i++) {
                                    JSONObject c = products.getJSONObject(i);
                                    String aid9 = c.getString(TAG_AID);

                                    String name9 = c.getString(TAG_NAME);

                                    if (aid9.compareTo(dk1) == 0 && name9.compareTo(name) == 0) {
                                        k = 1;
                                    }

                                }
                            } else {
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (k == 0) {

                            List<NameValuePair> para = new ArrayList<NameValuePair>();
                            para.add(new BasicNameValuePair("aid", dk1));
                            para.add(new BasicNameValuePair("name", name));


                            JSONObject jsony = jsonParser.makeHttpRequest(
                                    url_cre1, "POST", para);


                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("aid", dk1));


                    JSONObject json = jsonParser.makeHttpRequest(
                            url_product, "GET", params);


                    Log.d("Single Product Details", json.toString());
                    try {


                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {

                            JSONArray products = json
                                    .getJSONArray(TAG_ANS);


                            JSONObject c = products.getJSONObject(0);

                            str = c.getInt(TAG_DLIKE);


                            str = str + 1;
                            dislike = Integer.toString(str);


                            List<NameValuePair> param = new ArrayList<NameValuePair>();
                            param.add(new BasicNameValuePair(TAG_AID, dk1));
                            param.add(new BasicNameValuePair(TAG_DLIKE, dislike));


                            JSONObject jsons = jsonParser.makeHttpRequest(url_update_detials1,
                                    "POST", param);
                            try {
                                success = jsons.getInt(TAG_SUCCESS);

                                Log.d("Sh", jsons.toString());

                                if (success == 1) {

                                    Intent in = new Intent(getApplicationContext(), MainActivity.class);
                                    in.putExtra(TAG_NAME, name);
                                    in.putExtra(TAG_C,counter);
                                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                                    startActivity(in);
                                } else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        } else {
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }}
                    else{};


                    return null;
                }


                protected void onPostExecute(String file_url) {

                    pDialog.dismiss();

                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos;
                pos = (Integer) v.getTag();
                Log.d("Hey",Araid[pos]);
               Log.d("Hey1", Integer.toString(karray1[pos + 1]));
                jh=Arid[pos];

                Intent intent = new Intent(context, Home.class);
                intent.putExtra(TAG_NAME,name);
                intent.putExtra(TAG_AID, Araid[pos]);
                intent.putExtra(TAG_C, counter);
                intent.putExtra(TAG_PID, Arid[pos]);
                intent.putExtra(TAG_QUES, Arques[pos]);
                intent.putExtra(TAG_CCOM, Integer.toString(karray1[pos+1]));
                intent.putExtra(TAG_CA, Integer.toString(karray[Integer.parseInt(jh)]));

                context.startActivity(intent);
            }
        });
        return row;
    }
}
}
