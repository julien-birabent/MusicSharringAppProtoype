package julienbirabent.musicsharringappprotoype.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Observable;
import java.util.Observer;

import julienbirabent.musicsharringappprotoype.FragmentUtils;
import julienbirabent.musicsharringappprotoype.MockUpContent;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.adapter.PlaylistAdapter;
import julienbirabent.musicsharringappprotoype.models.Playlist;
import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by julbi on 2017-07-18.
 */

public class CustomPlaylistsFragment extends Fragment implements Observer {

    private  ListView playlists;
    private View rootView;
    private Context context;

    public CustomPlaylistsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_custom_playlists,container,false);
        playlists = (ListView) rootView.findViewById(R.id.listView_custom_playlists);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        FragmentUtils.activateNaviguationBack(this,false);
        inflater.inflate(R.menu.custom_playlists_menu, menu);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void initPlaylists(Context context){


        playlists.setOnItemClickListener((AdapterView.OnItemClickListener) context);
        PlaylistAdapter adapter = new PlaylistAdapter(context, R.layout.playlist_row, MockUpContent.getLocalUser().getPlaylists());
        playlists.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onResume() {
        super.onResume();
        FragmentUtils.changeActionBarTittle(this,getString(R.string.title_custom_playlist));
        initPlaylists(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        playlists.setAdapter(null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.add_playlist :

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_playlists_dialog);
                dialog.setTitle("Add a playlist");


                Button confirmBtn = (Button) dialog.findViewById(R.id.add_playlist_confirm_btn);
                // if button is clicked, close the custom dialog
                confirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText response = (EditText)dialog.findViewById(R.id.add_playlist_edittext);
                        Playlist playlist = new Playlist(response.getText().toString(), new Song[0]);
                        MockUpContent.getLocalUser().addPlaylist(playlist);
                        dialog.dismiss();
                    }
                });

                Button cancelBtn = (Button) dialog.findViewById(R.id.add_playlist_cancel_btn);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

                break;

            case R.id.share_a_song :
                break;
        }

        return true;
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof Playlist[] && o != null && playlists!= null){

            initPlaylists(context);
        }
    }
}
