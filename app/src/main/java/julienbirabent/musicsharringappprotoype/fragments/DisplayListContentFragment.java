package julienbirabent.musicsharringappprotoype.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Observable;
import java.util.Observer;

import julienbirabent.musicsharringappprotoype.FragmentUtils;
import julienbirabent.musicsharringappprotoype.MockUpContent;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.adapter.PlaylistAdapter;
import julienbirabent.musicsharringappprotoype.adapter.SongAdapter;
import julienbirabent.musicsharringappprotoype.listeners.SongActionManager;
import julienbirabent.musicsharringappprotoype.models.Playlist;
import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by Julien on 2017-07-18.
 */

public class DisplayListContentFragment extends Fragment implements Observer{

    private ListView songsList;
    private Context context;

    public DisplayListContentFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onResume() {
        super.onResume();

        FragmentUtils.activateNaviguationBack(this,true);

        songsList = (ListView) getActivity().findViewById(R.id.listView_playlist_content);

        Bundle args = getArguments();
        Playlist playlistPassed =(Playlist) args.getSerializable("playlist");
        boolean generated = args.getBoolean("generated");


        FragmentUtils.changeActionBarTittle(this, playlistPassed.getName());

        SongAdapter adapter = new SongAdapter(context, R.layout.playlist_content_row, playlistPassed.getSongs(),generated);
        songsList.setAdapter(adapter);

        songsList.setOnItemClickListener(new SongActionManager(this.getActivity()));

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
        FragmentManager fragmentManager = getFragmentManager();
        switch(item.getItemId()) {
            case android.R.id.home:
                fragmentManager.popBackStack();
                return true;

            case R.id.share_a_song:
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RecommandSongFragment recommandSongFragment = new RecommandSongFragment();
                fragmentTransaction.replace(R.id.content, recommandSongFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof Playlist){
            songsList.setAdapter(null);
            SongAdapter adapter = new SongAdapter(context, R.layout.playlist_content_row, ((Playlist) o).getSongs());
            songsList.setAdapter(adapter);
        }
    }
}
