package julienbirabent.musicsharringappprotoype.util;

import julienbirabent.musicsharringappprotoype.models.MusicType;

/**
 * Created by julbi on 2017-07-24.
 */

public class MusicTypePair {

    private MusicType left;
    private  MusicType right;

    public MusicTypePair(MusicType left, MusicType right) {
        this.left = left;
        this.right = right;
    }

    public MusicType getLeft() { return left; }
    public MusicType getRight() { return right; }


}