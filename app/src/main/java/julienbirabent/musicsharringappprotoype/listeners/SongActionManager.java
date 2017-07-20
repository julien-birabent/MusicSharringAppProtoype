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

import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.fragments.SongDetailPageFragment;
import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by Julien on 2017-07-19.
 */

public class SongActionManager implements View.OnClickListener,AdapterView.OnItemClickListener {

    private Context context;
    private int songId;

    public SongActionManager(Context context, int songId) {
        this.context = context;
        this.songId = songId;
    }

    public SongActionManager(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(final View view) {
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(context, view);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.song_popup_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){

                    case R.id.song_detail :


                        try{
                            final Activity activity = (Activity) context;

                            ListView songList = (ListView) activity.findViewById(R.id.listView_playlist_content);
                            Song song = (Song)songList.getAdapter().getItem(songId);

                            FragmentManager fragmentManager;
                            // Return the fragment manager
                            fragmentManager =  activity.getFragmentManager();

                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                           /* Transaction de remplacement de fragment */
                            SongDetailPageFragment songDetailPageFragment =  new SongDetailPageFragment();

                            Bundle args = new Bundle();
                            args.putSerializable("song", song);
                            songDetailPageFragment.setArguments(args);
                            fragmentTransaction.replace(R.id.content, songDetailPageFragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();

                        } catch (ClassCastException e) {
                            Log.d("SongActionManager", "Can't get the fragment manager with this");
                        }



                        break;

                    case R.id.recommande_song :
                        break;

                    case R.id.add_this_song :
                        break;

                }


                return true;
            }
        });

        popup.show(); //showing popup menu
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Song songSelected = (Song)adapterView.getAdapter().getItem(i);

        Toast.makeText(context, "You want to play the song " + songSelected.getName(), Toast.LENGTH_SHORT).show();

        /* TODO implémenter le code permettant de lire la chanson dans le player et de garder la trace de cette lecture*/

    }
}
