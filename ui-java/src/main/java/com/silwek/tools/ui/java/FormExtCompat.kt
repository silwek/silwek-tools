package com.silwek.tools.ui.java

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.silwek.tools.ui.*

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
            return charSequence.lengthAtLeast(minLength)
        }

        fun clear(editText: EditText) {
            editText.clear()
        }

        fun attachForm(
            validButton: Button,
            editText: EditText,
            layout: TextInputLayout?,
            isValid: () -> Boolean
        ) {
            validButton.attachForm(editText, layout, isValid)
        }

        fun attachLayout(input: TextInputEditText, layout: TextInputLayout) {
            input.attachLayout(layout)
        }
    }
}