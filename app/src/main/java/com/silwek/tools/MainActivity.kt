package com.silwek.tools

import android.view.LayoutInflater
import com.silwek.tools.binding.ActivityBinding
import com.silwek.tools.databinding.ActivityMainBinding

class MainActivity : ActivityBinding<ActivityMainBinding>() {

    override fun initView(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }
}