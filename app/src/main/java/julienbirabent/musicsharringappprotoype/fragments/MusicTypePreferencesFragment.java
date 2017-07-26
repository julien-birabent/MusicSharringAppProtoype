package julienbirabent.musicsharringappprotoype.fragments;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import julienbirabent.musicsharringappprotoype.FragmentUtils;

import julienbirabent.musicsharringappprotoype.MockUpContent;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.adapter.MusicTypePreferencesAdapter;
import julienbirabent.musicsharringappprotoype.models.MusicType;
import julienbirabent.musicsharringappprotoype.util.MusicTypePair;

/**
 * Created by julbi on 2017-07-24.
 */

public class MusicTypePreferencesFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnTouchListener {

    private View rootView;
    private Context context;
    private int touchPositionX;
    private ListView musicTypeList;
    private Button confirmBtn;

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
        musicTypeList = (ListView) rootView.findViewById(R.id.music_type_preferences_listView);
        confirmBtn = (Button) rootView.findViewById(R.id.music_type_preferences_confirmation);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(context)
                        .setTitle("Confirm the selection")
                        .setMessage("Once you have confirmed your selection you will have access to the generate playlists of your choice.")
                        .setIcon(R.drawable.ic_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                ArrayList<MusicType> musicTypesSelected = new ArrayList<MusicType>();

                                ArrayList<MusicType> allMusicTypes = new ArrayList<MusicType>(Arrays.asList(MockUpContent.getInstance().getAllMusicTypes()));

                                for(MusicType musicType : allMusicTypes){
                                    if(musicType.isDesired()){
                                        musicTypesSelected.add(musicType);
                                    }
                                }

                                MusicType[] convertedMusicTypes = new MusicType[musicTypesSelected.size()];
                                MockUpContent.getInstance().getLocalUser().setMusicTypePreferences(musicTypesSelected.toArray(convertedMusicTypes));

                                FragmentManager fragmentManager = getFragmentManager();
                                fragmentManager.popBackStack();

                                Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
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

        MusicTypePair[] musicTypes = getMusicTypeForAdapter(MockUpContent.getInstance().getAllMusicTypes());
        MusicTypePreferencesAdapter adapter = new MusicTypePreferencesAdapter(context, R.layout.music_type_selection_row, musicTypes);
        musicTypeList.setAdapter(adapter);

        musicTypeList.setOnTouchListener(this);
        musicTypeList.setOnItemClickListener(this);

    }

    private MusicTypePair[] getMusicTypeForAdapter(MusicType[] musicTypesArray) {

        ArrayList<MusicTypePair> musicTypePairs = new ArrayList<>();
        ArrayList<MusicType> musicTypes = new ArrayList<MusicType>(Arrays.asList(musicTypesArray));

        for (int i = 0; i < musicTypes.size() / 2; i = i + 2) {

            musicTypePairs.add(new MusicTypePair(musicTypesArray[i], musicTypesArray[i + 1]));
        }

        MusicTypePair[] musicTypePairsArray = new MusicTypePair[musicTypePairs.size()];
        return musicTypePairs.toArray(musicTypePairsArray);

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

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        touchPositionX = (int)motionEvent.getX();
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        MusicTypePair  musicTypePair =(MusicTypePair) adapterView.getAdapter().getItem(i);

       // Log.e("Testing", "X position of touch = " +touchPositionX +"\n Width dimension = " + adapterView.getWidth());

        CheckBox checkBoxSelected;

        if(touchPositionX < adapterView.getWidth()/2){
            checkBoxSelected=(CheckBox) view.findViewById(R.id.music_type_preferences_left_checkbox);
            checkBoxSelected.setChecked(!checkBoxSelected.isChecked());
            musicTypePair.getLeft().setDesired(!musicTypePair.getLeft().isDesired());
        }else{
            checkBoxSelected=(CheckBox) view.findViewById(R.id.music_type_preferences_right_checkbox);
            checkBoxSelected.setChecked(!checkBoxSelected.isChecked());
            if(musicTypePair.getRight() != null){
                musicTypePair.getRight().setDesired(!musicTypePair.getRight().isDesired());
            }
        }

        Log.e("Testing",musicTypePair.getLeft().getName()+  ", " + musicTypePair.getLeft().isDesired());
        Log.e("Testing",musicTypePair.getRight().getName()+  ", " + musicTypePair.getRight().isDesired());
    }
}
