package roiattia.com.thatjoke;

import android.content.Intent;
import android.support.v4.app.Fragment;

import roiattia.com.jokelibrary.JokeActivity;
import roiattia.com.joker.Joker;

public class MainFragment extends Fragment {

    public void openJokeActivity() {
        Joker joker = new Joker();
        Intent intent = new Intent(getContext(), JokeActivity.class);
        intent.putExtra("joke", joker.getJoke());
        startActivity(intent);
    }
}
