package julienbirabent.musicsharringappprotoype.listeners;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import julienbirabent.musicsharringappprotoype.MockUpContent;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.fragments.SongDetailPageFragment;
import julienbirabent.musicsharringappprotoype.models.Song;
import julienbirabent.musicsharringappprotoype.player.MusicPlayer;

/**
 * Created by Julien on 2017-07-19.
 */

public class SongActionManager implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Context context;
    private Song song;
    private PopupMenu popup;

    public SongActionManager(Context context, Song song) {
        this.context = context;
        this.song = song;
    }

    public SongActionManager(Context context) {
        this.context = context;
    }

    @Override
    public void onClick( View view) {
        //Creating the instance of PopupMenu
        if(popup== null){
            popup = new PopupMenu(context, view);
            //Inflating the Popup using xml file
            popup.getMenuInflater()
                    .inflate(R.menu.song_popup_menu, popup.getMenu());

            MenuItem recommandItem = popup.getMenu().findItem(R.id.recommande_song);

            if(song.isRecommanded()){
                recommandItem.setTitle(R.string.popmenu_unrecommand_this_song);
                recommandItem.setChecked(true);
            }else{
                recommandItem.setTitle(R.string.popmenu_recommand_this_song);
                recommandItem.setChecked(false);
            }

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId()) {

                        case R.id.song_detail:
                            showSongDetails();
                            break;

                        case R.id.recommande_song:
                            if(!item.isChecked()){
                                recommandASong();
                                item.setChecked(true);
                            }else{
                                unrecommandeSong();
                                item.setChecked(false);
                            }

                            break;

                        case R.id.add_this_song:
                            break;
                        case R.id.delete_song:
                            break;

                    }

                    return true;
                }
            });
        }
        popup.show(); //showing popup menu
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getAdapter().getItem(i)!= null){
            Song songSelected = (Song) adapterView.getAdapter().getItem(i);
            MusicPlayer.getInstance().nextSong(songSelected);
            songSelected.setListened(true);
        }
    }

    private void unrecommandeSong(){
        song.setRecommanded(false);
        MockUpContent.getLocalUser().deleteRecommandedSong(song);

    }

    private void recommandASong() {
        song.setRecommanded(true);
        MockUpContent.getLocalUser().addRecommandedSong(song);
    }

    private void showSongDetails() {
        try {
            Activity activity = (Activity) context;

            FragmentManager fragmentManager;
            // Return the fragment manager
            fragmentManager = activity.getFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                           /* Transaction de remplacement de fragment */
            SongDetailPageFragment songDetailPageFragment = new SongDetailPageFragment();

            Bundle args = new Bundle();
            args.putSerializable("song", song);
            songDetailPageFragment.setArguments(args);
            fragmentTransaction.replace(R.id.content, songDetailPageFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } catch (ClassCastException e) {
            Log.d("SongActionManager", "Can't get the fragment manager with this");
        }
    }

}
