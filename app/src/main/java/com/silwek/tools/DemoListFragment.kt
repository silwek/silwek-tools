package com.silwek.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silwek.tools.binding.FragmentBinding
import com.silwek.tools.databinding.FragmentDemoListBinding
import com.silwek.tools.magellan.safeNavigateTo

class DemoListFragment : FragmentBinding<FragmentDemoListBinding>() {
    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDemoListBinding {
        return FragmentDemoListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btShowKeyboard.setOnClickListener {
            safeNavigateTo(DemoListFragmentDirections.actionFormDemoFragment())
        }
    }
}