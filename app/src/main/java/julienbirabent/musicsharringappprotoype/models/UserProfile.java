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
    private MusicType[] musicTypePreferences;


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
        playlists = new Playlist[playlistsList.size()];
        playlists = (Playlist[]) playlistsList.toArray(playlists);
        setChanged();
        notifyObservers(playlists);

    }

    public void deletePlaylist(Playlist playlist){

        ArrayList<Playlist> playlistsList =new ArrayList<Playlist>(Arrays.asList(playlists));
        playlistsList.remove(playlist);
        playlists = new Playlist[playlistsList.size()];
        playlists = (Playlist[]) playlistsList.toArray(playlists);
        setChanged();
        notifyObservers(playlists);
    }

    public void addRecommandedSong(Song song){
        ArrayList<Song> songArrayList = new ArrayList<Song>(Arrays.asList(recommandedSongs));
        songArrayList.add(song);
        recommandedSongs = new Song[songArrayList.size()];
        recommandedSongs = (Song[]) songArrayList.toArray(recommandedSongs);
        setChanged();
        notifyObservers(recommandedSongs);
    }

    public void addRecommandedSongs(Song[] songs){
        ArrayList<Song> songArrayList = new ArrayList<Song>(Arrays.asList(recommandedSongs));
        ArrayList<Song> songToAdd = new ArrayList<Song>(Arrays.asList(songs));

        songArrayList.addAll(songToAdd);

        recommandedSongs = new Song[songArrayList.size()];
        recommandedSongs = (Song[]) songArrayList.toArray(recommandedSongs);
        setChanged();
        notifyObservers(recommandedSongs);

    }

    public void deleteRecommandedSong(Song song){
        ArrayList<Song> songArrayList = new ArrayList<Song>(Arrays.asList(recommandedSongs));
        songArrayList.remove(song);
        recommandedSongs = new Song[songArrayList.size()];
        recommandedSongs = (Song[]) songArrayList.toArray(recommandedSongs);
        setChanged();
        notifyObservers(recommandedSongs);
    }

    public MusicType[] getMusicTypePreferences() {
        return musicTypePreferences;
    }

    public void setMusicTypePreferences(MusicType[] musicTypePreferences) {
        this.musicTypePreferences = musicTypePreferences;
    }

    public Playlist[] getGeneratedPlaylistsFromPreferences() {

        ArrayList<MusicType> musicTypes = new ArrayList<MusicType>(Arrays.asList(musicTypePreferences));

        ArrayList<Playlist> playlists = new ArrayList<>();

        for(MusicType musicType : musicTypes){
            playlists.add(musicType.getPlaylistAssociated());
        }

        Playlist[] playlistsArray = new Playlist[playlists.size()];
        return playlists.toArray(playlistsArray);

    }

}
