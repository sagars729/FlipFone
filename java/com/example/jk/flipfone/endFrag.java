package com.example.jk.flipfone;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.R.id.button1;
import static com.example.jk.flipfone.R.id.scoreNum;

/**
 * Created by User on 4/29/2017.
 */

public class endFrag extends Fragment implements View.OnClickListener {
    String highScore="0";
    String score="0";
    String lastOne="0";
    TextView scoreNum;
    TextView highScoreNum;
    TextView lastFlipNum;
    Button home;
    Button playAgain;
    endResponse mCallback;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.end, container, false);
        scoreNum = (TextView) rootView.findViewById(R.id.scoreNum);
        highScoreNum = (TextView) rootView.findViewById(R.id.highscoreNum);
        lastFlipNum = (TextView) rootView.findViewById(R.id.lastFlipNum);
        scoreNum.setText(score);
        highScoreNum.setText(highScore);
        lastFlipNum.setText(lastOne);
        home = (Button) rootView.findViewById(R.id.home);
        home.setOnClickListener(this);
        playAgain = (Button) rootView.findViewById(R.id.playAgain);
        playAgain.setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (endFrag.endResponse) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataFromActivityToFragment");
        }
    }
    public void setScores(String a, String b, String c){
        score=a;
        highScore=b;
        lastOne=c;
    }

    @Override
    public void onClick(View view) {
        if(view == playAgain)
            mCallback.comeBack(0);
        else
            mCallback.comeBack(1);
    }
    public interface endResponse {
        void comeBack(int x);
    }
}
