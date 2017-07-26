package julienbirabent.musicsharringappprotoype.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import julienbirabent.musicsharringappprotoype.FragmentUtils;
import julienbirabent.musicsharringappprotoype.MockUpContent;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.adapter.RecommandSongAdapter;
import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by julbi on 2017-07-25.
 */

public class RecommandSongFragment extends Fragment implements AdapterView.OnItemClickListener {

    private Context context;
    private View rootView;
    private SearchView searchView;
    private ListView songList;
    private FloatingActionButton fab;
    private ArrayList<Song> currentSelection = new ArrayList<Song>();
    ;


    public RecommandSongFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.e("Testing", " click du search button");

                Song[] songFiltered = getSongCorrespondingTo(s);
                RecommandSongAdapter adapter = new RecommandSongAdapter(context, R.layout.recommand_song_item_row, songFiltered);
                songList.setAdapter(adapter);

                fab.setVisibility(View.VISIBLE);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.equals("")) {
                    fab.setVisibility(View.GONE);
                    songList.setAdapter(null);
                }

                return false;
            }
        });

        songList.setOnItemClickListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!currentSelection.isEmpty()){

                    new AlertDialog.Builder(context)
                            .setTitle("Confirm the selection")
                            .setMessage("Do you really want to add the selected song(s) to your recommanded songs list?\n")
                            .setIcon(R.drawable.ic_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Song[] songSelectedArray = new Song[currentSelection.size()];
                                    songSelectedArray = currentSelection.toArray(songSelectedArray);

                                    MockUpContent.getInstance().getLocalUser().addRecommandedSongs(songSelectedArray);

                                    FragmentManager fragmentManager = getFragmentManager();
                                    fragmentManager.popBackStack();

                                    Toast.makeText(context, "The selection has been added to your profile", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();

                }else{
                    Toast.makeText(context,"The selection is empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

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

        rootView = inflater.inflate(R.layout.fragment_recommand_song, container, false);
        searchView = rootView.findViewById(R.id.recommand_song_search);
        songList = rootView.findViewById(R.id.recommand_song_list);
        fab = rootView.findViewById(R.id.recommand_song_confirm_btn);

        fab.setVisibility(View.GONE);

        return rootView;


    }

    private Song[] getSongCorrespondingTo(String filter) {

        filter = filter.toLowerCase();

        ArrayList<Song> songsWeWant = new ArrayList<>();
        ArrayList<Song> songOfUniverse = new ArrayList<Song>(Arrays.asList(MockUpContent.getInstance().getAllTheSongsInTheWorld()));

        for (Song song : songOfUniverse) {
            if (song.getName().toLowerCase().contains(filter) || song.getArtist().toLowerCase().contains(filter) || song.getAlbum().toLowerCase().contains(filter)) {
                songsWeWant.add(song);
            }
        }

        Song[] songResulting = new Song[songsWeWant.size()];
        return songsWeWant.toArray(songResulting);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        FragmentUtils.activateNaviguationBack(this, true);
        FragmentUtils.changeActionBarTittle(this, "Recommend a song");

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (adapterView.getAdapter().getItem(i) != null) {
            Song songSelected = (Song) adapterView.getAdapter().getItem(i);

            if (view != null) {
                CheckBox checkBox = view.findViewById(R.id.recommand_song_item_checkbox);
                checkBox.setChecked(!checkBox.isChecked());

                if (checkBox.isChecked()) {
                    currentSelection.add(songSelected);
                    songSelected.setRecommanded(true);

                }else{

                    songSelected.setRecommanded(false);
                    currentSelection.remove(songSelected);

                }
            }
        }

    }
}
