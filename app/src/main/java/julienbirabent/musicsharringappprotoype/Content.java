package julienbirabent.musicsharringappprotoype;

import julienbirabent.musicsharringappprotoype.models.Playlist;

/**
 * Created by Julien on 2017-07-18.
 */

public class Content {

    private static Content instance;

    private static Playlist[] generatedPlaylists;
    private static Playlist[] customPlaylists;

    private Content() {
        initGeneratedPlaylists();
    }

    public static Content getInstance()
    {
        if (instance == null)
        {
            // Create the instance
            instance = new Content();
        }
        return instance;
    }

    private void initGeneratedPlaylists(){

        generatedPlaylists = new Playlist[]
                {
                                new Playlist(null,"Jazz",R.drawable.ic_jazz_music, 3, true),
                                new Playlist(null,"Metal",R.drawable.ic_metal_music, 2, true),
                                new Playlist(null,"Country",R.drawable.ic_country_music, 2, true),
                                new Playlist(null,"Summer Mix",R.drawable.ic_sun, 4, true),
                                new Playlist(null,"Gospel",R.drawable.ic_gospel_music, 3, true),
                                new Playlist(null,"The 60's",R.drawable.ic_60s, 1, true)
                };

    }

    public static  Playlist[] getGeneratedPlaylistsMockUp(){

        return generatedPlaylists;
    }

}
