package com.example.asim.assignment2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
 * {@link StockPromptFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StockPromptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StockPromptFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ProgressBar progressBar;

    private OnFragmentInteractionListener mListener;

    public StockPromptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StockPromptFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StockPromptFragment newInstance(String param1, String param2) {
        StockPromptFragment fragment = new StockPromptFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_stock_prompt, container, false);

        // Set the progress bar
        progressBar = rootView.findViewById(R.id.progressBar);
        // Turn the working indicator off
        setProgressBarOff();

        return rootView;
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

    /**
     * This method hides to working indicator.
     */
    public void setProgressBarOff(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    /**
     * This method makes the working indicator visible.
     */
    public void setProgressBarOn(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
}
