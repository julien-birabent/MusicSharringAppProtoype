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
import android.widget.ImageView;
import android.widget.TextView;

import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by julbi on 2017-07-25.
 */

public class RecommandSongAdapter extends ArrayAdapter <Song>{

    private Context context;
    private int resourceId;
    private Song[] data = null;

    public RecommandSongAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Song[] objects) {
        super(context, resource, objects);

        this.context = context;
        this.resourceId = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        RecommandSongHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resourceId, parent, false);

            holder = new RecommandSongHolder();
            holder.vignette = (ImageView)row.findViewById(R.id.recommand_song_item_vignette);
            holder.titleSong = (TextView)row.findViewById(R.id.recommand_song_item_title);
            holder.artistAlbum = (TextView) row.findViewById(R.id.recommand_song_item_album_artist);
            holder.check = (CheckBox) row.findViewById(R.id.recommand_song_item_checkbox);
            row.setTag(holder);
        }
        else
        {
            holder = (RecommandSongHolder) row.getTag();
        }

        if(data[position]!=null){
            Song song = data[position];

            holder.vignette.setImageResource(song.getAlbumVignetteId());
            holder.titleSong.setText(song.getName());
            holder.artistAlbum.setText(song.getAlbum() + " - " + song.getArtist());

        }


        return  row;
    }

    private static class RecommandSongHolder{

        ImageView vignette;
        TextView titleSong;
        TextView artistAlbum;
        CheckBox check;

    }
}
