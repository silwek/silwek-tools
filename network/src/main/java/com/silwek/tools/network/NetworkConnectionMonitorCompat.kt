package com.silwek.tools.network

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build

class NetworkConnectionMonitorCompat(var context: Context) : NetworkConnectionMonitor() {
    var broadcastReceiver: NetworkChangeReceiver? = null
    override fun unregisterDefaultNetworkCallback() {
        if (broadcastReceiver == null) return
        context.unregisterReceiver(broadcastReceiver)
    }

    override fun registerDefaultNetworkCallback() {
        broadcastReceiver = NetworkChangeReceiver {
            checkCurrentStatus(context)
        }
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(broadcastReceiver, filter)
    }

    companion object {
        fun create(context: Context): NetworkConnectionMonitor {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                NetworkConnectionMonitorV24(context)
            } else {
                NetworkConnectionMonitorCompat(context)
            }
        }
    }
}