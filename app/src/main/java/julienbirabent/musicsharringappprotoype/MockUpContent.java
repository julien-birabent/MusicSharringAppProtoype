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
    private static Song[] allTheSongsInTheWorld;
    private static Song[] recommandedSongs = new Song[0];
    private static UserProfile localUser;
    private static MusicType[] musicTypePreferences;
    private static MusicType[] allMusicTypes;

    private static String[] musicTypeMockupContent = {"Heavy Metal", "Rock n' Roll", "Hard Rock", "Alternative", "Classical", "Punk Rock", "Pop", "Rap", "Blues", "Dirty Rap", "Thrash Metal", "Electronic", "Grunge", "Progressive Rock", "R&B", "Pop Rock", "Psychedelic Rock", "Hip Hop", "Soul", "Alternative Metal", "Reggae", "Nu Metal", "Funk", "Folk", "Disco", "Progressive Metal", "Blues Rock", "Alternative Rock", "Death Metal", "Melodic Death Metal", "Groove Metal", "Rap Rock", "Emo", "Power Metal", "Trance ", "Black Metal", "Metalcore", "Metal", "New Wave", "Pop Punk", "Indie Rock", "Piano Rock", "Dubstep", "Glam Rock", "House", "Swing Music", "Classic Country", "Art Rock", "Punk", "Opera", "Dance", "Melodic Metal", "Trap", "Rap Metal", "Folk Metal", "K Pop", "Chiptune", "Electronic Rock", "Traditional Heavy Metal", "Industrial Rock", "Experimental Rock", "Synthwave", "Neo-classical Metal", "Synthpop", "Darkwave", "Industrial Metal", "Soundtrack", "50s Doo-wop", "West Coast Hip Hop", "Classic R&B", "Drum and Bass", "Electronic Dance Music", "Symphonic Metal", "Chillstep", "Christian", "Instrumental Hip Hop", "Video Game", "Folk rock", "Russian Folk", "Comedy Rock", "East Coast Hip Hop", "Techno", "J Pop", "Nightcore", "Ska", "Neoclassical Darkwave", "Country Pop", "Contemporary Country", "Electro Rock", "Electrorap", "Post-Hardcore", "Indie Pop", "Contemporary Christian", "Gangsta Rap", "Gothic Rock", "Rockabilly", "Alternative Hip Hop", "Stoner Rock", "Country Rock"};


    private MockUpContent() {
        initRandomSongs();
        initAllTheSongsInTheWorld();
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
        localUser.setMusicTypePreferences(musicTypePreferences);
    }

    private void initAllTheSongsInTheWorld(){

        ArrayList<String> userRecommandations = getSomeUserReommandations();

        allTheSongsInTheWorld = new Song[]{

                new Song("Gorrilaz", "Plastic Beach", "Broken", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Cloud of unknowing", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "White flag", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Empire Ants", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Stylo", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Orchestral intro", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Welcome To the World Of The Plastic Beach", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Rhinestones Eyes", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Stylo", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Superfast Jellyfish", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Glitter Freeze", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "some Kind Of Nature", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "On Melamcholy Hill", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Sweeptakes", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Plastic Beach", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "To Binge", R.drawable.plastic_beach_1000_1000, userRecommandations),
                new Song("Gorrilaz", "Plastic Beach", "Pirate Jet", R.drawable.plastic_beach_1000_1000, userRecommandations),

                new Song("Gorrilaz", "Demon Days", "Intro", R.drawable.gorillaz_demondays, userRecommandations),
                new Song("Gorrilaz", "Demon Days", "Last Living Souls", R.drawable.gorillaz_demondays, userRecommandations),
                new Song("Gorrilaz", "Demon Days", "Kids With Guns", R.drawable.gorillaz_demondays, userRecommandations),
                new Song("Gorrilaz", "Demon Days", "O Green World", R.drawable.gorillaz_demondays, userRecommandations),
                new Song("Gorrilaz", "Demon Days", "Dirty Harry", R.drawable.gorillaz_demondays, userRecommandations),
                new Song("Gorrilaz", "Demon Days", "Feel Good Inc.", R.drawable.gorillaz_demondays, userRecommandations),
                new Song("Gorrilaz", "Demon Days", "El Manana", R.drawable.gorillaz_demondays, userRecommandations),
                new Song("Gorrilaz", "Demon Days", "Every Planet We Reach Is Dead", R.drawable.gorillaz_demondays, userRecommandations),
                new Song("Gorrilaz", "Demon Days", "November has come", R.drawable.gorillaz_demondays, userRecommandations),
                new Song("Gorrilaz", "Demon Days", "White Light", R.drawable.gorillaz_demondays, userRecommandations),
                new Song("Gorrilaz", "Demon Days", "Dare", R.drawable.gorillaz_demondays, userRecommandations),
                new Song("Gorrilaz", "Demon Days", "Fire Coming Out Of The Monkey's Head", R.drawable.gorillaz_demondays, userRecommandations),
                new Song("Gorrilaz", "Demon Days", "Don't Get Lost In Heaven", R.drawable.gorillaz_demondays, userRecommandations),
                new Song("Gorrilaz", "Demon Days", "Demon Days", R.drawable.gorillaz_demondays, userRecommandations),

        };

    }

    private void initMusicTypePreference() {

        // Adding the content the fake user initially wants
        musicTypePreferences = new MusicType[]{
                new MusicType(new Playlist(songs, "Jazz", R.drawable.ic_jazz_music, true),"Jazz",true),
                new MusicType(new Playlist(songs, "Metal", R.drawable.ic_metal_music, true), "Metal",true),
                new MusicType(new Playlist(songs, "Country", R.drawable.ic_country_music, true), "Country",true),
                new MusicType(new Playlist(songs, "Summer Mix", R.drawable.ic_sun, true), "Summer Mix",true),
                new MusicType(new Playlist(songs, "Gospel", R.drawable.ic_gospel_music, true), "Gospel",true),
                new MusicType(new Playlist(songs, "The 60's", R.drawable.ic_60s, true), "The 60's",true),
        };

        ArrayList<MusicType> musicTypesExtra = new ArrayList<>(Arrays.asList(musicTypePreferences));


        for(int i = 0 ; i <musicTypeMockupContent.length ; i++ ){
            musicTypesExtra.add(new MusicType(new Playlist(songs, musicTypeMockupContent[i], R.drawable.default_album_vignette, true),musicTypeMockupContent[i],false));
        }
        allMusicTypes = new MusicType[musicTypesExtra.size()];
        allMusicTypes = musicTypesExtra.toArray(allMusicTypes);
    }



    private void initCustomPlaylists() {

        customPlaylists = new Playlist[]
                {
                        new Playlist(songs, "Go to sleep", R.drawable.ic_sleep, false),
                        new Playlist(songs, "Workout", R.drawable.ic_workout , false),
                        new Playlist(songs, "Transport", R.drawable.ic_transport, false),
                };


    }

    private ArrayList<String> getSomeUserReommandations(){
        ArrayList<String> userRecommandations = new ArrayList<String>();
        userRecommandations.add("Alex Martin");
        userRecommandations.add("Marc-Antoine Roberge");
        userRecommandations.add("Justin Trudeau");
        userRecommandations.add("President Obama");
        return userRecommandations;
    }

    private void initRandomSongs() {

        ArrayList<String> userRecommandations =  getSomeUserReommandations();

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

    public  UserProfile getLocalUser() {
        return localUser;
    }

    public static void setLocalUser(UserProfile localUser) {
        MockUpContent.localUser = localUser;
    }

    public  MusicType[] getAllMusicTypes() {
        return allMusicTypes;
    }

    public  void setAllMusicTypes(MusicType[] allMusicTypes) {
        MockUpContent.allMusicTypes = allMusicTypes;
    }

    public  Song[] getAllTheSongsInTheWorld() {
        return allTheSongsInTheWorld;
    }

    public  void setAllTheSongsInTheWorld(Song[] allTheSongsInTheWorld) {
        MockUpContent.allTheSongsInTheWorld = allTheSongsInTheWorld;
    }
}
