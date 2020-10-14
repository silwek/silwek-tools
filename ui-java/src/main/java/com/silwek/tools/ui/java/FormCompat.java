package com.silwek.tools.ui.java;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class FormCompat {
    public static void disableForm(List<View> fields) {
        FormExtCompat.Companion.disableForm(fields);
    }

    public static void enableForm(List<View> fields) {
        FormExtCompat.Companion.enableForm(fields);
    }

    public static boolean isEmail(CharSequence charSequence) {
        return FormExtCompat.Companion.isEmail(charSequence);
    }

    public static boolean isNotEmail(CharSequence charSequence) {
        return FormExtCompat.Companion.isNotEmail(charSequence);
    }

    public static boolean lengthAtLeast(CharSequence charSequence, int minLength) {
        return FormExtCompat.Companion.lengthAtLeast(charSequence, minLength);
    }

    public static void clear(EditText editText) {
        FormExtCompat.Companion.clear(editText);
    }

    public static void attachForm(
            Button validButton,
            TextInputEditText editText,
            TextInputLayout layout,
            FormCompatValidator validator
    ) {
        FormExtCompat.Companion.attachForm(validButton, editText, layout, validator::isValid);
    }

    public static void attachLayout(TextInputEditText input, TextInputLayout layout) {
        FormExtCompat.Companion.attachLayout(input, layout);
    }

    public static interface FormCompatValidator {
        boolean isValid();
    }
}
