package com.silwek.tools.magellan.java;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import kotlin.Unit;

public class Magellan {
    public static NavController findNavController(Fragment fragment) {
        if (fragment == null) //Cancel
            return null;
        return NavHostFragment.findNavController(fragment);
    }

    /**
     * @return 0 if no destination or null Fragment/NavController
     */
    public static int getCurrentDestinationId(@Nullable Fragment fragment) {
        if (fragment == null) return 0;
        NavController navController = findNavController(fragment);
        return getCurrentDestinationId(navController);
    }

    /**
     * @return 0 if no destination or null NavController
     */
    public static int getCurrentDestinationId(@Nullable NavController navController) {
        if (navController != null && navController.getCurrentDestination() != null)
            return navController.getCurrentDestination().getId();
        else return 0;
    }

    public static void navigateUp(Activity activity, Intent defaultIntent) {
        MagellanExtCompat.Companion.navigateUp(activity, defaultIntent);
    }

    public static void navigateUp(Fragment fragment, Intent defaultIntent) {
        MagellanExtCompat.Companion.navigateUp(fragment, defaultIntent);
    }

    public static boolean navigateBack(Fragment fragment) {
        return MagellanExtCompat.Companion.navigateBack(fragment);
    }

    public static void onBackPress(Fragment fragment, MagellanAction action) {
        MagellanExtCompat.Companion.onBackPress(fragment, () -> {
            action.doAction();
            return Unit.INSTANCE;
        });
    }

    public static void safeNavigate(NavController navController, NavDirections direction) {
        MagellanExtCompat.Companion.safeNavigate(navController, direction);
    }

    public static void safeNavigate(NavController navController, int destId) {
        MagellanExtCompat.Companion.safeNavigate(navController, destId);
    }

    public static void safeNavigateTo(Fragment fragment, int destId) {
        MagellanExtCompat.Companion.safeNavigateTo(fragment, destId);
    }

    public static void safeNavigateTo(Fragment fragment, NavDirections direction) {
        MagellanExtCompat.Companion.safeNavigateTo(fragment, direction);
    }

    public static void popBackStack(Fragment fragment) {
        MagellanExtCompat.Companion.popBackStack(fragment);
    }

    public interface MagellanAction {
        void doAction();
    }
}
