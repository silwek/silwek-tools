package com.silwek.tools.magellan.java;

import android.app.Activity;
import android.content.Intent;

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
