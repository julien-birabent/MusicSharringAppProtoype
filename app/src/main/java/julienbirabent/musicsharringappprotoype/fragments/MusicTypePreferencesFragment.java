package julienbirabent.musicsharringappprotoype.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;

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

/**
 * Created by julbi on 2017-07-24.
 */

public class MusicTypePreferencesFragment extends Fragment {

    private View rootView;
    private Context context;

    public MusicTypePreferencesFragment() {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.music_type_preferences_fragment, container, false);

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        FragmentUtils.activateNaviguationBack(this, true);
        FragmentUtils.changeActionBarTittle(this, getString(R.string.title_music_type_preferences));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
