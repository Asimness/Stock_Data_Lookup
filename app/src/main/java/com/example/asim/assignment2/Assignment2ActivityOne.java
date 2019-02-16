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
import android.widget.ProgressBar;
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

    StringBuilder URLBuilder = new StringBuilder("http://utdallas.edu/~John.Cole/2017Spring/"); // Base URL for data
    final String TAG = "ASSIGNMENT2"; // Tag for logging

    // Used for the Recycler View
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    // Buttons on activity and message tothe user
    private Button displayButton;
    private Button backButton;
    private String displayMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment2_one);

        // Initialize buttons on the activity
        displayButton = findViewById(R.id.displayButton);
        backButton = findViewById(R.id.backButton);

        // Add the prompt fragment to the activity
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        StockPromptFragment fragment = new StockPromptFragment();
        fragmentTransaction.replace(R.id.activity_assignment_2_constraint_layout, fragment);
        fragmentTransaction.commit();
    }

    /**
     * This method is triggered when a user clicks on the display button.
     * It passes text entered in the EditText field with the base URL to the Asynch Task
     * inorder to retrieve the stock data.
     * @param view
     */
    public void onClickDisplayData(View view) {
        EditText editText = findViewById(R.id.inputSymbolText);
        String symbol = editText.getText().toString();
        URLBuilder.append(symbol.toUpperCase());
        displayMessage = symbol.toUpperCase();
        URLBuilder.append(".txt");
        String url = URLBuilder.toString();
        new GetStockDataTask().execute(url);
        URLBuilder = new StringBuilder("http://utdallas.edu/~John.Cole/2017Spring/");
    }

    /**
     * This method is triggered when a user clicks on the back button.
     * It returns the user to the prompt fragment.
     * @param view
     */
    public void onClickToPromptFragment(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        StockPromptFragment fragment = new StockPromptFragment();
        fragmentTransaction.replace(R.id.activity_assignment_2_constraint_layout, fragment);
        fragmentTransaction.commit();
        displayButton.setVisibility(View.VISIBLE);
        backButton.setVisibility(View.INVISIBLE);
    }

    /**
     * Async task class for retrieving the stock data.
     */
    class GetStockDataTask extends AsyncTask<String, Integer, ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(String... urls) {
            onProgressUpdate(1);
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


        protected void onProgressUpdate(Integer... progress) {
            StockPromptFragment fragment = (StockPromptFragment) getSupportFragmentManager().findFragmentById(R.id.activity_assignment_2_constraint_layout);
            fragment.setProgressBarOn();
        }

        protected void onPostExecute(ArrayList<String> result) {

            Bundle bundle = new Bundle();
            bundle.putStringArrayList("data", result);
            bundle.putString("DisplayMessage", displayMessage);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            StockDataDisplayFragment fragment = new StockDataDisplayFragment();
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.activity_assignment_2_constraint_layout, fragment);
            fragmentTransaction.commit();
            displayButton.setVisibility(View.INVISIBLE);
            backButton.setVisibility(View.VISIBLE);
        }
    }

    public void onFragmentInteraction(Uri uri){

    }
}

