package com.mnassar.WhoRicher;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.content.Intent;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Handler myHandler;
    private TextView appSlogan;
    private CardView playButton;
    private TextView bestScore;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            appSlogan.setVisibility(View.INVISIBLE);
        }
    };
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int maxScore;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        MobileAds.setRequestConfiguration(
                new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("ABCDEF012345"))
                        .build());

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        mAdView = findViewById(R.id.adView);
        if(mAdView!=null){
            mAdView.setVisibility(View.VISIBLE);
            final AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
            mAdView.setAdListener(new AdListener(){
                @Override
                public void onAdFailedToLoad(LoadAdError adError) {
                    // Code to be executed when an ad request fails.
                    //System.out.println(adError.getMessage());
                    mAdView.loadAd(adRequest);
                }
            });

            mAdView.loadAd(adRequest);

        }


        maxScore = sharedPreferences.getInt("maxScore", -1);
        if(maxScore == -1){
            maxScore = 0;
            editor.putInt("maxScore", 0);
            editor.commit();
        }

        myHandler = new Handler();
        appSlogan = findViewById(R.id.app_slogan);
        playButton = findViewById(R.id.play_button);
        bestScore = findViewById(R.id.best_score);

        bestScore.setText("Best Score: " + maxScore);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity2Intent = new Intent(getApplicationContext(), FullscreenActivity.class);
                startActivity(activity2Intent);
                overridePendingTransition(R.anim.from_right_in, R.anim.empty_transition);
            }
        });
        myHandler.postDelayed(runnable, 4000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void addListenerOnButton() {
/*

        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                System.out.println("Button Clicked");

                Intent activity2Intent = new Intent(getApplicationContext(), FullscreenActivity.class);
                startActivity(activity2Intent);

            }

        });
*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        maxScore = sharedPreferences.getInt("maxScore", -1);
        bestScore.setText("Best Score: " + maxScore);
    }
}
