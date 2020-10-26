package com.silwek.tools.network

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData

abstract class NetworkConnectionMonitor : LiveData<Boolean?>() {
    abstract fun unregisterDefaultNetworkCallback()
    abstract fun registerDefaultNetworkCallback()
    fun checkCurrentStatus(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
        postValue(isConnected)
        return isConnected
    }
}