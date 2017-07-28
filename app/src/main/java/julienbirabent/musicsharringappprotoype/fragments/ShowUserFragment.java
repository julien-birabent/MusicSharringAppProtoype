package julienbirabent.musicsharringappprotoype.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import julienbirabent.musicsharringappprotoype.FragmentUtils;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.models.Song;
import julienbirabent.musicsharringappprotoype.models.UserProfile;

/**
 * Created by julbi on 2017-07-28.
 */

public class ShowUserFragment extends Fragment {

    private Context context;
    private View rootView;
    private Song song;
    private LinearLayout userList;
    private ViewGroup container;
    private ArrayList<UserProfile> users;


    public ShowUserFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        this.container = container;

        rootView = inflater.inflate(R.layout.show_user_fragment, container, false);
        userList =(LinearLayout) rootView;

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        FragmentUtils.activateNaviguationBack(this,true);
        FragmentUtils.changeActionBarTittle(this, "Recommanded by");
    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle args = getArguments();
        song = (Song)args.getSerializable("song");
        int i = 0;
        users = song.getWhoRecommanded();

        for(final UserProfile user : users){

            View userView = new View(context);
            userView = LayoutInflater.from(context).inflate(R.layout.show_user_row, container, false);

            ImageView photo = (ImageView) userView.findViewById(R.id.user_photo);
            TextView name = (TextView) userView.findViewById(R.id.user_name);
            TextView followercount = (TextView) userView.findViewById(R.id.user_followers_count);

            photo.setImageResource(R.drawable.ic_profile);
            name.setText(user.getName());
            followercount.setText(user.getFollowersCount() + " followers");

            userView.setId(i);
            i++;

            userView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserProfile userSelected;

                    userSelected = users.get(view.getId());

                    Log.e("Lala", userSelected.getName());

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ProfileFragment profileFragment = new ProfileFragment();

                    Bundle args = new Bundle();
                    args.putSerializable("user", userSelected);
                    profileFragment.setArguments(args);

                    fragmentTransaction.replace(R.id.content, profileFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
            });

            userList.addView(userView);

        }


    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home :
                getFragmentManager().popBackStack();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
