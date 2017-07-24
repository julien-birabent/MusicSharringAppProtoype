package julienbirabent.musicsharringappprotoype.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.ListView;

import julienbirabent.musicsharringappprotoype.FragmentUtils;
import julienbirabent.musicsharringappprotoype.MockUpContent;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.adapter.PlaylistAdapter;

/**
 * Created by julbi on 2017-07-18.
 */

public class GeneratedPlaylistsFragment extends Fragment {

    private ListView playlists;

    public GeneratedPlaylistsFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_generated_playlists,container,false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        FragmentUtils.activateNaviguationBack(this,false);
        inflater.inflate(R.menu.generated_playlists_menu, menu);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        initPlaylists();
        FragmentUtils.changeActionBarTittle(this,getString(R.string.title_generated_playlist));
    }

    @Override
    public void onPause() {
        super.onPause();
        playlists.setAdapter(null);
    }

    private void initPlaylists(){
        playlists = (ListView) this.getActivity().findViewById(R.id.listView_generated_playlists);
        playlists.setOnItemClickListener((AdapterView.OnItemClickListener) getActivity());
        PlaylistAdapter adapter = new PlaylistAdapter(this.getActivity(), R.layout.playlist_row, MockUpContent.getInstance().getGeneratedPlaylistsFromPreferences());
        playlists.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.select_music_types:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MusicTypePreferencesFragment musicTypePreferencesFragment = new MusicTypePreferencesFragment();

                fragmentTransaction.replace(R.id.content, musicTypePreferencesFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
