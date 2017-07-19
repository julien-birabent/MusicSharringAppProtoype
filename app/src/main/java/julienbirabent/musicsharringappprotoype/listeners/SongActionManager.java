package julienbirabent.musicsharringappprotoype.listeners;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import julienbirabent.musicsharringappprotoype.R;

/**
 * Created by Julien on 2017-07-19.
 */

public class SongActionManager implements View.OnClickListener {

    private Context context;

    public SongActionManager(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(context, view);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.song_popup_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(
                        context,
                        "You Clicked : " + item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
        });

        popup.show(); //showing popup menu
    }

}
