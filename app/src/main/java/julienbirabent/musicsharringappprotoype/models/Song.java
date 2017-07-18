package julienbirabent.musicsharringappprotoype.models;

import java.io.Serializable;

/**
 * Created by Julien on 2017-07-18.
 */

public class Song implements Serializable {

    private String artist;
    private String album;
    private String name;


    public Song(String artist, String album, String name) {
        this.artist = artist;
        this.album = album;
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
