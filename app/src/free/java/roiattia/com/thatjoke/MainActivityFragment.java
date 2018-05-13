package roiattia.com.thatjoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import roiattia.com.jokelibrary.JokeActivity;
import roiattia.com.joker.Joker;

public class MainActivityFragment extends MainFragment
    implements EndpointsAsyncTask.EndpointCallback{

    private InterstitialAd mInterstitialAd;
    private String mJoke;
    private LinearLayout mLoadingLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);

        mLoadingLayout = rootview.findViewById(R.id.layout_loading);

        initializeAd();

        Button button = rootview.findViewById(R.id.btn_joke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EndpointsAsyncTask(MainActivityFragment.this).execute();
             }
        });

        return rootview;
    }


    private void initializeAd() {
        MobileAds.initialize(getContext(), "ca-app-pub-3940256099942544~3347511713");

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                startJokeActivity(mJoke);
            }
        });
    }

    @Override
    public void preJoke() {
        mLoadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void postJoke(String joke) {
        mJoke = joke;
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mLoadingLayout.setVisibility(View.INVISIBLE);
        }
    }
}
