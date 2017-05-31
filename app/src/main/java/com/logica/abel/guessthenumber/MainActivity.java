package com.logica.abel.guessthenumber;

import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        //playMusic();
    }


    private void initialization() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }

    private void setupViewPager() {

        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);
    }

    private void playMusic()  {
        int resource = getResources().getIdentifier("happy_song", "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, resource);

        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }
}
