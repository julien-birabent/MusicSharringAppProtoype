package julienbirabent.musicsharringappprotoype.models;

import java.util.ArrayList;

/**
 * Created by Julien on 2017-07-18.
 */

public class Playlist {

    private ArrayList<Song> songs;
    private String name;
    private int idVignette;

    public Playlist(ArrayList<Song> songs, String name, int idVignette) {
        this.songs = songs;
        this.name = name;
        this.idVignette = idVignette;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
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
}
