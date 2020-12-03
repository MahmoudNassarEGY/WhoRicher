package com.mnassar.WhoRicher;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.util.*;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;

import java.util.*;
/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private Button button2;
    private TextView scoreTV;
    private TextView topName;
    private TextView bottomName;
    private ImageView topImage;
    private ImageView bottomImage;
    private ImageView backButton;
    private CardView centerWrapper;
    private RelativeLayout centerLine;
    private TextView centerText;
    private ImageView centerImage;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int maxScore;
    private int tempRandom;
    private String message;
    private InterstitialAd mInterstitialAd;

    ImageView image1;
    ImageView image2;

    Questions Drake = new Questions(0,R.drawable.drake,150000,"Drake");
    Questions Elon = new Questions(1,R.drawable.elon1,72000000,"Elon Musk");
    Questions Bezos = new Questions(2,R.drawable.jeff,196000000,"Jeff Bezos");
    Questions Eminem = new Questions(3,R.drawable.eminem,210000,"Eminem");
    Questions Gates = new Questions(4,R.drawable.gates,120000000,"Bill Gates");
    Questions Zuckerberg = new Questions(5,R.drawable.mark,96000000,"Mark Zuckerberg");
    Questions Buffet = new Questions(6,R.drawable.buffet,77500000,"Warren Buffet");
    Questions Larry = new Questions(7,R.drawable.lpage,71600000,"Larry Page");
    Questions Sergey = new Questions(8,R.drawable.sbrin,69400000,"Sergey Brin");
    Questions Ronaldo = new Questions(9,R.drawable.ronaldo,450000,"Cristiano Ronaldo");
    Questions Sawiris = new Questions(10,R.drawable.sawiris,3000000,"Naguib Sawiris");
    Questions Cuban = new Questions(11,R.drawable.cuban,4200000,"Mark Cuban");
    Questions Walton = new Questions(12,R.drawable.walton,61200000,"Rob Walton");
    Questions Bloomberg = new Questions(13,R.drawable.bloomberg,54900000,"Michael Bloomberg");
    Questions Huateng = new Questions(14,R.drawable.huateng,57300000,"Ma Huateng");
    Questions Alice = new Questions(15,R.drawable.awalton,62000000,"Alice Walton");
    Questions Arnault = new Questions(16,R.drawable.arnault,110200000,"Bernard Arnault");
    Questions Ambani = new Questions(17,R.drawable.ambani,80000000,"Mukesh Ambani");
    Questions Ellison = new Questions(18,R.drawable.ellison,73400000,"Larry Ellison");
    Questions Ballmer = new Questions(19,R.drawable.ballmer,73000000,"Steve Ballmer");
    Questions Ortega = new Questions(20,R.drawable.ortega,65400000,"Amnacio Ortega");
    Questions Mackenzie = new Questions(21,R.drawable.mackenzie,62400000,"Mackenzie Scott");
    Questions Jwalton = new Questions(22,R.drawable.jwalton,61800000,"Jim Walton");
    Questions Dgilbert = new Questions(23,R.drawable.dgilbert,49600000,"Daniel Gilbert");
    Questions Jma = new Questions(24,R.drawable.jma,48800000,"Jack Ma");
    Questions Ckoch = new Questions(25,R.drawable.ckoch,44900000,"Charles Koch");
    Questions Wtalal = new Questions(26,R.drawable.wtalal,18900000,"Alwaleed Ibn Talal");
    Questions Twoods = new Questions(27,R.drawable.twoods,800000,"Tiger Woods");
    Questions Mayweather = new Questions(28,R.drawable.mayweather,700000,"Floyd Mayweather");
    Questions Trump = new Questions(29,R.drawable.trump,2100000,"Donald Trump");
    Questions Dell = new Questions(30,R.drawable.dell,35100000,"Michael Dell");
    Questions Kanye = new Questions(31,R.drawable.kanye,1300000,"Kanye West");
    Questions Kim = new Questions(32,R.drawable.kimk,900000,"Kim Kardashian");
    Questions Oprah = new Questions(33,R.drawable.oprah,3500000,"Oprah");
    Questions Jayz = new Questions(34,R.drawable.jayz,1000000,"Jay-Z");
    Questions Dion = new Questions(35,R.drawable.dion,800000,"Celine Dion");
    Questions Cruise = new Questions(36,R.drawable.cruise,600000,"Tom Cruise");
    Questions Rihanna = new Questions(37,R.drawable.rihanna,550000,"Rihanna");




    Questions dummy = new Questions(30,R.drawable.dell,35100000,"Michael Dell");

    int score = 0;
    /*
    Questions Answer = new Questions(3,13,112000000,"Answer");
    Questions Answer2 = new Questions(3,13,112000000,"Answer2");
    */
    final Questions [] array = {Drake,Elon,Bezos,Eminem, Gates,Zuckerberg, Buffet,Larry,Sergey,Ronaldo,Sawiris,Cuban,
            Dell, Trump, Mayweather,Twoods, Wtalal,Ckoch ,Jma,Jwalton, Dgilbert, Mackenzie,Ortega,Ellison,Ballmer,Ambani,Arnault,Alice,Huateng,Bloomberg, Walton,Kanye,Kim,Oprah,Jayz,Dion,Cruise,Rihanna,dummy};
    Random rand = new Random();
    int random1 = rand.nextInt(array.length-1);
    int random2 = rand.nextInt(array.length-1);

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            //mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (AUTO_HIDE) {
                        delayedHide(AUTO_HIDE_DELAY_MILLIS);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    view.performClick();
                    break;
                default:
                    break;
            }
            return false;
        }
    };
    /**
    *
    */
    private final Runnable resumeToNormal = new Runnable() {
        @Override
        public void run() {
            centerImage.setVisibility(View.INVISIBLE);
            centerText.setVisibility(View.VISIBLE);
            centerWrapper.setCardBackgroundColor(Color.parseColor("#999999"));

            score++;
            scoreTV.setText("Score: " + score);

            topImage.setImageResource(array[random1].ImageID);
            topName.setText(array[random1].CelebName);

            bottomImage.setImageResource(array[random2].ImageID);
            bottomName.setText(array[random2].CelebName);

            bottomImage.setOnClickListener(onBottomImageClick);

            topImage.setOnClickListener(onTopImageClick);
        }
    };

    private final Runnable gameOver = new Runnable() {
        @Override
        public void run() {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
                new AlertDialog.Builder(FullscreenActivity.this)
                        .setTitle("Game Over")
                        .setCancelable(false)
                        .setMessage(message)
                        .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                score = 0;

                                centerImage.setVisibility(View.INVISIBLE);
                                centerText.setVisibility(View.VISIBLE);
                                centerWrapper.setCardBackgroundColor(Color.parseColor("#999999"));

                                scoreTV.setText("Score: " + score);

                                topImage.setImageResource(array[random1].ImageID);
                                topName.setText(array[random1].CelebName);

                                bottomImage.setImageResource(array[random2].ImageID);
                                bottomName.setText(array[random2].CelebName);

                                bottomImage.setOnClickListener(onBottomImageClick);
                                topImage.setOnClickListener(onTopImageClick);

                                    /*
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.empty_transition, R.anim.empty_transition);
*/
                                mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                            }
                        })
                        .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
            }

        }
    };

    private View.OnClickListener onTopImageClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(array[random1].getNetWorth() > array[random2].getNetWorth()){
                centerText.setVisibility(View.INVISIBLE);
                centerImage.setImageDrawable(getDrawable(R.drawable.check_icon));
                centerImage.setVisibility(View.VISIBLE);
                centerWrapper.setCardBackgroundColor(Color.parseColor("#33BB77"));

                if((score+1)%5 == 0) {
                    random1 = rand.nextInt(array.length - 1);
                }
                    tempRandom = rand.nextInt(array.length-1);
                    while(tempRandom == random2 || tempRandom == random1){
                        tempRandom = rand.nextInt(array.length-1);
                    }
                    random2 = tempRandom;

                Handler h = new Handler();
                topImage.setOnClickListener(null);
                bottomImage.setOnClickListener(null);
                h.postDelayed(resumeToNormal, 1000);
            } else {
                topImage.setOnClickListener(null);
                bottomImage.setOnClickListener(null);

                centerText.setVisibility(View.INVISIBLE);
                centerImage.setImageDrawable(getDrawable(R.drawable.x_icon));
                centerImage.setVisibility(View.VISIBLE);
                centerWrapper.setCardBackgroundColor(Color.parseColor("#f24b2e"));

                if(score > maxScore){
                    editor.putInt("maxScore", score);
                    editor.commit();
                    message = "New Best Score: " + score;
                }
                else{
                    message = "Score: " + score + ", " + "Best Score: " + maxScore;
                }

                random1 = rand.nextInt(array.length);
                random2 = rand.nextInt(array.length);
                while(random1 == random2){
                    random2 = rand.nextInt(array.length);
                }

                Handler h = new Handler();
                h.postDelayed(gameOver, 1500);
            }
        }
    };

    private View.OnClickListener onBottomImageClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(array[random2].getNetWorth() > array[random1].getNetWorth()){
                centerText.setVisibility(View.INVISIBLE);
                centerImage.setImageDrawable(getDrawable(R.drawable.check_icon));
                centerImage.setVisibility(View.VISIBLE);
                centerWrapper.setCardBackgroundColor(Color.parseColor("#33BB77"));
                //centerLine.setBackground(getDrawable(R.drawable.color_green));
                if((score+1)%5 == 0){
                    random2 = rand.nextInt(array.length-1);
                }
                tempRandom = rand.nextInt(array.length-1);
                while(tempRandom == random2 || tempRandom == random1){
                    tempRandom = rand.nextInt(array.length-1);
                }
                random1 = tempRandom;
                Handler h = new Handler();
                topImage.setOnClickListener(null);
                bottomImage.setOnClickListener(null);
                h.postDelayed(resumeToNormal, 1000);
            } else {
                topImage.setOnClickListener(null);
                bottomImage.setOnClickListener(null);

                centerText.setVisibility(View.INVISIBLE);
                centerImage.setImageDrawable(getDrawable(R.drawable.x_icon));
                centerImage.setVisibility(View.VISIBLE);
                centerWrapper.setCardBackgroundColor(Color.parseColor("#f24b2e"));

                if(score > maxScore){
                    editor.putInt("maxScore", score);
                    editor.commit();
                    message = "New Best Score: " + score;
                }
                else{
                    message = "Score: " + score + ", " + "Best Score: " + maxScore;
                }

                random1 = rand.nextInt(array.length);
                random2 = rand.nextInt(array.length);
                while(random1 == random2){
                    random2 = rand.nextInt(array.length);
                }
                Handler h = new Handler();
                h.postDelayed(gameOver, 1500);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9783627063502026/4417212458");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                new AlertDialog.Builder(FullscreenActivity.this)
                        .setTitle("Game Over")
                        .setCancelable(false)
                        .setMessage(message)
                        .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                score = 0;

                                centerImage.setVisibility(View.INVISIBLE);
                                centerText.setVisibility(View.VISIBLE);
                                centerWrapper.setCardBackgroundColor(Color.parseColor("#999999"));

                                scoreTV.setText("Score: " + score);

                                topImage.setImageResource(array[random1].ImageID);
                                topName.setText(array[random1].CelebName);

                                bottomImage.setImageResource(array[random2].ImageID);
                                bottomName.setText(array[random2].CelebName);

                                bottomImage.setOnClickListener(onBottomImageClick);
                                topImage.setOnClickListener(onTopImageClick);

                                    /*
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.empty_transition, R.anim.empty_transition);
*/
                                mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                            }
                        })
                        .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
            }
        });

        mVisible = true;
        //mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        scoreTV = findViewById(R.id.score);
        bottomImage = findViewById(R.id.image_bottom);
        topImage = findViewById(R.id.image_top);
        topName = findViewById(R.id.name1);
        bottomName = findViewById(R.id.name2);
        backButton = findViewById(R.id.back_button);
        centerImage = findViewById(R.id.center_image);
        centerText = findViewById(R.id.center_text);
        centerWrapper = findViewById(R.id.center_wrapper);
        centerLine = findViewById(R.id.center_line);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        maxScore = sharedPreferences.getInt("maxScore", -1);


        while(random1 == random2){
            random2 = rand.nextInt(array.length);
        }

        topImage.setImageResource(array[random1].ImageID);
        topName.setText(array[random1].CelebName);
        bottomImage.setImageResource(array[random2].ImageID);
        bottomName.setText(array[random2].CelebName);

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        bottomImage.setOnClickListener(onBottomImageClick);

        topImage.setOnClickListener(onTopImageClick);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*
        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });
*/
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
     }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mContentView = findViewById(R.id.fullscreen_content);
        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);

    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
    public void addListenerOnButton() {

        button2 = (Button) findViewById(R.id.button);

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                System.out.println("Button Clicked");

                Intent activity1Intent = new Intent(getApplicationContext(), FullscreenActivity.class);
                startActivity(activity1Intent);

            }

        });

    }
}