package julienbirabent.musicsharringappprotoype.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.models.MusicType;
import julienbirabent.musicsharringappprotoype.util.MusicTypePair;

/**
 * Created by julbi on 2017-07-24.
 */

public class MusicTypePreferencesAdapter extends ArrayAdapter<MusicTypePair> {
    private Context context;
    private int resourceId;
    private MusicTypePair[] data = null;

    public MusicTypePreferencesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull MusicTypePair[] objects) {
        super(context, resource, objects);

        this.context = context;
        this.resourceId = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        MusicTypePreferenceHolder holder = null;

        if(row ==null)

        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resourceId, parent, false);

            holder = new MusicTypePreferenceHolder();
            holder.leftLabel = (TextView) row.findViewById(R.id.music_type_selection_left_label);
            holder.rightLabel = (TextView) row.findViewById(R.id.music_type_selection_right_label);
            holder.left = (CheckBox) row.findViewById(R.id.music_type_preferences_left_checkbox);
            holder.right = (CheckBox) row.findViewById(R.id.music_type_preferences_right_checkbox);
            row.setTag(holder);
        }
        else

        {
            holder = (MusicTypePreferenceHolder) row.getTag();
        }

        MusicTypePair musicTypes = data[position];

        if(musicTypes.getRight()!=null){
            holder.rightLabel.setText(musicTypes.getRight().getName());
            holder.right.setChecked(musicTypes.getRight().isDesired());
        }else{
            holder.rightLabel.setVisibility(View.GONE);
            holder.right.setVisibility(View.GONE);
        }
        holder.leftLabel.setText(musicTypes.getLeft().getName());
        holder.left.setChecked(musicTypes.getLeft().isDesired());


        return row;
    }

    private static class MusicTypePreferenceHolder {

        TextView leftLabel;
        TextView rightLabel;
        CheckBox left;
        CheckBox right;
    }

}

