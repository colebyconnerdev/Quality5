package com.ccdev.quality;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Coleby on 7/21/2016.
 */

public class HomeFragment extends QualityFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv = (TextView) view.findViewById(R.id.home_text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        InputDialogFragment f = new InputDialogFragment();
        f.setTargetFragment(this, 0);
        f.show(getFragmentManager(), "TEST");
    }

    @Override
    public void onInputDialogResult(boolean result, String input) {
        ((TextView) getView().findViewById(R.id.home_text)).setText(input);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
