package com.example.jk.flipfone;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by User on 2/11/2017.
 */

public class PlayFrag extends android.support.v4.app.Fragment implements View.OnClickListener {
    OnButtonPressedListener mCallback;
    Button button1;
    Button button2;
    Button button3;
    public interface OnButtonPressedListener {
        public void onButtonSelected(String whichOne);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.play, container, false);
        button1 = (Button) rootView.findViewById(R.id.buttonClassic);
        button2 = (Button) rootView.findViewById(R.id.buttonArcade);
        button3 = (Button) rootView.findViewById(R.id.buttonSettings);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnButtonPressedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnButtonPressedListener");
        }
    }
    @Override
    public void onClick (View v){
        if(v == button1)
            mCallback.onButtonSelected("classic");
        else if(v == button2)
            mCallback.onButtonSelected("arcade");
        else mCallback.onButtonSelected("settings");
    }
}

