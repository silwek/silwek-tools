package com.silwek.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silwek.tools.binding.FragmentBinding
import com.silwek.tools.databinding.FragmentFormDemoBinding
import com.silwek.tools.ui.*

class FormDemoFragment : FragmentBinding<FragmentFormDemoBinding>() {
    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFormDemoBinding {
        return FragmentFormDemoBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btShowKeyboard.setOnClickListener { editText.showKeyboard() }
            btHideKeyboard.setOnClickListener {
                editText.hideKeyboard()
            }
            btValidate.attachForm(editText, null) {
                return@attachForm editText.text?.lengthAtLeast(6) ?: false
            }
            btValidate.setOnClickListener {
                val message = editText.text?.toString() ?: ""
                editText.clear()
                context?.showToast("Your message : $message")
            }
        }

    }
}