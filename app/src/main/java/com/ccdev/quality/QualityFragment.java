package com.ccdev.quality;

import android.support.v4.app.Fragment;

/**
 * Created by Coleby on 7/21/2016.
 */

public abstract class QualityFragment extends Fragment{
    public abstract boolean onBackPressed();

    public void onInputDialogResult(boolean result, String input) {
        throw new DialogResponseException("onInputDialogResult must be overrode in calling class.");
    };

    public void onYesNoDialogResult(boolean result) {
        throw new DialogResponseException("onYesNoDialogResult must be overrode in calling class.");
    };

    public class DialogResponseException extends RuntimeException {
        public DialogResponseException(String message) {
            super(message);
        }
    }
}
