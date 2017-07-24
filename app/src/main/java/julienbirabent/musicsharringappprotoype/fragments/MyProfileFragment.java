package julienbirabent.musicsharringappprotoype.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Observable;
import java.util.Observer;

import julienbirabent.musicsharringappprotoype.MockUpContent;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.adapter.PlaylistAdapter;
import julienbirabent.musicsharringappprotoype.adapter.SongAdapter;
import julienbirabent.musicsharringappprotoype.listeners.SongActionManager;
import julienbirabent.musicsharringappprotoype.models.Playlist;
import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by julbi on 2017-07-18.
 */

public class MyProfileFragment extends Fragment implements Observer {

    private ListView myPlaylists;
    private ListView myRecommandedSongs;
    private Context context;

    public MyProfileFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_my_profile, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        inflater.inflate(R.menu.my_profile_menu, menu);
    }

    @Override
    public void onResume() {
        super.onResume();

        myPlaylists = (ListView) this.getActivity().findViewById(R.id.myprofile_playlists);
        myPlaylists.setOnItemClickListener((AdapterView.OnItemClickListener) getActivity());
        PlaylistAdapter adapter = new PlaylistAdapter(context, R.layout.playlist_row, MockUpContent.getInstance().getLocalUser().getPlaylists());
        myPlaylists.setAdapter(adapter);

        myRecommandedSongs = (ListView) this.getActivity().findViewById(R.id.myprofile_recommanded_playlists);
        SongAdapter songAdapter = new SongAdapter(context, R.layout.playlist_content_row, MockUpContent.getInstance().getLocalUser().getRecommandedSongs());
        myRecommandedSongs.setAdapter(songAdapter);
        myRecommandedSongs.setOnItemClickListener(new SongActionManager(this.getActivity()));


    }

    @Override
    public void onPause() {
        super.onPause();
        myPlaylists.setAdapter(null);
        myRecommandedSongs.setAdapter(null);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof Playlist[]) {
            PlaylistAdapter adapter = new PlaylistAdapter(context, R.layout.playlist_row, (Playlist[]) o);
            myPlaylists.setAdapter(adapter);
        }
        if (o instanceof Song[]) {
            myRecommandedSongs.setAdapter(null);
            SongAdapter songAdapter = new SongAdapter(context, R.layout.playlist_content_row, (Song[]) o);
            myRecommandedSongs.setAdapter(songAdapter);
        }

    }
}

