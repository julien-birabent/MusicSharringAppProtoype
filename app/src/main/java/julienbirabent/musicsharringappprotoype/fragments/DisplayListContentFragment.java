package julienbirabent.musicsharringappprotoype.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import julienbirabent.musicsharringappprotoype.FragmentUtils;
import julienbirabent.musicsharringappprotoype.MockUpContent;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.adapter.PlaylistAdapter;
import julienbirabent.musicsharringappprotoype.adapter.SongAdapter;
import julienbirabent.musicsharringappprotoype.models.Playlist;

/**
 * Created by Julien on 2017-07-18.
 */

public class DisplayListContentFragment extends Fragment {

    private ListView songsList;

    public DisplayListContentFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onResume() {
        super.onResume();

        FragmentUtils.activateNaviguationBack(this,true);

        songsList = (ListView) getActivity().findViewById(R.id.listView_playlist_content);

        Bundle args = getArguments();
        Playlist playlistPassed =(Playlist) args.getSerializable("playlist");

        SongAdapter adapter = new SongAdapter(this.getActivity(), R.layout.playlist_content_row, playlistPassed.getSongs());
        songsList.setAdapter(adapter);


    }

    @Override
    public void onPause() {
        super.onPause();
        songsList.setAdapter(null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playlist_content,container,false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.playlist_content_base_menu, menu);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
