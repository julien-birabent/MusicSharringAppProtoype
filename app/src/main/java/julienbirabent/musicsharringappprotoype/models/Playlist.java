package julienbirabent.musicsharringappprotoype.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import julienbirabent.musicsharringappprotoype.R;

/**
 * Created by Julien on 2017-07-18.
 */

public class Playlist extends Observable implements Serializable {

    private Song[] songs;
    private String name;
    private int idVignette =-1;
    private boolean generated;

    public Playlist(String name, Song[] songs) {
        this.name = name;
        this.songs = songs;
    }

    public Playlist(Song[]  songs, String name, int idVignette, boolean generated) {
        this.songs = songs;
        this.name = name;
        this.idVignette = idVignette;
        this.generated = generated;
    }

    public Song[] getSongs() {
        return songs;
    }

    public void setSongs(Song[] songs) {
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdVignette() {
        if(idVignette==-1){
            idVignette = R.drawable.default_album_vignette;
        }

        return idVignette;
    }

    public void setIdVignette(int idVignette) {
        this.idVignette = idVignette;
    }

    public int getNbOfSongs() {
        if(songs == null){
            return 0;
        }else{
            return songs.length;
        }

    }


    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }

    public int getNonListenedSongNumber(){

        int i = 0;

        for(int z = 0 ; z < songs.length ; z++){
            if (!songs[z].isListened()) {
                i++;
            }
        }
        return i;
    }

    public Song[] getRecommandedSongs(){

        ArrayList<Song> recommandedSongs = new ArrayList<Song>();

        for(int i =0 ; i<songs.length ; i++){
            if (songs[i].isRecommanded()){
                recommandedSongs.add(songs[i]);
            }
        }
        return (Song[])recommandedSongs.toArray();

    }

    public void addSong(Song song){

        ArrayList<Song> songList = new ArrayList<Song>(Arrays.asList(songs));
        songList.add(song);
        songs = new Song[songList.size()];
        songs = (Song[]) songList.toArray(songs);
        setChanged();
        notifyObservers(this);

    }

    public void addSongCollection(Song[] songsToAdd){
        ArrayList<Song> songList = new ArrayList<Song>(Arrays.asList(songs));
        songList.addAll(Arrays.asList(songsToAdd));
        songs = new Song[songList.size()];
        songs = (Song[]) songList.toArray(songs);
        setChanged();
        notifyObservers(this);
    }

    public void removeSong(Song song){

        ArrayList<Song> songList = new ArrayList<Song>(Arrays.asList(songs));
        songList.remove(song);
        songs = new Song[songList.size()];
        songs = (Song[]) songList.toArray(songs);
        setChanged();
        notifyObservers(this);

    }


}
