package roiattia.com.thatjoke;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;


import roiattia.com.jokelibrary.JokeActivity;
import roiattia.com.joker.Joker;

public class MainFragment extends Fragment {

    public void startJokeActivity(String joke){
        Intent intent = new Intent(getContext(), JokeActivity.class);
        intent.putExtra("joke", joke);
        startActivity(intent);
    }
}
