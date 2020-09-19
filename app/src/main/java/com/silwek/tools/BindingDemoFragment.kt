package com.silwek.tools

import android.view.LayoutInflater
import android.view.ViewGroup
import com.silwek.tools.binding.FragmentBinding
import com.silwek.tools.databinding.FragmentBindingDemoBinding

class BindingDemoFragment : FragmentBinding<FragmentBindingDemoBinding>() {
    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBindingDemoBinding {
        return FragmentBindingDemoBinding.inflate(inflater, container, false)
    }
}