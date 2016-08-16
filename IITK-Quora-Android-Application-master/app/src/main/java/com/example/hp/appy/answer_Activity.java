package com.example.hp.appy;

/**
 * Created by HP on 19-06-2015.
 */


        import android.app.ActionBar;
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
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.example.hp.appy.SlidingTabs.SlidingTabLayout;

        import org.apache.http.NameValuePair;
        import org.apache.http.message.BasicNameValuePair;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;


public class answer_Activity extends ActionBarActivity {
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    SlidingTabLayout mSlidingTabLayout;
    ViewPager mViewPager;
    EditText inputDesc;
    String name;
    String pid;
    String ques;
    String dk;
    String dk1;
    String aicounter;int aiicounter;
    private ListView listView;
    JSONParser jsonParser = new JSONParser();
    private ProgressDialog pDialog;
    int r=0;

    JSONArray products = null;
    String[] Arid;
    String[] Arname;
    String[] Araid;
    String[] Arlike;
    String[]Ardlike;
    String[] Aranswer;
    String[] Artime;
    String []arraid;
    int[]karray;

    String[] pk;

    private static final String url_create_product = "http://172.24.16.61/chelsea/create_product.php";
    private static String url_all_products = "http://172.24.16.61/chelsea/get_all_product_details.php";
    private static String url_cre = "http://172.24.16.61/realMadrid/create_product.php";
    private static String url_all_products_like = "http://172.24.16.61/realMadrid/get_all_product_details.php";
    private static String url_cre1 = "http://172.24.16.61/bayern/create_product.php";
    private static String url_all_products_dlike = "http://172.24.16.61/bayern/get_all_product_details.php";
    private static String url_all_products6 = "http://172.24.16.61/atletico/get_all_product_details.php";
    private NewAdapter1 newAdapter;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ANS = "answ";
    private static final String TAG_PID = "pid";
    private static final String TAG_AID = "aid";
    private static final String TAG_NAME = "name";
    private static final String TAG_TIME = "current_time";
    private static final String TAG_DLIKE = "dislike";
    private static final String TAG_CA = "CA";
    private static final String TAG_CCOM = "CCOM";
    private static final String TAG_C = "C";
    private static final String TAG_LIKE = "likes";
    private static final String TAG_ANSWER = "ans";
    private static final String TAG_QUES = "ques";
    private static final String TAG_PRODUCT = "products";
    private static final String url_product = "http://172.24.16.61/chelsea/get_product.php";
    private static final String url_update_detials = "http://172.24.16.61/chelsea/update_product.php";
    private static final String url_update_detials1 = "http://172.24.16.61/chelsea/update_product1.php";
     String counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_);
        Intent i = getIntent();

        name = i.getStringExtra(TAG_NAME);
        pid = i.getStringExtra(TAG_PID);
        ques=i.getStringExtra(TAG_QUES);
        aicounter=i.getStringExtra(TAG_CA);
        counter=i.getStringExtra(TAG_C);
        aiicounter=Integer.parseInt(aicounter);
        Log.d("SEE",aicounter);
        arraid=new String[Integer.parseInt(aicounter)+1];
        Arid=new String[Integer.parseInt(aicounter)];
        Arname=new String[Integer.parseInt(aicounter)];
        Araid=new String[Integer.parseInt(aicounter)];
        Aranswer=new String[Integer.parseInt(aicounter)];
        Arlike=new String[Integer.parseInt(aicounter)];
        Ardlike=new String[Integer.parseInt(aicounter)];
        Artime=new String[Integer.parseInt(aicounter)];
        karray=new int[aiicounter+1];
        karray[0]=0;
        arraid[0]="";

        new sam().execute();


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
    class sam extends AsyncTask<String, String, String> {



        protected String doInBackground(String... args) {
            List<NameValuePair> param = new ArrayList<NameValuePair>();

            JSONObject js = jsonParser.makeHttpRequest(url_all_products, "GET", param);

            try {

                int success = js.getInt(TAG_SUCCESS);

                if (success == 1) {
                    products = js.getJSONArray(TAG_ANS);

                    int k = 1;
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);


                        String aaid = c.getString(TAG_AID);
                        String id = c.getString(TAG_PID);
                        if (id.compareTo(pid) == 0) {
                            Log.d("hhduih","");
                            arraid[k] = aaid;
                            k++;
                        }
                    }
                }}
            catch (JSONException e) {
                e.printStackTrace();
            }




                        List<NameValuePair> params = new ArrayList<NameValuePair>();

            JSONObject jsons = jsonParser.makeHttpRequest(url_all_products6, "GET", params);


            Log.d("All Products: ", jsons.toString());

            try {

                int success = jsons.getInt(TAG_SUCCESS);

                if (success == 1) {
                    products = jsons.getJSONArray(TAG_ANS);

                    for(int j=1;j<=aiicounter;j++) {
                        for (int i = 0; i < products.length(); i++) {
                            JSONObject c = products.getJSONObject(i);


                            String aid = c.getString(TAG_AID);


                            int iid = Integer.parseInt(aid);
                            Log.d("hhduih1",arraid[j]);


                            if (aid.compareTo(arraid[j])==0) {

                                karray[j] = karray[j] + 1;
                            }
                        }Log.d("hhduih2",Integer.toString(karray[1]));




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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();


        if (id == R.id.logout) {
            Intent intent = new Intent(this,loginActivity.class);
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
                string = "Answers";
            if (position == 1)
                string = "Reply";

            return string;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = null;
            if (position == 0) {

                Log.d("hi",Integer.toString(r));
                view = getLayoutInflater().inflate(R.layout.ans_pager,
                        container, false);
                TextView  quest= (TextView)view.findViewById(R.id.qstn_in_ans);


                quest.setText(ques);
                listView = (ListView) view.findViewById(R.id.listView_ans);
                newAdapter = new NewAdapter1(view.getContext());
                listView.setAdapter(newAdapter);


             //   new cd().execute();
                new abc().execute();

                container.addView(view);
                views.put(position, view);
            }
            if (position == 1) {

                view = getLayoutInflater().inflate(R.layout.ans_pager1,
                        container, false);
                inputDesc = (EditText) view.findViewById(R.id.editText_ans);
                Button btn = (Button) view.findViewById(R.id.sub_ans);

                btn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        new CreateNewProduct().execute();
                    }
                });


                container.addView(view);
                views.put(position, view);
            }

            return view;
        }


        class CreateNewProduct extends AsyncTask<String, String, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(answer_Activity.this);
                pDialog.setMessage("Creating Answer..");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }


            protected String doInBackground(String... args) {


                String ans = inputDesc.getText().toString();


                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("inputName", name));

                params.add(new BasicNameValuePair("inputDesc", ans));
                params.add(new BasicNameValuePair(TAG_PID, pid));


                JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                        "POST", params);


                Log.d("Create Response", json.toString());

                try {
                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1) {
                       int aiicounter=Integer.parseInt(aicounter);
                        aiicounter++;
                        Intent i = new Intent(getApplicationContext(), answer_Activity.class);
                        i.putExtra(TAG_NAME,name);
                        i.putExtra(TAG_PID,pid);
                        i.putExtra(TAG_QUES,ques);
                        i.putExtra(TAG_QUES,ques);i.putExtra(TAG_CA,Integer.toString(aiicounter));
                        i.putExtra(TAG_C,(counter));

                        startActivity(i);
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

        class abc extends AsyncTask<String, String, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(answer_Activity.this);
                pDialog.setMessage("Loading products. Please wait...");
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

int k=0;int s=aiicounter-1;
                        for (int i = 0; i < products.length(); i++) {
                            JSONObject c = products.getJSONObject(i);


                            String id = c.getString(TAG_PID);

                            String answer = c.getString(TAG_ANSWER);

                            String aaid = c.getString(TAG_AID);

                            String likes = c.getString(TAG_LIKE);
                            String dislike = c.getString(TAG_DLIKE);
                            String names = c.getString(TAG_NAME);
                            String  times=c.getString(TAG_TIME);


                           if (id.compareTo(pid) == 0) {
                               Log.d("Al: ", id);
                               Araid[s] = aaid;
                               Arlike[s] = likes;
                               Ardlike[s] = dislike;
                               Arid[s] = id;
                               Aranswer[s] = answer;
                               Arname[s] = names;
                               Artime[s] = times;
                               s--;
                           }

                        }Log.d("ONLY",Integer.toString(r));
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

    class NewAdapter1 extends BaseAdapter {



        private Context context;
        String[] Question;
        String[] answer;
        String[] names;
        String[]liked;
        String[] dliked;
        String[]tim;
        Integer pos;


        // int[] images={R.drawable.ic_action_search,R.drawable.ic_action_edit,R.drawable.ic_action_person,R.drawable.ic_action_settings};
        public NewAdapter1(Context context) {

            Log.d("ITS TIME", Integer.toString(r));
            Question = context.getResources().getStringArray(R.array.question);
            answer=Aranswer;
            names=Arname;
            liked=Arlike;
            dliked=Ardlike;
            tim=Artime;
           /* for(int i=0;i<r;i++)
            {
                answer[i]=Aranswer[i];names[i]=Arname[i];dliked[i]=Ardlike[i];liked[i]=Arlike[i];tim[i]=Artime[i];
            }*/




            this.context = context;
        }

        @Override
        public int getCount() {
            return answer.length;
        }

        @Override
        public Object getItem(int i) {
            return answer[i];
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
                row = inflater.inflate(R.layout.ans_list, viewGroup, false);
            } else {
                row = view;
            }
            TextView ans = (TextView) row.findViewById(R.id.tv_ans);
            TextView namess = (TextView) row.findViewById(R.id.tv_name_ans);
            TextView likes = (TextView) row.findViewById(R.id.ans_like);
            TextView dlike = (TextView) row.findViewById(R.id.ans_dlike);
            TextView ti = (TextView) row.findViewById(R.id.tv_post_ans);

            Button btn1 = (Button) row.findViewById(R.id.btn1_ans);
            btn1.setTag(new Integer(i));
            Button btn2 = (Button) row.findViewById(R.id.btn2_ans);

            btn2.setTag(new Integer(i));

            Button btn3 = (Button) row.findViewById(R.id.btn3_ans);
            btn3.setTag(new Integer(i));
            ans.setText(answer[i]);
            namess.setText(names[i]);
            likes.setText(liked[i]);
            dlike.setText(dliked[i]);
            ti.setText(tim[i]);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer pos;
                    pos = (Integer) v.getTag();
                    dk=Araid[pos];
                    new adb().execute();



                }
                class adb extends AsyncTask<String, String, String> {
                    int success,str;String likes;int k=0;


                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        pDialog = new ProgressDialog(answer_Activity.this);
                        pDialog.setMessage("Loading product details. Please wait...");
                        pDialog.setIndeterminate(false);
                        pDialog.setCancelable(true);
                        pDialog.show();
                    }


                    protected String doInBackground(String... args) {
                        List<NameValuePair> p = new ArrayList<NameValuePair>();

                        JSONObject hbk = jsonParser.makeHttpRequest(url_all_products_like, "GET", p);


                        Log.d("All Products: ", hbk.toString());


                        try {
                            int success = hbk.getInt(TAG_SUCCESS);


                            if (success == 1) {
                                products = hbk.getJSONArray(TAG_ANS);


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

                        if (k==0)
                        {


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

                                        Intent in = new Intent(getApplicationContext(), answer_Activity.class);
                                        in.putExtra(TAG_NAME, name);
                                        in.putExtra(TAG_PID, pid);
                                        in.putExtra(TAG_QUES, ques);
                                        in.putExtra(TAG_CA, aicounter);
                                        in.putExtra(TAG_C,(counter));
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
                    }
                        else
                        {}










                        return null;
                    }




                    protected void onPostExecute(String file_url) {

                        pDialog.dismiss();

                    }}


            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer pos;
                    pos = (Integer) v.getTag();


                  /*  Intent intent = new Intent(context, comActivity1.class);
                    intent.putExtra(TAG_AID,Araid[pos]);
                    intent.putExtra(TAG_NAME, name);
                    intent.putExtra(TAG_PID, pid);
                    intent.putExtra(TAG_QUES, ques);



                    context.startActivity(intent);*/
                    dk1=Araid[pos];
                    new cde().execute();
                }
                class cde extends AsyncTask<String, String, String> {
                    int success,str;String dislike;int k=0;


                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        pDialog = new ProgressDialog(answer_Activity.this);
                        pDialog.setMessage("Loading Answers. Please wait...");
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

                                            Intent in = new Intent(getApplicationContext(), answer_Activity.class);
                                            in.putExtra(TAG_NAME, name);
                                            in.putExtra(TAG_PID, pid);
                                            in.putExtra(TAG_QUES, ques);
                                            in.putExtra(TAG_CA, aicounter);
                                            in.putExtra(TAG_C,(counter));
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
                        }
                        else{

                        }









                        return null;
                    }




                    protected void onPostExecute(String file_url) {

                        pDialog.dismiss();

                    }}
            });
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer pos;
                    pos = (Integer) v.getTag();

                    Intent intent = new Intent(context, Home.class);
                    intent.putExtra(TAG_NAME,name);
                    intent.putExtra(TAG_AID,Araid[pos]);
                    intent.putExtra(TAG_C,counter);

                    intent.putExtra(TAG_PID, pid);
                    intent.putExtra(TAG_QUES, ques);
                    intent.putExtra(TAG_CA, aicounter);


                    intent.putExtra(TAG_CCOM, Integer.toString(karray[pos+1]));

                    context.startActivity(intent);
                }
            });
            return row;
        }
    }
}
