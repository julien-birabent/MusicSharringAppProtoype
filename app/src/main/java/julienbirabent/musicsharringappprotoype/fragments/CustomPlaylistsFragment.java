package julienbirabent.musicsharringappprotoype.fragments;

import android.app.Fragment;
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

import julienbirabent.musicsharringappprotoype.FragmentUtils;
import julienbirabent.musicsharringappprotoype.MockUpContent;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.adapter.PlaylistAdapter;
import julienbirabent.musicsharringappprotoype.models.Playlist;
import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by julbi on 2017-07-18.
 */

public class CustomPlaylistsFragment extends Fragment {

    private ListView playlists;

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
        return inflater.inflate(R.layout.fragment_custom_playlists,container,false);
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

    private void initPlaylists(){

        playlists = (ListView) this.getActivity().findViewById(R.id.listView_custom_playlists);
        playlists.setOnItemClickListener((AdapterView.OnItemClickListener) getActivity());
        PlaylistAdapter adapter = new PlaylistAdapter(this.getActivity(), R.layout.playlist_row, MockUpContent.getInstance().getCustomPlaylistsMockUp());
        playlists.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        initPlaylists();
        FragmentUtils.changeActionBarTittle(this,getString(R.string.title_custom_playlist));

    }

    @Override
    public void onPause() {
        super.onPause();
        playlists.setAdapter(null);
    }
}
