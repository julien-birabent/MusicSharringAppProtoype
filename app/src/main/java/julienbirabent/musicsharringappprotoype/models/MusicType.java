package julienbirabent.musicsharringappprotoype.models;

/**
 * Created by julbi on 2017-07-24.
 */

public class MusicType {

    private Playlist playlistAssociated;
    private String name;
    private boolean desired = false;

    public MusicType(Playlist playlistAssociated, String name, boolean desired) {
        this.playlistAssociated = playlistAssociated;
        this.name = name;
        this.desired = desired;
    }

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

    public boolean isDesired() {
        return desired;
    }

    public void setDesired(boolean desired) {
        this.desired = desired;
    }
}
