package com.oskrojas.elrefri;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by oskrojas on 16/3/15.
 */
public class MainFragment {

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login_facebook_fragment, container, false);

        return view;
    }
}
