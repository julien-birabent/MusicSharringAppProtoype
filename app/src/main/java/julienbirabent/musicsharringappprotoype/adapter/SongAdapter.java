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
import julienbirabent.musicsharringappprotoype.listeners.SongActionManager;
import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by julbi on 2017-07-19.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    private Context context;
    private int resourceId;
    private Song[] data = null;
    private boolean fromGeneratedPlaylist = false;

    private static class SongHolder{
        TextView position;
        TextView informations;
        TextView recommandations;
        ImageButton moreOptions;
        ImageView listenedIndicator;
    }
    
    public SongAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Song[] objects) {
        super(context, resource, objects);

        this.context = context;
        this.resourceId = resource;
        this.data = objects;
    }

    public SongAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Song[] objects, boolean fromGeneratedPlaylist) {
        super(context, resource, objects);

        this.context = context;
        this.resourceId = resource;
        this.data = objects;
        this.fromGeneratedPlaylist = fromGeneratedPlaylist;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        SongHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resourceId, parent, false);

            holder = new SongHolder();
            holder.position = (TextView)row.findViewById(R.id.song_position_in_list);
            holder.informations = (TextView)row.findViewById(R.id.song_basic_informations);
            holder.recommandations = (TextView) row.findViewById(R.id.song_recommandations);
            holder.moreOptions = (ImageButton) row.findViewById(R.id.playlist_content_more_options);
            holder.listenedIndicator = (ImageView)  row.findViewById(R.id.listened_indicator);

            row.setTag(holder);


        }
        else
        {
            holder = (SongHolder) row.getTag();
        }
        if(data[position]!=null){
            Song song = data[position];
            holder.position.setText(Integer.toString(position+1));
            holder.informations.setText(song.getName()+" - " + song.getArtist());
            holder.recommandations.setText(song.getUserRecommandationsFormatted());
            holder.moreOptions.setOnClickListener(new SongActionManager(context,song));

            if(fromGeneratedPlaylist){
                if(!song.isListened()){
                    holder.listenedIndicator.setVisibility(View.VISIBLE);
                }else{
                    holder.listenedIndicator.setVisibility(View.GONE);
                }
            }else{
                holder.listenedIndicator.setVisibility(View.GONE);
            }
        }

        return row;
    }

}
