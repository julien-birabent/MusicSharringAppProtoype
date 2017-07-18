package julienbirabent.musicsharringappprotoype.models;

import java.util.ArrayList;

/**
 * Created by Julien on 2017-07-18.
 */

public class Playlist {

    private Song[] songs;
    private String name;
    private int idVignette;
    private int nbOfSongs;
    private boolean generated;

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
        return nbOfSongs;
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
}
