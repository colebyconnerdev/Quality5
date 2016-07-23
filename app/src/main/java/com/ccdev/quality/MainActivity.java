package com.ccdev.quality;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        QualityFragment.FragmentInterface {

    QualityFragment mSelectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void setSelectedFragment(QualityFragment fragment) {
        mSelectedFragment = fragment;
    }

    @Override
    public void onBackPressed() {
        if (mSelectedFragment != null && !mSelectedFragment.onBackPressed()) {

        }
    }
}
