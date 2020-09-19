package com.silwek.tools.binding

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class ActivityBinding<T : ViewBinding> : AppCompatActivity() {
    private var _binding: T? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = initView(layoutInflater)
        setContentView(_binding?.root)
    }

    abstract fun initView(inflater: LayoutInflater): T

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}