package julienbirabent.musicsharringappprotoype.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import julienbirabent.musicsharringappprotoype.R;

/**
 * Created by julbi on 2017-07-18.
 */

public class CustomPlaylistsFragment extends Fragment {

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
}
