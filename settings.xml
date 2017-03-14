package com.example.jk.flipfone;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by User on 2/27/2017.
 */

public class settingsFrag extends Fragment implements View.OnClickListener{
    PlayFrag.OnButtonPressedListener mCallback;
    Button TButton, MButton, SButton, CButton, RButton, LButton, TOSButton, CrButton;
    public interface OnButtonPressedListener {
        public void onButtonSelected(String whichOne);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.settings, container, false);
        TButton = (Button) rootView.findViewById(R.id.buttonTutorial);
        TButton.setOnClickListener(this);
        MButton = (Button) rootView.findViewById(R.id.buttonMusic);
        MButton.setOnClickListener(this);
        SButton = (Button) rootView.findViewById(R.id.buttonSound);
        SButton.setOnClickListener(this);
        CButton = (Button) rootView.findViewById(R.id.buttonCamera);
        CButton.setOnClickListener(this);
        RButton = (Button) rootView.findViewById(R.id.buttonRemoveAds);
        RButton.setOnClickListener(this);
        LButton = (Button) rootView.findViewById(R.id.buttonLogin);
        LButton.setOnClickListener(this);
        TOSButton = (Button) rootView.findViewById(R.id.buttonTermsOfService);
        TOSButton.setOnClickListener(this);
        CrButton = (Button) rootView.findViewById(R.id.buttonCredits);
        CrButton.setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (PlayFrag.OnButtonPressedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnButtonPressedListener");
        }
    }
    public void onClick (View v){
        if(v == TButton)
            mCallback.onButtonSelected("Tutorial");
        else if(v == MButton)
            mCallback.onButtonSelected("Music");
        else if(v == SButton)
            mCallback.onButtonSelected("Sound");
        if(v == CButton)
            mCallback.onButtonSelected("Camera");
        else if(v == RButton)
            mCallback.onButtonSelected("RemoveAds");
        else if(v == LButton)
            mCallback.onButtonSelected("Login");
        else if(v == TOSButton)
            mCallback.onButtonSelected("Terms of Service");
        else if(v == CrButton)
            mCallback.onButtonSelected("Credits");
    }

}
