package julienbirabent.musicsharringappprotoype.player;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import julienbirabent.musicsharringappprotoype.R;
import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by Julien on 2017-07-20.
 */

public class MusicPlayer implements View.OnClickListener {

    private ImageButton previousButton;
    private ImageButton pauseOrPlayButton;
    private ImageButton nextButton;

    private TextView songTitle;
    private TextView songArtist;
    private TextView songAlbum;
    private ImageView songVignette;

    private ArrayList<Song> songQueue = new ArrayList<Song>();
    private int queueIndex = 0;

    private boolean isPlaying = true;

    private static MusicPlayer instance;


    private MusicPlayer() {

    }

    public static MusicPlayer getInstance()
    {
        if (instance == null)
        {
            // Create the instance
            instance = new MusicPlayer();
        }
        return instance;
    }

    public void initMusicPlayer(ImageButton  previousButton, ImageButton pauseOrPlayButton, ImageButton nextButton, TextView songTitle, TextView songArtist, TextView songAlbum, ImageView songVignette) {
        this.previousButton = previousButton;
        this.pauseOrPlayButton = pauseOrPlayButton;
        this.nextButton = nextButton;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songAlbum = songAlbum;
        this.songVignette = songVignette;

        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
        pauseOrPlayButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == pauseOrPlayButton) {

            if(!songQueue.isEmpty()){
                if (!isPlaying) {
                    pauseOrPlayButton.setImageResource(R.drawable.ic_play_arrow);
                    isPlaying = true;
                } else {
                    pauseOrPlayButton.setImageResource(R.drawable.ic_pause);
                    isPlaying = false;
                }
            }


        }
        if(view == nextButton){
            MusicPlayer.getInstance().nextSong();
        }
        if(view == previousButton){
            MusicPlayer.getInstance().previousSong();
        }

    }

    public void nextSong(Song song){

        if(songQueue.isEmpty()){
            displaySongInfos(song);
            songQueue.add(song);
        }else{
            if(!song.equals(songQueue.get(queueIndex))){
                displaySongInfos(song);
                songQueue.add(song);
                queueIndex++;
            }
        }
    }

    public void nextSong(){
        if(queueIndex+1 < songQueue.size()){
            displaySongInfos(songQueue.get(queueIndex+1));
            queueIndex ++;
        }

    }

    public  void previousSong(){
        if(queueIndex -1 >= 0){
            displaySongInfos(songQueue.get(queueIndex-1));
            queueIndex--;
        }

    }

    private void displaySongInfos(Song song){
        songTitle.setText(song.getName());
        songAlbum.setText(song.getAlbum());
        songArtist.setText(song.getArtist());
        songVignette.setImageResource(song.getAlbumVignetteId());
    }
    @Nullable
    public Song getCurrentPlayingSong(){
        if(songQueue.isEmpty()){
            return  null;
        }else{
            return songQueue.get(queueIndex);
        }

    }


}
