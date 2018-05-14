package roiattia.com.thatjoke;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivityFragment extends MainFragment{

    @Override
    public void postJoke(String joke) {
        // hide progress bar
        hideLoading();
        // open joke activity
        startJokeActivity(joke);
    }
}
