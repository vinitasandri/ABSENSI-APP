package com.example.absensi.utils;

import android.content.Context;
import android.content.Intent;

public class SwitchAcitivity {

    public static void mainSwitch(Context from,Class to){
        Intent intent = new Intent(from,to);
        from.startActivity(intent);
    }
}
