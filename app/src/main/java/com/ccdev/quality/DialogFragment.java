package com.ccdev.quality;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

public class DialogFragment extends Fragment {

    private static final String TAG = "DialogFragment";

    public static final String DIALOG_TITLE = "dialog_title";
    public static final String DIALOG_MESSAGE = "dialog_message";
    public static final String DIALOG_TYPE = "dialog_type";
    public static final int TYPE_OK_ONLY = 0;
    public static final int TYPE_OK_CANCEL = 1;
    public static final int TYPE_YES_NO = 2;
    public static final int TYPE_INPUT = 3;

    private TextView mTitleText, mMessageText;
    private EditText mInputText;
    private Button mPositiveButton, mNegativeButton;

    private String mDialogTag;
    private int mDialogType;
    private FragmentManager mFragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null && !getArguments().isEmpty()) {
            setupView(view);
        } else {
            Log.d(TAG, "no arguments passed from " + getTargetFragment().toString());
            dismiss();
        }
    }

    public void setupView(View view) {
        String title = getArguments().getString(DIALOG_TITLE);
        String message = getArguments().getString(DIALOG_MESSAGE);
        mDialogType = getArguments().getInt(DIALOG_TYPE);

        mTitleText = (TextView) view.findViewById(R.id.inputDialog_titleText);
        mMessageText = (TextView) view.findViewById(R.id.inputDialog_messageText);
        mInputText = (EditText) view.findViewById(R.id.inputDialog_inputText);
        mPositiveButton = (Button) view.findViewById(R.id.inputDialog_positiveButton);
        mNegativeButton = (Button) view.findViewById(R.id.inputDialog_negativeButton);

        if (title != null) {
            mTitleText.setText(title);
        } else {
            view.findViewById(R.id.inputDialog_titleContainer).setVisibility(View.GONE);
        }

        if (message != null) {
            mMessageText.setText(message);
        } else {
            mMessageText.setVisibility(View.GONE);
        }

        if (mDialogType != TYPE_INPUT) {
            mInputText.setVisibility(View.GONE);
        }

        switch (mDialogType) {
            case TYPE_OK_ONLY:
                mNegativeButton.setVisibility(View.INVISIBLE);
                mPositiveButton.setText("OK");
                break;
            case TYPE_OK_CANCEL:
                mNegativeButton.setText("CANCEL");
                mPositiveButton.setText("OK");
                break;
            case TYPE_YES_NO:
                mNegativeButton.setText("NO");
                mPositiveButton.setText("YES");
                break;
        }

        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirm();
            }
        });

        mNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void confirm() {
        Fragment fragment = getTargetFragment();

        if (fragment != null && fragment instanceof QualityFragment) {
            if (mDialogType == TYPE_INPUT) {
                String input = mInputText.getText().toString();
                ((QualityFragment) fragment).onDialogResult(input);
            } else {
                ((QualityFragment) fragment).onDialogResult();
            }
        }

        dismiss();
    }

    public void show(FragmentManager fm, String tag) {
        mDialogTag = tag;
        mFragmentManager = fm;
        mFragmentManager.beginTransaction().add(R.id.main_content, this).addToBackStack(mDialogTag).commit();
    }

    public void dismiss() {
        mFragmentManager.popBackStack(mDialogTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
