package com.nest.scratch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class inbox extends Fragment {


    public inbox() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View root=inflater.inflate(R.layout.fragment_inbox, container, false);

        getFragmentManager().beginTransaction().replace(R.id.inbox_frame,new Messages()).commit();

        return  root;
    }

}
