package com.example.jk.flipfone;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.button;
import static android.R.id.button1;
import static com.example.jk.flipfone.R.id.buttonClassic;

/**
 * Created by User on 2/11/2017.
 */

public class PlayFrag extends android.support.v4.app.Fragment implements View.OnClickListener {
    OnButtonPressedListener mCallback;
    Button button1;

    public interface OnButtonPressedListener {
        void onButtonSelected();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.play, container, false);
        button1 = (Button) rootView.findViewById(R.id.buttonClassic);
        button1.setOnClickListener(this);
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
        mCallback.onButtonSelected();
    }
}

