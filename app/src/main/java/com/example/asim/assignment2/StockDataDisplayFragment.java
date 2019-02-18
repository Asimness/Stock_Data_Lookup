package com.example.asim.assignment2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Asim Anis
 * Assignment 2 CS4301.002
 * Professor John Cole
 * 2/19/2019
 * This program takes in a ticker symbol from the user and retrieves the stock data for this symbol
 * from a website and reads in this information to display it to the user. The processing of the data is done on
 * a asynchronously. There are 2 fragments one to display the prompt for the user and the other to display
 * the stock data. The user can switch between these fragments using the Display and Back Buttons.
 */

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StockDataDisplayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StockDataDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StockDataDisplayFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private OnFragmentInteractionListener mListener;

    public StockDataDisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StockDataDisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StockDataDisplayFragment newInstance(String param1, String param2) {
        StockDataDisplayFragment fragment = new StockDataDisplayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stock_data_display, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // get data from the arguments bundle
        ArrayList<String> data = getArguments().getStringArrayList("data");
        updateRecyclerView(data);


        String displayMessage = getArguments().getString("DisplayMessage");
        TextView textView = rootView.findViewById(R.id.stockDisplayText);

        if(displayMessage.startsWith("error"))
            textView.setText(displayMessage + " press the back button \n and enter a new symbol");
        else
            textView.setText("Showing data for " + displayMessage);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void updateRecyclerView(ArrayList<String> result){
        String[] dataset = new String[result.size()];
        dataset = result.toArray(dataset);

        mAdapter = new MyAdapter(dataset);
        recyclerView.setAdapter(mAdapter);
    }
}
