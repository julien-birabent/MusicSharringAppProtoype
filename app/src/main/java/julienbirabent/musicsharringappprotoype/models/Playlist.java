package julienbirabent.musicsharringappprotoype.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Julien on 2017-07-18.
 */

public class Playlist extends Observable implements Serializable {

    private Song[] songs;
    private String name;
    private int idVignette;
    private int nbOfSongs;
    private boolean generated;

    public Playlist(String name, Song[] songs) {
        this.name = name;
        this.songs = songs;
    }

    public Playlist(Song[]  songs, String name, int idVignette, int nbOfSongs, boolean generated) {
        this.songs = songs;
        this.name = name;
        this.idVignette = idVignette;
        this.nbOfSongs = nbOfSongs;
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

    public void setNbOfSongs(int nbOfSongs) {
        this.nbOfSongs = nbOfSongs;
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


}
