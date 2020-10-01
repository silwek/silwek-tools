package com.silwek.tools.ui.java

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.silwek.tools.ui.*

class UIExtCompat {
    companion object {
        fun showToast(
            context: Context?,
            @StringRes messageRes: Int,
            duration: Int = Toast.LENGTH_SHORT
        ) {
            context?.showToast(messageRes, duration)
        }

        fun showToast(context: Context?, message: String, duration: Int = Toast.LENGTH_SHORT) {
            context?.showToast(message, duration)
        }

        fun hasOpenedDialogs(activity: FragmentActivity?): Boolean {
            return activity?.hasOpenedDialogs() ?: false
        }

        fun showKeyboard(view: View?, parentDialog: Dialog? = null) {
            view?.showKeyboard(parentDialog)
        }

        fun hideKeyboard(fragment: Fragment?) {
            fragment?.hideKeyboard()
        }

        fun hideKeyboard(view: View?, parentDialog: Dialog? = null) {
            view?.hideKeyboard(parentDialog)
        }

        fun tintBackground(view: View?, @ColorRes colorRes: Int) {
            view?.tintBackground(colorRes)
        }

        fun tintBackgroundColor(view: View?, @ColorInt colorInt: Int) {
            view?.tintBackgroundColor(colorInt)
        }
    }
}