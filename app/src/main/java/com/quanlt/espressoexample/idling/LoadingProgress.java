package com.quanlt.espressoexample.idling;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by quanlt on 04/01/2017.
 */

public class LoadingProgress extends DialogFragment {
    public static final String TAG = "LoadingProgress";
    private MaterialDialog dialog;

    public LoadingProgress(Context context, String title) {
        dialog = new MaterialDialog.Builder(context)
                .title(title)
                .content("Please wait")
                .progress(true, 0)
                .build();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return dialog;
    }

    public void setTitle(String title) {
        dialog.setTitle(title);
    }
}
