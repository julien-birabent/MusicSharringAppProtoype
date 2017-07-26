package julienbirabent.musicsharringappprotoype.models;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.StringTokenizer;

import julienbirabent.musicsharringappprotoype.R;

/**
 * Created by Julien on 2017-07-18.
 */

public class Song extends Observable implements Serializable  {

    private String artist;
    private String album;
    private String name;
    private int albumVignetteId = -1;
    private ArrayList<String> userRecommandations;
    private boolean listened = false;
    private boolean recommanded = false;
    private ArrayList<Comment> comments = new ArrayList<>();


    public Song(String artist, String album, String name) {
        this.artist = artist;
        this.album = album;
        this.name = name;
    }

    public Song(String artist, String album, String name, ArrayList<String> userRecommandations) {
        this.artist = artist;
        this.album = album;
        this.name = name;
        this.userRecommandations = userRecommandations;
    }

    public Song(String artist, String album, String name,  int albumVignetteId, ArrayList<String> userRecommandations) {
        this.artist = artist;
        this.album = album;
        this.name = name;
        this.albumVignetteId = albumVignetteId;
        this.userRecommandations = userRecommandations;
    }

    public Song(String artist, String album, String name,  int albumVignetteId, ArrayList<String> userRecommandations, ArrayList<Comment> comments) {
        this.artist = artist;
        this.album = album;
        this.name = name;
        this.albumVignetteId = albumVignetteId;
        this.userRecommandations = userRecommandations;
        this.comments = comments;
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

    public String getUserRecommandationsFormatted(){

        String formatted;

        if(userRecommandations.size() == 0){
            formatted = "Recommended by no one at the moment. Be the first!";
        }else{
            int restOfRecommandation =  userRecommandations.size()-2;
            formatted = "Recommended by "+ userRecommandations.get(0) +", " + userRecommandations.get(1) + " and " + restOfRecommandation + " more people";
        }

        return formatted;

    }

    public int getAlbumVignetteId() {
        if(albumVignetteId == -1){
            albumVignetteId = R.drawable.default_album_vignette;
        }
        return albumVignetteId;
    }

    public void setAlbumVignetteId(int albumVignetteId) {
        this.albumVignetteId = albumVignetteId;
    }

    public ArrayList<String> getUserRecommandations() {
        return userRecommandations;
    }

    public void setUserRecommandations(ArrayList<String> userRecommandations) {
        this.userRecommandations = userRecommandations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (artist != null ? !artist.equals(song.artist) : song.artist != null) return false;
        if (album != null ? !album.equals(song.album) : song.album != null) return false;
        return name != null ? name.equals(song.name) : song.name == null;

    }

    @Override
    public int hashCode() {
        int result = artist != null ? artist.hashCode() : 0;
        result = 31 * result + (album != null ? album.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public boolean isListened() {
        return listened;
    }

    public void setListened(boolean listened) {
        this.listened = listened;
    }

    public boolean isRecommanded() {
        return recommanded;
    }

    public void setRecommanded(boolean recommanded) {
        this.recommanded = recommanded;
        setChanged();
        notifyObservers(this);
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
