package com.silwek.tools.ui.java

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.silwek.tools.ui.hideKeyboard
import com.silwek.tools.ui.isEmail
import com.silwek.tools.ui.isNotEmail
   internal
class FormExtCompat {
    companion object {

        fun disableForm(fields: List<View>) {
            com.silwek.tools.ui.disableForm(fields)
        }

        fun enableForm(fields: List<View>) {
            com.silwek.tools.ui.enableForm(fields)
        }

        fun isEmail(charSequence: CharSequence): Boolean {
            return charSequence.isEmail()
        }

        fun isNotEmail(charSequence: CharSequence): Boolean = charSequence.isNotEmail()

        fun lengthAtLeast(charSequence: CharSequence, minLength: Int): Boolean {
            return minLength <= 0 && charSequence.isBlank() || charSequence.isNotBlank() && charSequence.length >= minLength
        }

        fun clear(editText: EditText) {
            editText.setText("")
            editText.hideKeyboard()
        }

        fun attachForm(
            validButton: Button,
            editText: TextInputEditText,
            layout: TextInputLayout,
            isValid: () -> Boolean
        ) {
            validButton.isEnabled = isValid()
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    layout.error = null
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    validButton.isEnabled = isValid()
                }

            })
        }

        fun attachLayout(input: TextInputEditText, layout: TextInputLayout) {
            input.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    layout.error = null
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

            })
        }
    }
}