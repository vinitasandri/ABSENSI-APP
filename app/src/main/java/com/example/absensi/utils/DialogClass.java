package com.example.absensi.utils;

import android.content.Context;
import android.view.View;

public class DialogClass {
    public static android.app.AlertDialog.Builder dialog(Context context, View v) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setView(v);
        builder.setCancelable(false);
        return builder;
    }
}
