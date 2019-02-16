package com.example.asim.assignment2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
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
