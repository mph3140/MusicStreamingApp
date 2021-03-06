package org.stream.group5.streamingapp;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder>
{
    private ArrayList<Song> songs;
    private LayoutInflater songInf;
    private org.stream.group5.streamingapp.MainActivity activity;

    public SongAdapter(Context c, ArrayList<Song> theSongs) {
        this.activity = (org.stream.group5.streamingapp.MainActivity) c;
        songs=theSongs;
        songInf=LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public SongAdapter.SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = songInf.inflate(R.layout.song, parent, false);
        return new SongAdapter.SongViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.SongViewHolder holder, int position) {
        String currentTitle = songs.get(position).getTitle();
        String currentArtist = songs.get(position).getArtist();
        holder.songTitleView.setText(currentTitle);
        holder.songArtistView.setText(currentArtist);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        public final TextView songTitleView;
        public final TextView songArtistView;
        final SongAdapter adapter;


        public SongViewHolder(View itemView, SongAdapter adapter) {
            super(itemView);
            songTitleView = itemView.findViewById(R.id.song_title);
            songArtistView = itemView.findViewById(R.id.song_artist);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get the position of the item that was clicked.
            int position = getLayoutPosition();
            // Use that to access the affected item in musicList.
            activity.songPicked(position);
            Log.d("C", "CLICKED");
            // Change the word in the musicList.
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            adapter.notifyDataSetChanged();
        }

        @Override
        public boolean onLongClick(View v) {
            View popupView = LayoutInflater.from(activity).inflate(R.layout.popup_window, null);

            int songPosn = getLayoutPosition();
            final Song playSong = songs.get(songPosn);

            final EditText title = (EditText) popupView.findViewById(R.id.titleText);
            final EditText artist = (EditText) popupView.findViewById(R.id.artistText);

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setView(popupView)
            .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            })
            .setNegativeButton("Discogs Search", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DiscogsKt.srch(title.getText().toString(), artist.getText().toString(), activity);
                }
            });

            builder.setTitle("Edit Info");
            builder.create();

            // pass song title and name to textboxes on alert dialog
            title.setText(playSong.getTitle());
            artist.setText(playSong.getArtist());

            builder.show();

            return true;
        }
    }
}
