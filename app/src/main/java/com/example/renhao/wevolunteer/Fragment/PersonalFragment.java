package com.example.renhao.wevolunteer.Fragment;
/*
 *
 * Created by Ge on 2016/8/8  10:03.
 *
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.renhao.wevolunteer.R;

public class PersonalFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_personal,container,false);

        return view;

    }
}
