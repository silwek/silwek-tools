package com.silwek.tools.magellan

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun NavController.safeNavigate(direction: NavDirections) {
    try {
        navigate(direction)
    } catch (ignored: IllegalArgumentException) {

    }
}
fun NavController.safeNavigate(destId: Int) {
    try {
        navigate(destId)
    } catch (ignored: IllegalArgumentException) {

    }
}

fun Fragment.safeNavigateTo(destId: Int) {
    try {
        val nav = findNavController()
        nav.navigate(destId)
    } catch (ignored: IllegalStateException) {
        //Cancel
    }
}

fun Fragment.safeNavigateTo(direction: NavDirections) {
    try {
        val nav = findNavController()
        nav.navigate(direction)
    } catch (ignored: IllegalStateException) {
        //Cancel
    }
}

fun Fragment.popBackStack() {
    try {
        findNavController().popBackStack()
    } catch (ignored: IllegalStateException) {
        //Cancel
    }
}