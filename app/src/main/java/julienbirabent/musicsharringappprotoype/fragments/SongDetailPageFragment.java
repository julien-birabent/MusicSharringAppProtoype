package julienbirabent.musicsharringappprotoype.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import julienbirabent.musicsharringappprotoype.FragmentUtils;
import julienbirabent.musicsharringappprotoype.MockUpContent;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by Julien on 2017-07-19.
 */

public class SongDetailPageFragment extends Fragment {

    private Song song;

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
        return inflater.inflate(R.layout.song_detail_page_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        FragmentUtils.activateNaviguationBack(this, true);

        Bundle args = getArguments();
         song = (Song) args.getSerializable("song");

        FragmentUtils.changeActionBarTittle(this, getString(R.string.title_song_page));

        displaySongDetails(song);


    }

    private void displaySongDetails(Song song) {

        TextView title = ((TextView) getActivity().findViewById(R.id.song_page_title));
        TextView artist = ((TextView) getActivity().findViewById(R.id.song_page_artist));
        TextView album = ((TextView) getActivity().findViewById(R.id.song_page_album));
        ImageView vignette = ((ImageView) getActivity().findViewById(R.id.song_page_vignette_album));
        TextView recommandations = ((TextView) getActivity().findViewById(R.id.page_song_recommanded_by_label));

        title.setText(song.getName());
        artist.setText(song.getArtist());
        album.setText(song.getAlbum());
        vignette.setImageResource(song.getAlbumVignetteId());
        recommandations.setText(song.getUserRecommandationsFormatted());

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.song_page_menu, menu);

        MenuItem itemRecommand =  menu.findItem(R.id.recommande_song);
        if(itemRecommand.getItemId() == R.id.recommande_song){
            if(song.isRecommanded()){
                itemRecommand.setTitle(R.string.popmenu_unrecommand_this_song);
                itemRecommand.setChecked(true);
            }else{
                itemRecommand.setTitle(R.string.popmenu_recommand_this_song);
                itemRecommand.setChecked(false);
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                return true;
            case R.id.recommande_song :
                if(!item.isChecked()){
                    recommandASong();
                    item.setTitle(R.string.popmenu_unrecommand_this_song);
                    item.setChecked(true);
                }else{
                    unrecommandeSong();
                    item.setTitle(R.string.popmenu_recommand_this_song);
                    item.setChecked(false);
                }


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void unrecommandeSong(){
        song.setRecommanded(false);
        MockUpContent.getLocalUser().deleteRecommandedSong(song);

    }

    private void recommandASong() {
        song.setRecommanded(true);
        MockUpContent.getLocalUser().addRecommandedSong(song);
    }
}
