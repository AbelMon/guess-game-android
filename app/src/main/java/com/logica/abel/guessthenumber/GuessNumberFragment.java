package com.logica.abel.guessthenumber;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by abel on 2/03/17.
 */

public class GuessNumberFragment extends Fragment {


    private ImageView questionMark;
    private TextView initGame;


    public static GuessNumberFragment newInstance() {

        Bundle args = new Bundle();

        GuessNumberFragment fragment = new GuessNumberFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guess_number_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);
    }

    private void initialization(View view) {
        questionMark = (ImageView) view.findViewById(R.id.questionMark);
        setupQuestionMark();

        initGame = (TextView) view.findViewById(R.id.initGame);
        setupInitGame();
    }

    private void setupQuestionMark() {
        YoYo.with(Techniques.RubberBand).repeat(YoYo.INFINITE).playOn(questionMark);
    }


    private void setupInitGame() {
        initGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGuessGame();
            }
        });
    }


    private void initGuessGame() {
        Intent intent = new Intent(getActivity(), GuessNumberActivity.class);
        startActivity(intent);
    }



}
