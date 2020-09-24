package com.silwek.tools.ui

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class UICompat {
    companion object {
        fun showToast(
            context: Context?,
            @StringRes messageRes: Int
        ) {
            showToast(context, messageRes, Toast.LENGTH_SHORT)
        }

        fun showToast(
            context: Context?,
            @StringRes messageRes: Int,
            duration: Int = Toast.LENGTH_SHORT
        ) {
            if (context == null) return
            context.showToast(messageRes, duration)
        }

        fun showToast(context: Context?, message: String) {
            showToast(context, message, Toast.LENGTH_SHORT)
        }

        fun showToast(context: Context?, message: String, duration: Int = Toast.LENGTH_SHORT) {
            if (context == null) return
            context.showToast(message, duration)
        }

        fun hasOpenedDialogs(activity: FragmentActivity?): Boolean {
            if (activity == null) return false
            return activity.hasOpenedDialogs()
        }

        fun showKeyboard(view: View?, parentDialog: Dialog? = null) {
            if (view == null) return
            view.showKeyboard(parentDialog)
        }

        fun hideKeyboard(fragment: Fragment?) {
            if (fragment == null) return
            fragment.hideKeyboard()
        }

        fun hideKeyboard(view: View?, parentDialog: Dialog? = null) {
            if (view == null) return
            view.hideKeyboard(parentDialog)
        }
    }
}