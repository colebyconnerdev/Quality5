package com.ccdev.quality;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Coleby on 7/21/2016.
 */

public class InputDialogFragment extends Fragment {

    private TextView mTitleText, mMessageText;
    private EditText mInputText;
    private Button mPositiveButton, mNegativeButton;

    private String mTag;
    private FragmentManager mFragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTitleText = (TextView) view.findViewById(R.id.inputDialog_title);
        mMessageText = (TextView) view.findViewById(R.id.inputDialog_message);
        mInputText = (EditText) view.findViewById(R.id.inputDialog_input);
        mPositiveButton = (Button) view.findViewById(R.id.inputDialog_positiveButton);
        mNegativeButton = (Button) view.findViewById(R.id.inputDialog_negativeButton);

        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirm();
            }
        });
    }

    private void confirm() {
        ((QualityFragment) getTargetFragment()).onInputDialogResult(true, mInputText.getText().toString());
        dismiss();
    }

    public void show(FragmentManager fm, String tag) {
        mTag = tag;
        mFragmentManager = fm;
        mFragmentManager.beginTransaction().add(R.id.main_content, this).addToBackStack(mTag).commit();
    }

    public void dismiss() {
        mFragmentManager.popBackStack(mTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
