package com.logica.abel.guessthenumber;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by abel on 31/05/17.
 */

class NewGameDialog extends Dialog {


    private NewGameInterface gameInterface;
    private Button playBtn, cancelBtn;

    NewGameDialog(Context context, NewGameInterface gameInterface) {
        super(context);
        this.gameInterface = gameInterface;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game_dialog);
        initialization();
    }


    private void initialization() {
        playBtn = (Button) findViewById(R.id.playBtn);
        setupPlayBtn();
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        setupCancelBtn();
    }

    private void setupPlayBtn() {
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameInterface.onNewGame();
                dismiss();
            }
        });
    }

    private void setupCancelBtn() {
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                gameInterface.onFinishGame();
            }
        });
    }
}
