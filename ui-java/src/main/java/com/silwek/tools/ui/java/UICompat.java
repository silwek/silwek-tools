package com.silwek.tools.ui.java;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class UICompat {

    public static void showToast(
            Context context,
            @StringRes int messageRes
    ) {
        showToast(context, messageRes, Toast.LENGTH_SHORT);
    }

    public static void showToast(
            Context context,
            @StringRes int messageRes,
            int duration
    ) {
        UIExtCompat.Companion.showToast(context, messageRes, duration);
    }

    public static void showToast(Context context, String message) {
        showToast(context, message, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, String message, int duration) {
        UIExtCompat.Companion.showToast(context, message, duration);
    }

    public static boolean hasOpenedDialogs(FragmentActivity activity) {
        return UIExtCompat.Companion.hasOpenedDialogs(activity);
    }

    public static void showKeyboard(View view) {
        showKeyboard(view, null);
    }

    public static void showKeyboard(View view, Dialog parentDialog) {
        UIExtCompat.Companion.showKeyboard(view, parentDialog);
    }

    public static void hideKeyboard(Fragment fragment) {
        UIExtCompat.Companion.hideKeyboard(fragment);
    }

    public static void hideKeyboard(View view) {
        hideKeyboard(view, null);
    }

    public static void hideKeyboard(View view, Dialog parentDialog) {
        UIExtCompat.Companion.hideKeyboard(view, parentDialog);
    }
}
