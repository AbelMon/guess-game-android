package com.logica.abel.guessthenumber;


import android.animation.Animator;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.logica.abel.guessthenumber.numberButtonBuilder.NumberBtnInterface;
import com.logica.abel.guessthenumber.numberButtonBuilder.NumberButton;
import com.luolc.emojirain.EmojiRainLayout;

import java.util.Random;

/**
 * Created by abel on 4/03/17.
 */

public class GuessNumberActivity extends Activity implements NumberBtnInterface, NewGameInterface {

    private int numberToGuess;
    private NumberButton zeroBtn, oneBnt, twoBtn, threeBtn, fourBtn,
            fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn;
    private TextView textArea, messageView;
    private boolean isFirstPress = true;
    private ImageView undo, done;
    private int randomNumber;
    private EmojiRainLayout emojiContainer;

    private MediaPlayer mediaPlayer, laughSound, applauseSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guess_number_activity);
        initialization();
        randomNumber = getRandomNumber();
    }

    private void initialization() {
        messageView = (TextView) findViewById(R.id.messageView);

        startBtnNumbers();
        setupMusic();
        setupEmojiContainer();
        setupLaughSound();
        setupApplauseSound();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }


    private void setupLaughSound() {
        int resource = getResources().getIdentifier("risa", "raw", getPackageName());
        laughSound = MediaPlayer.create(this, resource);
    }

    private void startApplause() {
        applauseSound.start();
    }

    private void setupApplauseSound() {
        int resource = getResources().getIdentifier("aplausos", "raw", getPackageName());
        applauseSound = MediaPlayer.create(this, resource);
    }

    private void setupEmojiContainer() {
        emojiContainer = (EmojiRainLayout) findViewById(R.id.emojiContainer);

        emojiContainer.setDuration(1000);
    }


    private void clearEmojiContainer() {
        emojiContainer.clearEmojis();
    }

    private void setupMusic() {
        int resource = getResources().getIdentifier("game_song", "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, resource);
        mediaPlayer.setLooping(true);
    }

    private void startBtnNumbers() {
        zeroBtn = new NumberButton(this, this, R.id.zeroBtn);

        oneBnt = new NumberButton(this, this, R.id.oneBtn);

        twoBtn = new NumberButton(this, this, R.id.twoBtn);

        threeBtn = new NumberButton(this, this, R.id.threeBtn);

        fourBtn = new NumberButton(this, this, R.id.fourBtn);

        fiveBtn = new NumberButton(this, this, R.id.fiveBtn);

        sixBtn = new NumberButton(this, this, R.id.sixBtn);

        sevenBtn = new NumberButton(this, this, R.id.sevenBtn);

        eightBtn = new NumberButton(this, this, R.id.eightBtn);

        nineBtn = new NumberButton(this, this, R.id.nineBtn);

        textArea = (TextView) findViewById(R.id.textArea);

        undo = (ImageView) findViewById(R.id.undo);
        setupUndoBtn();

        done = (ImageView) findViewById(R.id.done);
        setupDoneBtn();
    }

    @Override
    public void onNumberSelected(int selectedNumber) {

        if (isFirstPress) {
            isFirstPress = false;
            setTextOnFirstPress(selectedNumber);
            return;
        }

        setUserSelectedNumber(selectedNumber);
    }

    private void startLaugh() {
        laughSound.start();
    }


    private void setupUndoBtn() {
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undoNumberSelection();
            }
        });
    }

    private void undoNumberSelection() {
        String selectedNumber = textArea.getText().toString();
        String newNumberRemoved = selectedNumber.replaceFirst(".$","");
        textArea.setText(newNumberRemoved);
    }

    private void setTextOnFirstPress(int selectedNumber) {
        textArea.setText("");
        textArea.setText(String.valueOf(selectedNumber));
    }

    private void setUserSelectedNumber(int selectedNumber) {
        String prevSelectedNumber = textArea.getText().toString();

        textArea.setText(
                prevSelectedNumber + String.valueOf(selectedNumber)
        );
    }


    private void setupDoneBtn() {
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateNumber();
            }
        });
    }

    private int getRandomNumber() {
        Random random = new Random();

        return random.nextInt(2000) + 1;
    }

    private void validateNumber() {
        clearEmojiContainer();

        if (getUserNumber() == 0) {
            Toast.makeText(this, "Ingresa un numero valido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (randomNumber == getUserNumber()) {
            startWinEmojiAnim();
            startApplause();
            Toast.makeText(this, "Ganaste", Toast.LENGTH_SHORT).show();
            NewGameDialog dialog = new NewGameDialog(this, this);
            dialog.show();
            return;
        }

        if (randomNumber > getUserNumber()) {
            startGenericFailureCase();
            Toast.makeText(this, "Es mayor", Toast.LENGTH_SHORT).show();
            return;
        }

        if (randomNumber < getUserNumber()) {
            startGenericFailureCase();
            Toast.makeText(this, "Es menor", Toast.LENGTH_SHORT).show();
        }
    }


    private void startGenericFailureCase() {
        failGuessAnimation();
        startFailMessageAnim();
        startLaugh();
    }

    private void failGuessAnimation() {
        emojiContainer.addEmoji(
                R.drawable.me_divierte
        );

        startEmojiAnimation();
    }

    private void startFailMessageAnim() {

        YoYo.with(Techniques.SlideInLeft).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                messageView.setVisibility(View.VISIBLE);
                textArea.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startOutMessageAnim();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).delay(0).duration(1000).playOn(messageView);
    }


    private void startOutMessageAnim() {
        YoYo.with(Techniques.SlideOutRight).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                messageView.setVisibility(View.GONE);
                textArea.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).duration(1000).playOn(messageView);
    }

    private void startEmojiAnimation() {
        emojiContainer.startDropping();
    }

    private int getUserNumber() {

        if (textArea.getText().toString().isEmpty()) {
            return 0;
        }

        return Integer.valueOf(
                textArea.getText().toString()
        );
    }


    private void startWinEmojiAnim() {
        emojiContainer.addEmoji(
                R.drawable.me_asombra
        );

        startEmojiAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    private void startLoveAnimation() {
        clearEmojiContainer();
        emojiContainer.addEmoji(
                R.drawable.me_encanta
        );

        startEmojiAnimation();
    }

    @Override
    public void onNewGame() {
        startLoveAnimation();
        randomNumber = getRandomNumber();
        isFirstPress = true;
        textArea.setText(getString(R.string.enterNumber));
    }

    @Override
    public void onFinishGame() {
        finish();
    }
}
