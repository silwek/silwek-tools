package com.silwek.tools.ui

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


fun disableForm(fields: List<View>) {
    fields.forEach { it.isEnabled = false }
}

fun enableForm(fields: List<View>) {
    fields.forEach { it.isEnabled = true }
}

fun CharSequence.isEmail(): Boolean {
    return isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun CharSequence.isNotEmail(): Boolean = !isEmail()

fun CharSequence.lengthAtLeast(minLength: Int): Boolean {
    return minLength <= 0 && isBlank() || isNotBlank() && length >= minLength
}

fun EditText.clear() {
    setText("")
    hideKeyboard()
}

fun Button.attachForm(
    editText: EditText,
    layout: TextInputLayout?,
    isValid: () -> Boolean
) {
    isEnabled = isValid()
    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            layout?.error = null
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            isEnabled = isValid()
        }

    })
}

fun TextInputEditText.attachLayout(layout: TextInputLayout) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            layout.error = null
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    })
}