package com.logica.abel.guessthenumber.numberButtonBuilder;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by abel on 15/04/17.
 */

public class NumberButton {

    private TextView numberTextView;
    private Activity activity;
    private int buttonNumber;
    private NumberBtnInterface btnInterface;
    private MediaPlayer mediaPlayer;

    public NumberButton(Activity activity, NumberBtnInterface btnInterface, int textViewId) {
        this.activity = activity;
        this.btnInterface = btnInterface;
        numberTextView = (TextView) activity.findViewById(textViewId);
        initialization();
        setupBeepSound();
    }

    private void setupBeepSound() {
        int resource = activity.getResources().getIdentifier("button_beep", "raw", activity.getPackageName());
        mediaPlayer = MediaPlayer.create(activity, resource);
        //mediaPlayer.setLooping(true);
    }


    private void startBeep() {
        mediaPlayer.start();
    }

    private void initialization() {
        setButtonNumber();
        setupNumberTextView();
    }

    private void setButtonNumber() {
        buttonNumber = getTextViewNumber();
    }

    private int getTextViewNumber() {
        return Integer.valueOf(numberTextView.getText().toString());
    }

    public int getButtonNumber() {
        return buttonNumber;
    }

    private void setupNumberTextView() {
        numberTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBeep();
                initAnimation();
                btnInterface.onNumberSelected(buttonNumber);
            }
        });
    }

    private void initAnimation() {
        YoYo.with(Techniques.Bounce).duration(800).playOn(numberTextView);
    }
}
