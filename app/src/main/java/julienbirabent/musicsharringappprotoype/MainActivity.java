package julienbirabent.musicsharringappprotoype;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import julienbirabent.musicsharringappprotoype.fragments.CustomPlaylistsFragment;
import julienbirabent.musicsharringappprotoype.fragments.GeneratedPlaylistsFragment;
import julienbirabent.musicsharringappprotoype.fragments.MyProfileFragment;

public class MainActivity extends AppCompatActivity {

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

}
