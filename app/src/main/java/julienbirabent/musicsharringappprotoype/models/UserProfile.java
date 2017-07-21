package julienbirabent.musicsharringappprotoype.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

/**
 * Created by julbi on 2017-07-21.
 */

public class UserProfile extends Observable{

    private String name;
    private int followersCount;

    private Playlist[] playlists;
    private Song[] recommandedSongs;


    public UserProfile(String name, int followersCount) {
        this.name = name;
        this.followersCount = followersCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public Playlist[] getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist[] playlists) {
        this.playlists = playlists;
    }

    public Song[] getRecommandedSongs() {
        return recommandedSongs;
    }

    public void setRecommandedSongs(Song[] recommandedSongs) {
        this.recommandedSongs = recommandedSongs;
    }

    public void addPlaylist(Playlist playlist){

        ArrayList<Playlist> playlistsList = new ArrayList<Playlist>(Arrays.asList(playlists));
        playlistsList.add(playlist);
        playlists = (Playlist[]) playlistsList.toArray(playlists);
        setChanged();
        notifyObservers(playlists);

    }

    public void deletePlaylist(Playlist playlist){

        ArrayList<Playlist> playlistsList =new ArrayList<Playlist>(Arrays.asList(playlists));
        playlistsList.remove(playlist);
        playlists = (Playlist[]) playlistsList.toArray(playlists);
        setChanged();
        notifyObservers(playlists);
    }
}
