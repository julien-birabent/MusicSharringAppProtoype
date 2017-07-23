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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.listeners.PlaylistActionManager;
import julienbirabent.musicsharringappprotoype.models.Playlist;

/**
 * Created by julbi on 2017-07-22.
 */

public class AddToPlaylistAdapter extends ArrayAdapter {

    private Context context;
    private int resourceId;
    private Playlist[] data = null;

    public AddToPlaylistAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Playlist[] objects) {
        super(context, resource, objects);

        this.context = context;
        this.resourceId = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        PlaylistToAddHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resourceId, parent, false);

            holder = new PlaylistToAddHolder();
            holder.vignette = (ImageView)row.findViewById(R.id.playlist_vignette);
            holder.name = (TextView)row.findViewById(R.id.playlist_name);
            holder.nbSongs = (TextView) row.findViewById(R.id.playlist_number_of_songs);
            holder.check = (CheckBox) row.findViewById(R.id.add_to_playlist_checkbox);
            row.setTag(holder);
        }
        else
        {
            holder = (PlaylistToAddHolder) row.getTag();
        }

        Playlist playlist = data[position];
        holder.name.setText(playlist.getName());
        holder.vignette.setImageResource(playlist.getIdVignette());
        holder.nbSongs.setText(playlist.getNbOfSongs() + " songs.");

        return row;
    }

    private static class PlaylistToAddHolder{

        ImageView vignette;
        TextView name;
        TextView nbSongs;
        CheckBox check;

    }

}
