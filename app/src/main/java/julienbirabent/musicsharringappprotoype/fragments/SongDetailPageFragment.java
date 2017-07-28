package julienbirabent.musicsharringappprotoype.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.TextView;

import julienbirabent.musicsharringappprotoype.FragmentUtils;
import julienbirabent.musicsharringappprotoype.MockUpContent;
import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.models.Comment;
import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by Julien on 2017-07-19.
 */

public class SongDetailPageFragment extends Fragment {

    private Song song;
    private ListView commentList;
    private View rootView;
    private Context context;
    private ViewGroup container;
    private LinearLayout commentsContainer;
    private Button viewAllAction;

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

        rootView = inflater.inflate(R.layout.song_detail_page_fragment, container, false);
        commentsContainer = rootView.findViewById(R.id.song_detail_page_comments_container);
        viewAllAction = rootView.findViewById(R.id.song_page_view_all_recommandations_action);

        viewAllAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ShowUserFragment showUserFragment = new ShowUserFragment();

                Bundle args = new Bundle();
                args.putSerializable("song", song);
                showUserFragment.setArguments(args);

                fragmentTransaction.replace(R.id.content, showUserFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        this.container = container;



        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        FragmentUtils.activateNaviguationBack(this, true);
        FragmentUtils.changeActionBarTittle(this, getString(R.string.title_song_page));

        Bundle args = getArguments();
        song = (Song) args.getSerializable("song");

        if (song != null) {
            displaySongDetails(song);
            appendComments();
        }


    }

    private void appendComments() {

        for(Comment comment : song.getComments()){
            View commentView = new View(context);
            commentView = LayoutInflater.from(context).inflate(R.layout.simple_title_body_row, container, false);
            TextView title = (TextView) commentView.findViewById(R.id.title_row);
            TextView body = (TextView) commentView.findViewById(R.id.body_row);

            title.setText(comment.getCommentOwner().getName());
            body.setText(comment.getBody());

            commentsContainer.addView(commentView);
        }

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

        commentsContainer.removeAllViews();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.song_page_menu, menu);

        MenuItem itemRecommand = menu.findItem(R.id.recommande_song);
        if (itemRecommand.getItemId() == R.id.recommande_song) {
            if (song.isRecommanded()) {
                itemRecommand.setTitle(R.string.popmenu_unrecommand_this_song);
                itemRecommand.setChecked(true);
            } else {
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
            case R.id.recommande_song:
                if (!item.isChecked()) {
                    recommandASong();
                    item.setTitle(R.string.popmenu_unrecommand_this_song);
                    item.setChecked(true);
                } else {
                    unrecommandeSong();
                    item.setTitle(R.string.popmenu_recommand_this_song);
                    item.setChecked(false);
                }


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void unrecommandeSong() {
        song.setRecommanded(false);
        MockUpContent.getInstance().getLocalUser().deleteRecommandedSong(song);

    }

    private void recommandASong() {
        song.setRecommanded(true);
        MockUpContent.getInstance().getLocalUser().addRecommandedSong(song);
    }
}
