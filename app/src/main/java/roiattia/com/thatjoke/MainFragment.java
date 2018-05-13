package roiattia.com.thatjoke;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;


import roiattia.com.jokelibrary.JokeActivity;
import roiattia.com.joker.Joker;

public class MainFragment extends Fragment
        implements EndpointsAsyncTask.EndpointCallback{

    public void setAsyncTask(){
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
        endpointsAsyncTask.execute();
    }

    @Override
    public void getJoke(String joke) {
        Log.i("Endpoint", joke);
        Intent intent = new Intent(getContext(), JokeActivity.class);
        intent.putExtra("joke", joke);
        startActivity(intent);
    }
}
