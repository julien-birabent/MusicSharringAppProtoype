package julienbirabent.musicsharringappprotoype;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by julbi on 2017-07-19.
 */

public class FragmentUtils {


    public FragmentUtils() {
    }

    public static void activateNaviguationBack(Fragment fragment, boolean on){

        ((AppCompatActivity) fragment.getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(on);
        ((AppCompatActivity) fragment.getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(on);

    }


}
