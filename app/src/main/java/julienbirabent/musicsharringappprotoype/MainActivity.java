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
import android.widget.Toast;

import julienbirabent.musicsharringappprotoype.fragments.CustomPlaylistsFragment;
import julienbirabent.musicsharringappprotoype.fragments.DisplayListContentFragment;
import julienbirabent.musicsharringappprotoype.fragments.GeneratedPlaylistsFragment;
import julienbirabent.musicsharringappprotoype.fragments.MyProfileFragment;
import julienbirabent.musicsharringappprotoype.fragments.SongDetailPageFragment;
import julienbirabent.musicsharringappprotoype.models.Playlist;
import julienbirabent.musicsharringappprotoype.models.Song;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{



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
        toolbar =(Toolbar) findViewById(R.id.toolbar);
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

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        // Lorsque le click vient d une vue contenant une Playlist
        if(adapterView.getAdapter().getItem(i) instanceof Playlist){
            Playlist playlistSelected =(Playlist) adapterView.getAdapter().getItem(i);

            /* On passe la playlist sélectionnée en argument au fragment qui va en avoir besoin*/
            Bundle args = new Bundle();
            args.putSerializable("playlist", playlistSelected);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            /* Transaction de remplacement de fragment */
            DisplayListContentFragment displayListContentFragment =  new DisplayListContentFragment();
            displayListContentFragment.setArguments(args);
            fragmentTransaction.replace(R.id.content, displayListContentFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            // Lorsque le click vient d une vue contenant une Song
        }else if(adapterView.getAdapter().getItem(i) instanceof Song) {

            Song song = (Song) adapterView.getAdapter().getItem(i);
             /* On passe la playlist sélectionnée en argument au fragment qui va en avoir besoin*/
            Bundle args = new Bundle();
            args.putSerializable("song", song);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            /* Transaction de remplacement de fragment */
            SongDetailPageFragment songDetailPageFragment =  new SongDetailPageFragment();
            songDetailPageFragment.setArguments(args);
            fragmentTransaction.replace(R.id.content, songDetailPageFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
    }

}
