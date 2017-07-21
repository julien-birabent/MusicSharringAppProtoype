package julienbirabent.musicsharringappprotoype.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.listeners.PlaylistActionManager;
import julienbirabent.musicsharringappprotoype.models.Playlist;

/**
 * Created by Julien on 2017-07-18.
 */

public class PlaylistAdapter extends ArrayAdapter<Playlist> {

    private Context context;
    private int resourceId;
    private Playlist[] data = null;

    public PlaylistAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Playlist[] objects) {
        super(context, resource, objects);

        this.context = context;
        this.resourceId = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        PlaylistHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resourceId, parent, false);

            holder = new PlaylistHolder();
            holder.vignette = (ImageView)row.findViewById(R.id.playlist_vignette);
            holder.name = (TextView)row.findViewById(R.id.playlist_name);
            holder.nbSongs = (TextView) row.findViewById(R.id.playlist_number_of_songs);
            holder.moreOptions = (ImageButton) row.findViewById(R.id.playlist_moreOptions);

            row.setTag(holder);
        }
        else
        {
            holder = (PlaylistHolder) row.getTag();
        }

        Playlist playlist = data[position];
        holder.name.setText(playlist.getName());
        holder.vignette.setImageResource(playlist.getIdVignette());
        if(playlist.isGenerated()){
            if(playlist.getNonListenedSongNumber() == 0){
                holder.nbSongs.setText("Congrats! You listened to all the songs of this playlist.");
            }else{
                holder.nbSongs.setText(playlist.getNonListenedSongNumber() + " songs you never listened to.");
            }
        }else holder.nbSongs.setText(playlist.getNbOfSongs() + " songs.");
        holder.moreOptions.setOnClickListener(new PlaylistActionManager(context));


        return row;
    }

    private static class PlaylistHolder{

        ImageView vignette;
        TextView name;
        TextView nbSongs;
        ImageButton moreOptions;

    }
}
