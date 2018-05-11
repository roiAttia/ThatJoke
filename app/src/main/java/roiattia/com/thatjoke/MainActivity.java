package roiattia.com.thatjoke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import roiattia.com.jokelibrary.JokeActivity;
import roiattia.com.joker.Joker;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private Joker mJoker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJoker = new Joker();
        final boolean isFreeFlavor = BuildConfig.IS_FREE;

        if (BuildConfig.IS_FREE) {
            initializeAd();
        } else {
            Toast.makeText(this, "paid flavor", Toast.LENGTH_SHORT).show();
        }

        Button button = findViewById(R.id.btn_joke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFreeFlavor) {
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }
                } else {
                    openJokeActivity();
                }
            }
        });
    }

    private void openJokeActivity() {
        Intent intent = new Intent(MainActivity.this, JokeActivity.class);
        intent.putExtra("joke", mJoker.getJoke());
        startActivity(intent);
    }

    private void initializeAd() {
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                openJokeActivity();
            }
        });
    }
}
