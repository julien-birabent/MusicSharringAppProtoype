package julienbirabent.musicsharringappprotoype.models;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

import julienbirabent.musicsharringappprotoype.R;

/**
 * Created by Julien on 2017-07-18.
 */

public class Song implements Serializable {

    private String artist;
    private String album;
    private String name;
    private int albumVignetteId = -1;
    private ArrayList<String> userRecommandations;


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
            formatted = "Recommanded by no one at the moment. Be the first !";
        }else{
            int restOfRecommandation =  userRecommandations.size()-2;
            formatted = "Recommanded by "+ userRecommandations.get(0) +", " + userRecommandations.get(1) + " and " + restOfRecommandation + " more people.";
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
}
