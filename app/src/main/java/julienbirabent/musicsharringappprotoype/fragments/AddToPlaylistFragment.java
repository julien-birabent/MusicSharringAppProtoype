package julienbirabent.musicsharringappprotoype.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import julienbirabent.musicsharringappprotoype.FragmentUtils;
import julienbirabent.musicsharringappprotoype.MockUpContent;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.adapter.AddToPlaylistAdapter;
import julienbirabent.musicsharringappprotoype.listeners.PlaylistActionManager;
import julienbirabent.musicsharringappprotoype.models.Playlist;
import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by julbi on 2017-07-22.
 */

public class AddToPlaylistFragment extends Fragment {

    private View rootView;
    private ListView playlistListView;
    private Context context;
    private Playlist contentToAdd;
    private ArrayList<Playlist> playlistsSelected;
    private Button confirmBtn;

    public AddToPlaylistFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playlistsSelected = new ArrayList<Playlist>();
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

        rootView = inflater.inflate(R.layout.fragment_add_to_playlist, container, false);
        playlistListView = (ListView) rootView.findViewById(R.id.add_to_playlist_listView);
        confirmBtn = (Button) rootView.findViewById(R.id.aadd_to_playlist_confirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String selectedPlaylistFormatted = "\n";

                for (Playlist playlist : playlistsSelected) {
                    selectedPlaylistFormatted += ". "+playlist.getName() + "\n";
                }

                new AlertDialog.Builder(context)
                        .setTitle("Confirm the selection")
                        .setMessage("Do you really want to add the song(s) to the selected playlist(s)?\n" + selectedPlaylistFormatted)
                        .setIcon(R.drawable.ic_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                for (Playlist playlist : playlistsSelected) {
                                    playlist.addSongCollection(contentToAdd.getSongs());
                                }
                                FragmentManager fragmentManager = getFragmentManager();
                                fragmentManager.popBackStack();

                                Toast.makeText(context, "Success !", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();


            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle args = getArguments();
        contentToAdd = (Playlist) args.getSerializable("playlist");

        AddToPlaylistAdapter adapter = new AddToPlaylistAdapter(context, R.layout.add_to_playlist_row, MockUpContent.getLocalUser().getPlaylists());
        playlistListView.setAdapter(adapter);
        playlistListView.setOnItemClickListener(new PlaylistActionManager(context, playlistsSelected));

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        FragmentUtils.activateNaviguationBack(this, true);
        FragmentUtils.changeActionBarTittle(this, "Add song(s) to " + contentToAdd.getName());
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
