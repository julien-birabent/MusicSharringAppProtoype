package julienbirabent.musicsharringappprotoype.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Julien on 2017-07-18.
 */

public class Song implements Serializable {

    private String artist;
    private String album;
    private String name;
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


}
