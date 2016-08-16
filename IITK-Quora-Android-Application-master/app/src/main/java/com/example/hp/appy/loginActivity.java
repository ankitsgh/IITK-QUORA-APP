

package com.example.hp.appy;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import org.apache.http.NameValuePair;
        import org.apache.http.message.BasicNameValuePair;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import android.app.Activity;
        import android.app.ListActivity;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.graphics.Typeface;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;

public class loginActivity extends Activity {
    Button login;EditText inputName,inputpwd;


    private ProgressDialog pDialog;
    String pid,aid;


    JSONParser jParser = new JSONParser();
    int counter=0;

    ArrayList<HashMap<String, String>> productsList;

    private static final String url_all_products = "http://172.24.16.61/manU/get_all_product_details.php";
    private static final String url_all_products1 = "http://172.24.16.61/barca/get_all_product_details.php";


    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ANS = "log";
    private static final String TAG_C = "C";

    private static final String TAG_NAME = "name";
    private static final String TAG_PWD = "password";




    JSONArray products = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activa);
        new LoadAllProducts().execute();


        productsList = new ArrayList<HashMap<String, String>>();


        inputName = (EditText) findViewById(R.id.name);
        inputpwd = (EditText) findViewById(R.id.pwd);
        login = (Button) findViewById(R.id.login);







            login.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    new abc().execute();
                }
            });
        }
    class LoadAllProducts extends AsyncTask<String, String, String> {





        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = jParser.makeHttpRequest(url_all_products1, "GET", params);


            Log.d("All Products: ", json.toString());

            try {

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    products = json.getJSONArray("products");
                    counter=products.length();





                } else {


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


    }






    class abc extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(loginActivity.this);
            pDialog.setMessage("Logging in. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        protected String doInBackground(String... args) {
            String name = inputName.getText().toString();
            String pwd = inputpwd.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);


            Log.d("All Products: ", json.toString());

            try {

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    products = json.getJSONArray(TAG_ANS);


                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);


                        String name1 = c.getString(TAG_NAME);
                        String pwd1 = c.getString(TAG_PWD);
                        if(name.compareTo(name1) == 0 && pwd.compareTo(pwd1) == 0 )
                        {Intent in = new Intent(getApplicationContext(), MainActivity.class);
                            in.putExtra(TAG_NAME,name);
                            in.putExtra(TAG_C,Integer.toString(counter));

                            startActivity(in);}}}
                        else
                    {}





            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();



        }

    }
}
