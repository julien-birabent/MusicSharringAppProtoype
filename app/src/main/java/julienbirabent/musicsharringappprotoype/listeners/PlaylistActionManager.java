package julienbirabent.musicsharringappprotoype.listeners;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

import julienbirabent.musicsharringappprotoype.MainActivity;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.fragments.AddToPlaylistFragment;
import julienbirabent.musicsharringappprotoype.models.Playlist;

/**
 * Created by Julien on 2017-07-19.
 */

public class PlaylistActionManager implements View.OnClickListener , AdapterView.OnItemClickListener{

    private Context context;
    private Playlist playlist;
    private ArrayList<Playlist> playlistSelected;

    public PlaylistActionManager(Context context, Playlist playlist) {
        this.playlist= playlist;
        this.context = context;

    }

    public PlaylistActionManager(Context context) {
        this.context = context;
    }

    public PlaylistActionManager(Context context, ArrayList<Playlist> playlistSelected) {
        this.context = context;
        this.playlistSelected = playlistSelected;
    }

    @Override
    public void onClick(View view) {
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(context, view);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.playlist_item_popup_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {


                switch (item.getItemId()) {

                    case R.id.add_all_to_custom_playlist:

                        Activity activity = (Activity) context;
                        FragmentManager fragmentManager = activity.getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                           /* Transaction de remplacement de fragment */
                        AddToPlaylistFragment addToPlaylistFragment = new AddToPlaylistFragment();

                        Bundle args = new Bundle();
                        args.putSerializable("playlist", playlist);
                        args.putBoolean("single",false);
                        addToPlaylistFragment.setArguments(args);
                        fragmentTransaction.replace(R.id.content, addToPlaylistFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                        break;

                    case R.id.delete_playlist:
                        break;

                }


                return true;
            }
        });

        popup.show(); //showing popup menu
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getAdapter().getItem(i)!= null){

            if(view.findViewById(R.id.add_to_playlist_checkbox)!= null){
               CheckBox check = (CheckBox) view.findViewById(R.id.add_to_playlist_checkbox);

                check.setChecked(!check.isChecked());
                Playlist playlist = (Playlist) adapterView.getAdapter().getItem(i);
                if(check.isChecked()){
                    playlistSelected.add(playlist);
                }else{
                    playlistSelected.remove(playlist);
                }
            }
        }

    }
}
