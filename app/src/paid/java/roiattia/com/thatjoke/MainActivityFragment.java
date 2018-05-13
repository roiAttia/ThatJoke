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

public class MainActivityFragment extends MainFragment
    implements EndpointsAsyncTask.EndpointCallback{

    private LinearLayout mLoadingLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);

        mLoadingLayout = rootview.findViewById(R.id.layout_loading);

        Button button = rootview.findViewById(R.id.btn_joke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EndpointsAsyncTask(MainActivityFragment.this).execute();
            }
        });

        return rootview;
    }

    @Override
    public void preJoke() {
        mLoadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void postJoke(String joke) {
        mLoadingLayout.setVisibility(View.INVISIBLE);
        startJokeActivity(joke);
    }
}
