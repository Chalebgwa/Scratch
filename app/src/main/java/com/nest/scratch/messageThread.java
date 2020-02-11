package com.nest.scratch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Coded with love.
 * Created by black on 1/21/17.
 */

public class messageThread extends Fragment {



    private int threadID;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inbox, container,false);
        //int Id = (int) args.get("messageID");
        Bundle args = getArguments();
        threadID=(int)args.get("messageID");


        return root;

    }

    public messageThread() {

    }







}
