package com.silwek.tools.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

interface LocationHandler : PermissionRationaleDialog {
    companion object {
        private const val REQUEST_PERMISSIONS = 1547
    }

    var fusedLocationClient: FusedLocationProviderClient?
    var lastKnownPosition: Location?
    var locationPermissionAsked: Boolean

    @SuppressLint("MissingPermission")
    fun getLocation(ctx: Context, fragment: Fragment) {
        if (lastKnownPosition != null) {
            onLocation(lastKnownPosition)
        }
        if (isLocationPermissionGranted(ctx, fragment)) {
            val activity = fragment?.activity ?: return
            if (fusedLocationClient == null) {
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
                fusedLocationClient?.lastLocation?.addOnSuccessListener { location: Location? ->
                    lastKnownPosition = location
                    onLocation(lastKnownPosition)
                }
            }
        }
    }

    fun onLocation(lastKnownPosition: Location?) {}

    private fun isLocationPermissionGranted(ctx: Context, fragment: Fragment): Boolean {
        val perm = Manifest.permission.ACCESS_COARSE_LOCATION
        return if (Build.VERSION.SDK_INT >= 23) {
            if (ctx.checkSelfPermission(perm)
                == PackageManager.PERMISSION_GRANTED
            ) {
                true
            } else {
                if (fragment.shouldShowRequestPermissionRationale(perm)) {
                    showRequestLocationPermissionRationale(perm)
                } else {
                    if (!locationPermissionAsked) {
                        locationPermissionAsked = true
                        fragment.requestPermissions(
                            arrayOf(perm),
                            REQUEST_PERMISSIONS
                        )
                    }
                }
                false
            }
        } else {
            true
        }
    }

    fun showRequestLocationPermissionRationale(perm: String) {
        showRationaleDialog(
            getLocationDialogTitle(),
            getLocationDialogContent(),
            perm,
            REQUEST_PERMISSIONS
        )
    }

    @StringRes
    fun getLocationDialogTitle(): Int

    @StringRes
    fun getLocationDialogContent(): Int

    fun handleLocationPermissionResult(
        ctx: Context,
        fragment: Fragment,
        requestCode: Int,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation(ctx, fragment)
            }
        }
    }
}