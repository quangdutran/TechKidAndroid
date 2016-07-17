package com.example.tqdu.tablayoutclone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by tqdu on 7/17/2016.
 */
public class KeyBoardFragment extends Fragment {

    KeyboardView keyboardView;
    EditText editText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keyboard,container,false);
        init(view);
        return view;
    }

    private void init(View view) {
        keyboardView = (KeyboardView) view.findViewById(R.id.keyboard);
        //editText = (EditText) view.findViewById(R.id.edit_number);
    }
}
