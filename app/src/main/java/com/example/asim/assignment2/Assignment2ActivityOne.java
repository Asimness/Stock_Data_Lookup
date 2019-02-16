package com.example.asim.assignment2;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static java.net.Proxy.Type.HTTP;

public class Assignment2ActivityOne extends AppCompatActivity implements StockDataDisplayFragment.OnFragmentInteractionListener, StockPromptFragment.OnFragmentInteractionListener {

    StringBuilder URLBuilder = new StringBuilder("http://utdallas.edu/~John.Cole/2017Spring/");
    final String TAG = "ASSIGNMENT2";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button DisplayButton;
    private Button BackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment2_one);
        DisplayButton = findViewById(R.id.displayButton);
        BackButton = findViewById(R.id.backButton);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        StockPromptFragment fragment = new StockPromptFragment();
        fragmentTransaction.replace(R.id.activity_assignment_2_constraint_layout, fragment);
        fragmentTransaction.commit();
    }

    public void onClickDisplayData(View view) {

        EditText editText = findViewById(R.id.inputSymbolText);
        String symbol = editText.getText().toString();
        URLBuilder.append(symbol.toUpperCase());
        URLBuilder.append(".txt");
        String url = URLBuilder.toString();
        new GetStockDataTask().execute(url);
        URLBuilder = new StringBuilder("http://utdallas.edu/~John.Cole/2017Spring/");
    }

    public void onClickToPromptFragment(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        StockPromptFragment fragment = new StockPromptFragment();
        fragmentTransaction.replace(R.id.activity_assignment_2_constraint_layout, fragment);
        fragmentTransaction.commit();
        DisplayButton.setVisibility(View.VISIBLE);
        BackButton.setVisibility(View.INVISIBLE);
    }


    class GetStockDataTask extends AsyncTask<String, Integer, ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(String... urls) {
            ArrayList<String> data = new ArrayList();
            try {
                URL url = new URL(urls[0]);
                Log.i(TAG, urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(45000);
                //connection.setRequestProperty("Accept", "app2");
                connection.connect();
                if(connection.getResponseCode() == 200){
                    InputStream responseInputStream = connection.getInputStream();
                    Scanner scanner = new Scanner(responseInputStream);
                    StringBuilder builder = new StringBuilder();
                    while(scanner.hasNext()){
                        data.add(scanner.nextLine().replace(",", " "));
                    }
                    responseInputStream.close();
                    scanner.close();
                }else{
                    Log.i(TAG, "error "  + connection.getResponseCode());
                }

            }catch (Exception e){
                Log.e(TAG, e.toString());
            }



            data.remove(0);
            return data;
        }

        protected void onPostExecute(ArrayList<String> result) {

            Bundle bundle = new Bundle();
            bundle.putStringArrayList("data", result);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            StockDataDisplayFragment fragment = new StockDataDisplayFragment();
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.activity_assignment_2_constraint_layout, fragment);
            fragmentTransaction.commit();
            DisplayButton.setVisibility(View.INVISIBLE);
            BackButton.setVisibility(View.VISIBLE);
        }
    }


    public void onFragmentInteraction(Uri uri){

    }


}

