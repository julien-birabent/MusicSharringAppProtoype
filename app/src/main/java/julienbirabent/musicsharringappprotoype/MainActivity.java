package julienbirabent.musicsharringappprotoype;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import julienbirabent.musicsharringappprotoype.fragments.CustomPlaylistsFragment;
import julienbirabent.musicsharringappprotoype.fragments.DisplayListContentFragment;
import julienbirabent.musicsharringappprotoype.fragments.GeneratedPlaylistsFragment;
import julienbirabent.musicsharringappprotoype.fragments.MyProfileFragment;
import julienbirabent.musicsharringappprotoype.fragments.SongDetailPageFragment;
import julienbirabent.musicsharringappprotoype.models.Playlist;
import julienbirabent.musicsharringappprotoype.models.Song;
import julienbirabent.musicsharringappprotoype.player.MusicPlayer;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private boolean isPlaying = true;

    protected Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.generated_playlist:
                    toolbar.setTitle(R.string.title_generated_playlist);

                    GeneratedPlaylistsFragment generatedPlaylistsFragment = new GeneratedPlaylistsFragment();
                    fragmentTransaction.replace(R.id.content, generatedPlaylistsFragment);
                    fragmentTransaction.commit();

                    return true;
                case R.id.custom_playlist:
                    toolbar.setTitle(R.string.title_custom_playlist);

                    CustomPlaylistsFragment customPlaylistsFragment = new CustomPlaylistsFragment();
                    fragmentTransaction.replace(R.id.content, customPlaylistsFragment);
                    fragmentTransaction.commit();

                    return true;
                case R.id.my_profile:
                    toolbar.setTitle(R.string.title_profile);

                    MyProfileFragment myProfileFragment = new MyProfileFragment();
                    fragmentTransaction.replace(R.id.content, myProfileFragment);
                    fragmentTransaction.commit();

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* init the toolbar*/
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.title_generated_playlist);

        /* Init the bottom nav bar*/
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        /* Init the first fragment */
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        GeneratedPlaylistsFragment generatedPlaylistsFragment = new GeneratedPlaylistsFragment();
        fragmentTransaction.add(R.id.content, generatedPlaylistsFragment);
        fragmentTransaction.commit();

        ImageView buttonPlay = (ImageView) findViewById(R.id.audio_player_play);
        buttonPlay.setOnClickListener(this);

        ImageView buttonNext = (ImageView) findViewById(R.id.audio_player_next);
        buttonNext.setOnClickListener(this);

        ImageView buttonPrevious = (ImageView) findViewById(R.id.audio_player_previous);
        buttonPrevious.setOnClickListener(this);

        MusicPlayer musicPlayer = MusicPlayer.getInstance();
        musicPlayer.initMusicPlayer(findViewById(R.id.audio_player_previous),findViewById(R.id.audio_player_play),
                findViewById(R.id.audio_player_next), (TextView)findViewById(R.id.audio_player_song_title),(TextView)findViewById(R.id.audio_player_artist_name)
        ,(TextView)findViewById(R.id.audio_player_album_titre),(ImageView)findViewById(R.id.thumbnail_playing_song));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        // Lorsque le click vient d une vue contenant une Playlist
        if (adapterView.getAdapter().getItem(i) instanceof Playlist) {
            Playlist playlistSelected = (Playlist) adapterView.getAdapter().getItem(i);

            /* On passe la playlist sélectionnée en argument au fragment qui va en avoir besoin*/
            Bundle args = new Bundle();
            args.putSerializable("playlist", playlistSelected);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            /* Transaction de remplacement de fragment */
            DisplayListContentFragment displayListContentFragment = new DisplayListContentFragment();
            displayListContentFragment.setArguments(args);
            fragmentTransaction.replace(R.id.content, displayListContentFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == findViewById(R.id.audio_player_play).getId()) {

            ImageView buttonPlay = (ImageView) findViewById(R.id.audio_player_play);

            if (isPlaying == false) {
                buttonPlay.setImageResource(R.drawable.ic_play_arrow);
                isPlaying = true;
            } else {
                buttonPlay.setImageResource(R.drawable.ic_pause);
                isPlaying = false;
            }
        }
        if(view.getId() == findViewById(R.id.audio_player_next).getId()){
            MusicPlayer.getInstance().nextSong();
        }
        if(view.getId() == findViewById(R.id.audio_player_previous).getId()){
            MusicPlayer.getInstance().previousSong();
        }

    }
}
