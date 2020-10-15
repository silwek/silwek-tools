package com.silwek.tools.ui

import android.app.Dialog
import android.content.Context
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun Context.showToast(@StringRes messageRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, messageRes, duration).show()
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun FragmentActivity.hasOpenedDialogs(): Boolean {
    val fragments: List<Fragment>? = supportFragmentManager.fragments
    return fragments?.firstOrNull { fragment -> fragment is DialogFragment } != null
}

fun View.showKeyboard(parentDialog: Dialog? = null) {
    this.requestFocus()
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    parentDialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}

fun Fragment.hideKeyboard() {
    view?.clearFocus()
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun View.hideKeyboard(parentDialog: Dialog? = null) {
    clearFocus()
    parentDialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.hideSoftInputFromWindow(windowToken, 0)
}

fun View.tintBackground(@ColorRes colorRes:Int){
    val colorInt:Int = ContextCompat.getColor(context, colorRes)
    tintBackgroundColor(colorInt)
}

fun View.tintBackgroundColor(@ColorInt colorInt:Int){
    background.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(colorInt, BlendModeCompat.SRC_ATOP)
}