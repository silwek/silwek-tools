package com.silwek.tools.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

@RequiresApi(api = Build.VERSION_CODES.N)
class NetworkConnectionMonitorV24(private var context: Context) : NetworkConnectionMonitor() {
    private var callback: AdvancedNetworkCallback? = null
    override fun unregisterDefaultNetworkCallback() {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
                ?: return
        callback?.let {
            connectivityManager.unregisterNetworkCallback(it)
        }
    }

    override fun registerDefaultNetworkCallback() {
        try {
            checkCurrentStatus(context)
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val callback = object : AdvancedNetworkCallback() {
                override fun onNetworkChange(isNetworkConnected: Boolean) {
                    postValue(isNetworkConnected)
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            this.callback = callback
        } catch (e: Exception) {
            postValue(false)
        }
    }

    abstract class AdvancedNetworkCallback : NetworkCallback() {
        private var activeNetworks: MutableList<Network> = ArrayList()
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            if (activeNetworks.none { it.networkHandle == network.networkHandle })
                activeNetworks.add(network)
            onNetworkChange(activeNetworks.isNotEmpty())
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            activeNetworks.removeAll { it.networkHandle == network.networkHandle }
            onNetworkChange(activeNetworks.isNotEmpty())
        }

        abstract fun onNetworkChange(isNetworkConnected: Boolean)
    }
}