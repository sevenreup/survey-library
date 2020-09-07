package com.skybox.seven.survey.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skybox.seven.survey.databinding.FragmentEndStepBinding
import com.skybox.seven.survey.databinding.FragmentStartStepBinding
import com.skybox.seven.survey.helper.BasicFragmentArgs


class EndStepFragment : Fragment() {
    private lateinit var binding: FragmentStartStepBinding
    lateinit var args: BasicFragmentArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = BasicFragmentArgs(requireArguments())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentStartStepBinding.inflate(inflater, container, false)
        binding.args = args
        binding.start.setOnClickListener {finish()}
        return binding.root
    }

    private fun finish() {
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String, subTitle: String, buttonText: String) =
                EndStepFragment().apply {
                    arguments = BasicFragmentArgs.putGenerics(title, subTitle, buttonText)
                }
    }
}