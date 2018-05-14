package roiattia.com.thatjoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import roiattia.com.jokelibrary.JokeActivity;

public class MainFragment extends Fragment
        implements EndpointsAsyncTask.EndpointCallback{

    private LinearLayout mLoadingLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);

        mLoadingLayout = rootview.findViewById(R.id.layout_loading);

        // set the Hit me! button on click event
        Button button = rootview.findViewById(R.id.btn_joke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EndpointsAsyncTask(MainFragment.this).execute();
            }
        });

        return rootview;

    }

    /**
     * This method open the joke activity
     *@param joke the joke retrieved from the java library
    **/
    void startJokeActivity(String joke){
        Intent intent = new Intent(getContext(), JokeActivity.class);
        intent.putExtra("joke", joke);
        startActivity(intent);
    }

    @Override
    public void preJoke() {
        mLoadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void postJoke(String joke) { }

    /**
     * This method hides the progress bar
     **/
    void hideLoading(){
        mLoadingLayout.setVisibility(View.INVISIBLE);
    }
}
