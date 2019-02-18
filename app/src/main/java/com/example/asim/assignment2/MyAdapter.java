package com.example.asim.assignment2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

// Adapter for the RecyclerView

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a TextView in this case
        public TextView dateTextView;
        public TextView openTextView;
        public TextView highTextView;
        public TextView lowTextView;
        public TextView closeTextView;
        public TextView volumeTextView;
        public TextView adjcloseTextView;
        public MyViewHolder(View v) {

            super(v);
            dateTextView = v.findViewById(R.id.dateTextView);
            openTextView = v.findViewById(R.id.openTextView);
            highTextView = v.findViewById(R.id.highTextView);
            lowTextView = v.findViewById(R.id.lowTextView);
            closeTextView = v.findViewById(R.id.closeTextView);
            volumeTextView = v.findViewById(R.id.volumeTextView);
            adjcloseTextView = v.findViewById(R.id.adjcloseTextView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.column_headers, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.textView.setText(mDataset[position]);
        String[] data = mDataset[position].split(" ");
        holder.dateTextView.setText(data[0]);
        holder.openTextView.setText(data[1]);
        holder.highTextView.setText(data[2]);
        holder.lowTextView.setText(data[3]);
        holder.closeTextView.setText(data[4]);
        holder.volumeTextView.setText(data[5]);
        holder.adjcloseTextView.setText(data[6]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }


}
