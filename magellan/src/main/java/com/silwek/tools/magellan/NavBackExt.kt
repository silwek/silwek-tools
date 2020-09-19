package com.silwek.tools.magellan

import android.app.Activity
import android.content.Intent
import androidx.activity.addCallback
import androidx.core.app.NavUtils
import androidx.core.app.TaskStackBuilder
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


fun Activity.navigateUp(defaultIntent: Intent) {
    var upIntent = NavUtils.getParentActivityIntent(this)
    if (upIntent == null) {
        upIntent = defaultIntent
    }
    if (NavUtils.shouldUpRecreateTask(this, upIntent) || this.isTaskRoot) {
        TaskStackBuilder
            .create(this)
            .addNextIntent(upIntent).startActivities()
        this.finish()
    } else {
        this.onBackPressed()
    }
}

fun Fragment.navigateUp(defaultIntent: Intent) {
    val activity = activity ?: return
    var upIntent = NavUtils.getParentActivityIntent(activity)
    if (upIntent == null) {
        upIntent = defaultIntent
    }
    if (NavUtils.shouldUpRecreateTask(activity, upIntent)) {
        if (!navigateBack()) {
            TaskStackBuilder
                .create(activity)
                .addNextIntent(upIntent).startActivities()
            activity.finish()
        }
    } else {
        findNavController().popBackStack()
    }
}

fun Fragment.navigateBack(): Boolean {
    val navController = findNavController()
    val currentDestination = navController.currentDestination ?: return false
    val parent = currentDestination.parent ?: return false
    val startDestination = parent.startDestination
    return navController.popBackStack(startDestination, false)
}

inline fun Fragment.onBackPress(crossinline action: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(this) {
        action()
    }
}