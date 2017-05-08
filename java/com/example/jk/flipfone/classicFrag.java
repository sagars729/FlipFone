package com.example.jk.flipfone;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

import static android.R.attr.button;
import static android.R.attr.y;
import static android.R.id.button1;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.jk.flipfone.R.id.score;
import static com.example.jk.flipfone.R.layout.classic;


/**
 * Created by User on 2/11/2017.
 */

public class classicFrag extends android.support.v4.app.Fragment implements View.OnClickListener {
    Button button;
    classicResponse mCallback;
    boolean can=true;
    View rootView;
    TextView scoreBoard;
    boolean allow = false;
    TextView range;
    double numFlips;
    double lower;
    double upper;
    int pivot;
    int score=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(classic, container, false);
        button = (Button) rootView.findViewById(R.id.record);
        button.setOnClickListener(this);
        range = (TextView) rootView.findViewById(R.id.range);
        range.setText(findRange());
        scoreBoard = (TextView) rootView.findViewById(R.id.score);
        scoreBoard.setText(score + "");
        allow = true;
        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (classicResponse) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataFromActivityToFragment");
        }
    }
    @Override
    public void onClick(View v){
        if(can) {
            mCallback.getSensorData();
        }
    }

    public void startControl(String color, boolean state){
        if(allow){
            button.setTextColor(Color.parseColor(color));
            can = state;}
    }
    public void delay(){
        mCallback.changeAllow();
        can = false;
        button.setTextColor(Color.parseColor("#ff0000"));

        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.i("wtf",millisUntilFinished+"");
            }

            public void onFinish() {
                mCallback.changeAllow();
            }
        }.start();
    }
   public String findRange(){
       pivot=randInt(1,3);
       double cutPoint = (double)randInt(0,10);
       cutPoint = (cutPoint/10.0);
       double lowBuf = cutPoint;
       double upBuf = 1-lowBuf;
       lower = pivot-lowBuf;
       upper = pivot+upBuf;
       String ans = lower + " ≤ " + "          " + "≤" + upper;
       return ans;
   }
    public static int randInt(int min, int max) {
        Random rand=new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    public void changeFlips(float x){
        TextView flipCounter = (TextView) rootView.findViewById(R.id.flips);
        numFlips = (double)Math.round(x*10.0)/10.0;
        flipCounter.setText(numFlips+"");
    }
    public void changeRange()
    {
        if(numFlips>=lower && numFlips<=upper){
            range.setText(findRange());
            int add;
            if(numFlips==pivot)
                add = pivot;
            else
                add = 1;
            score+=add;
            scoreBoard.setText(score+"");
        }
        else{

                    mCallback.goToEnd(score,numFlips);


        }
    }
    public interface classicResponse {
        void getSensorData();
        void changeAllow();
        void goToEnd(int x, double y);
    }

}
