package com.silwek.tools.magellan

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections

object Magellan {

    fun navigateUp(activity: Activity?, defaultIntent: Intent) {
        if (activity == null) //Cancel
            return
        activity.navigateUp(defaultIntent)
    }

    fun navigateUp(fragment: Fragment?, defaultIntent: Intent) {
        if (fragment == null) //Cancel
            return
        fragment.navigateUp(defaultIntent)
    }

    fun navigateBack(fragment: Fragment?): Boolean {
        if (fragment == null) //Cancel
            return false
        return fragment.navigateBack()
    }

    inline fun onBackPress(fragment: Fragment?, crossinline action: () -> Unit) {
        if (fragment == null) //Cancel
            return
        fragment.onBackPress(action)
    }

    fun safeNavigate(navController: NavController?, direction: NavDirections) {
        if (navController == null) //Cancel
            return
        navController.safeNavigate(direction)
    }

    fun safeNavigateTo(fragment: Fragment?, destId: Int) {
        if (fragment == null) //Cancel
            return
        fragment.safeNavigateTo(destId)
    }

    fun safeNavigateTo(fragment: Fragment?, direction: NavDirections) {
        if (fragment == null) //Cancel
            return
        fragment.safeNavigateTo(direction)
    }

    fun popBackStack(fragment: Fragment?) {
        if (fragment == null) //Cancel
            return
        fragment.popBackStack()
    }
}