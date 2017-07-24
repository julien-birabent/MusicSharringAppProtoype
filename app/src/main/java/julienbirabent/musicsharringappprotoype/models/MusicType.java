package julienbirabent.musicsharringappprotoype.models;

/**
 * Created by julbi on 2017-07-24.
 */

public class MusicType {

    private Playlist playlistAssociated;
    private String name;

    public MusicType(Playlist playlistAssociated, String name) {
        this.playlistAssociated = playlistAssociated;
        this.name = name;
    }

    public MusicType(String name) {
        this.name = name;
    }

    public MusicType() {
    }

    public Playlist getPlaylistAssociated() {
        return playlistAssociated;
    }

    public void setPlaylistAssociated(Playlist playlistAssociated) {
        this.playlistAssociated = playlistAssociated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
