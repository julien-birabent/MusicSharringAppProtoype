package julienbirabent.musicsharringappprotoype.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import julienbirabent.musicsharringappprotoype.FragmentUtils;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by Julien on 2017-07-19.
 */

public class SongDetailPageFragment extends Fragment {


    public SongDetailPageFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.song_detail_page_fragment,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        FragmentUtils.activateNaviguationBack(this,true);

        Bundle args = getArguments();
        Song song =(Song) args.getSerializable("song");

        FragmentUtils.changeActionBarTittle(this, getString(R.string.title_song_page));



    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.song_page_menu, menu);
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
