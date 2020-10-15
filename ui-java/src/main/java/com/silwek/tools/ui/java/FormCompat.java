package com.silwek.tools.ui.java;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class FormCompat {
    public static void disableForm(@NonNull List<View> fields) {
        FormExtCompat.Companion.disableForm(fields);
    }

    public static void enableForm(@NonNull List<View> fields) {
        FormExtCompat.Companion.enableForm(fields);
    }

    public static boolean isEmail(@NonNull CharSequence charSequence) {
        return FormExtCompat.Companion.isEmail(charSequence);
    }

    public static boolean isNotEmail(@NonNull CharSequence charSequence) {
        return FormExtCompat.Companion.isNotEmail(charSequence);
    }

    public static boolean lengthAtLeast(@NonNull CharSequence charSequence, int minLength) {
        return FormExtCompat.Companion.lengthAtLeast(charSequence, minLength);
    }

    public static void clear(@NonNull EditText editText) {
        FormExtCompat.Companion.clear(editText);
    }

    public static void attachForm(
            @NonNull Button validButton,
            @NonNull EditText editText,
            @Nullable TextInputLayout layout,
            @NonNull FormCompatValidator validator
    ) {
        FormExtCompat.Companion.attachForm(validButton, editText, layout, validator::isValid);
    }

    public static void attachLayout(@NonNull TextInputEditText input, @NonNull TextInputLayout layout) {
        FormExtCompat.Companion.attachLayout(input, layout);
    }

    public static interface FormCompatValidator {
        boolean isValid();
    }
}
