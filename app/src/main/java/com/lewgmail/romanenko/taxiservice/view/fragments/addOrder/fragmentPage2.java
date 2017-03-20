package com.lewgmail.romanenko.taxiservice.view.fragments.addOrder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lewgmail.romanenko.taxiservice.R;

/**
 * Created by Lev on 18.03.2017.
 */

public class FragmentPage2 extends Fragment {

    public FragmentPage2() {
    }

    public static FragmentPage2 newInstance(int sectionNumber) {
        FragmentPage2 fragment = new FragmentPage2();
       /* Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);*/
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_order_2, container, false);
        return rootView;
    }
}
