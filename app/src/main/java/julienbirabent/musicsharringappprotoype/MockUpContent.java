package julienbirabent.musicsharringappprotoype;

import java.util.ArrayList;
import java.util.Arrays;

import julienbirabent.musicsharringappprotoype.models.MusicType;
import julienbirabent.musicsharringappprotoype.models.Playlist;
import julienbirabent.musicsharringappprotoype.models.Song;
import julienbirabent.musicsharringappprotoype.models.UserProfile;

/**
 * Created by Julien on 2017-07-18.
 */

public class MockUpContent {

    private static MockUpContent instance;

    private static Playlist[] generatedPlaylists;
    private static Playlist[] customPlaylists;
    private static Song[] songs;
    private static Song[] recommandedSongs = new Song[0];
    private static UserProfile localUser;
    private static MusicType[] musicTypePreferences;


    private MockUpContent() {
        initRandomSongs();
        initMusicTypePreference();
        initCustomPlaylists();
        initLocalUser();
    }

    public static MockUpContent getInstance() {
        if (instance == null) {
            // Create the instance
            instance = new MockUpContent();
        }
        return instance;
    }

    private void initLocalUser() {

        localUser = new UserProfile("Julien Birabent", 5);
        localUser.setPlaylists(customPlaylists);
        localUser.setRecommandedSongs(recommandedSongs);
    }

    private void initMusicTypePreference() {
        musicTypePreferences = new MusicType[]{
                new MusicType(new Playlist(songs, "Jazz", R.drawable.ic_jazz_music, true), "Jazz"),
                new MusicType(new Playlist(songs, "Metal", R.drawable.ic_metal_music, true), "Metal"),
                new MusicType(new Playlist(songs, "Country", R.drawable.ic_country_music, true), "Country"),
                new MusicType(new Playlist(songs, "Summer Mix", R.drawable.ic_sun, true), "Summer Mix"),
                new MusicType(new Playlist(songs, "Gospel", R.drawable.ic_gospel_music, true), "Gospel"),
                new MusicType(new Playlist(songs, "The 60's", R.drawable.ic_60s, true), "The 60's"),
        };
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

    private void initCustomPlaylists() {

        customPlaylists = new Playlist[]
                {
                        new Playlist(songs, "Go to sleep", R.drawable.ic_sleep, false),
                        new Playlist(songs, "Workout", R.drawable.ic_workout , false),
                        new Playlist(songs, "Transport", R.drawable.ic_transport, false),
                };


    }

    private void initRandomSongs() {

        ArrayList<String> userRecommandations = new ArrayList<String>();
        userRecommandations.add("Alex Martin");
        userRecommandations.add("Marc-Antoine Roberge");
        userRecommandations.add("Justin Trudeau");
        userRecommandations.add("President Obama");

        songs = new Song[]{

                new Song("Gorrilaz", "Plastic Beach", "Broken", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Cloud of unknowing", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "White flag", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Empire Ants", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Stylo", R.drawable.plastic_beach_1000_1000, userRecommandations),
                // Test l affichage de la vignette d album par défaut
                new Song("Muse", "Origin of Symmetry", "Bliss", userRecommandations),
        };
    }

    public static Playlist[] getGeneratedPlaylistsMockUp() {
        return generatedPlaylists;
    }

    public static Playlist[] getCustomPlaylistsMockUp() {
        return customPlaylists;
    }

    public static UserProfile getLocalUser() {
        return localUser;
    }

    public static void setLocalUser(UserProfile localUser) {
        MockUpContent.localUser = localUser;
    }
}
