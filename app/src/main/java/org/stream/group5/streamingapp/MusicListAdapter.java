package org.stream.group5.streamingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MusicViewHolder>
{
    private final LinkedList<String> musicList;
    private LayoutInflater mInflater;

    public MusicListAdapter(Context context, LinkedList<String> musicList) {
        mInflater = LayoutInflater.from(context);
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mItemVIew = mInflater.inflate(R.layout.musiclist_item, parent, false);
        return new MusicViewHolder(mItemVIew, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        String mCurrent = musicList.get(position);
        holder.musicItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView musicItemView;
        final MusicListAdapter adapter;


        public MusicViewHolder(View itemView, MusicListAdapter adapter) {
            super(itemView);
            musicItemView = itemView.findViewById(R.id.song);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            // Use that to access the affected item in musicList.
            String element = musicList.get(mPosition);
            // Change the word in the musicList.
            musicList.set(mPosition, "Clicked! " + element);
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            adapter.notifyDataSetChanged();
        }
    }
}
