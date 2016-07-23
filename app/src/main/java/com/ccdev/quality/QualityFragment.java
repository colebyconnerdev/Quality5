package com.ccdev.quality;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by Coleby on 7/21/2016.
 */

public class QualityFragment extends Fragment{

    public FragmentInterface fragmentInterface;
    public interface FragmentInterface {
        void setSelectedFragment(QualityFragment fragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof FragmentInterface)) {
            throw new ClassCastException("Hosting activity must implement FragmentInterface");
        } else {
            fragmentInterface = (FragmentInterface) getActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        fragmentInterface.setSelectedFragment(this);
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onDialogResult(String input) {
        Log.e("QualityFragment", "onDialogResult(String input) called but not overridden in " + this.toString());
    }

    public void onDialogResult() {
        Log.e("QualityFragment", "onDialogResult() called but not overridden in " + this.toString());
    }
}
