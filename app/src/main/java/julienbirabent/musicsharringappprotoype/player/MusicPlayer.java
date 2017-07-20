package julienbirabent.musicsharringappprotoype.player;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import julienbirabent.musicsharringappprotoype.models.Song;

/**
 * Created by Julien on 2017-07-20.
 */

public class MusicPlayer {

    private View previousButton;
    private View pauseOrPlayButton;
    private View nextButton;

    private TextView songTitle;
    private TextView songArtist;
    private TextView songAlbum;
    private ImageView songVignette;

    private ArrayList<Song> songQueue = new ArrayList<Song>();
    private int queueIndex = 0;

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

    public void initMusicPlayer(View previousButton, View pauseOrPlayButton, View nextButton, TextView songTitle, TextView songArtist, TextView songAlbum, ImageView songVignette) {
        this.previousButton = previousButton;
        this.pauseOrPlayButton = pauseOrPlayButton;
        this.nextButton = nextButton;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songAlbum = songAlbum;
        this.songVignette = songVignette;
    }

    public void nextSong(Song song){

            displaySongInfos(song);
            songQueue.add(song);
            queueIndex = songQueue.indexOf(song);

    }

    public void nextSong(){
        if(queueIndex+1 < songQueue.size()){
            displaySongInfos(songQueue.get(queueIndex+1));
            queueIndex = songQueue.indexOf(songQueue.get(queueIndex+1));
        }

    }

    public  void previousSong(){
        if(queueIndex -1 >= 0){
            displaySongInfos(songQueue.get(queueIndex-1));
            queueIndex = songQueue.indexOf(songQueue.get(queueIndex-1));
        }

    }

    private void displaySongInfos(Song song){
        songTitle.setText(song.getName());
        songAlbum.setText(song.getAlbum());
        songArtist.setText(song.getArtist());
        songVignette.setImageResource(song.getAlbumVignetteId());
    }

    public View getPreviousButton() {
        return previousButton;
    }

    public void setPreviousButton(View previousButton) {
        this.previousButton = previousButton;
    }

    public View getPauseOrPlayButton() {
        return pauseOrPlayButton;
    }

    public void setPauseOrPlayButton(View pauseOrPlayButton) {
        this.pauseOrPlayButton = pauseOrPlayButton;
    }

    public View getNextButton() {
        return nextButton;
    }

    public void setNextButton(View nextButton) {
        this.nextButton = nextButton;
    }

    public TextView getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(TextView songTitle) {
        this.songTitle = songTitle;
    }

    public TextView getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(TextView songArtist) {
        this.songArtist = songArtist;
    }

    public TextView getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(TextView songAlbum) {
        this.songAlbum = songAlbum;
    }

    public ImageView getSongVignette() {
        return songVignette;
    }

    public void setSongVignette(ImageView songVignette) {
        this.songVignette = songVignette;
    }

    public ArrayList<Song> getSongQueue() {
        return songQueue;
    }

    public void setSongQueue(ArrayList<Song> songQueue) {
        this.songQueue = songQueue;
    }


}
