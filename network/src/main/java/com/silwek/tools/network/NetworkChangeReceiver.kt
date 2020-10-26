package com.silwek.tools.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NetworkChangeReceiver(private var callback: () -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        callback()
    }
}