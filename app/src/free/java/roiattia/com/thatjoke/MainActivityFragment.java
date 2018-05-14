package roiattia.com.thatjoke;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivityFragment extends MainFragment{

    private InterstitialAd mInterstitialAd;
    private String mJoke;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeAd();
    }

    /*
    * This method initialize the Interstitial Ad
    */
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
                //open joke activity
                startJokeActivity(mJoke);
            }
        });
    }

    @Override
    public void postJoke(String joke) {
        mJoke = joke;
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            hideLoading();
        }
    }
}
