package com.doeunkongden.finalprojectecommerce.ui.Search.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.doeunkongden.finalprojectecommerce.R;

public class DeleteDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.deleting_dialog,null);
        builder.setView(view)
                .setPositiveButton("Yes",(dialogInterface, i) -> {

                })
                .setNegativeButton("No",(dialogInterface, i) -> {

                });

        return builder.create();
    }
}
