package com.skybox.seven.survey.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.skybox.seven.survey.SurveyViewModel
import com.skybox.seven.survey.databinding.FragmentBooleanBinding
import com.skybox.seven.survey.helper.QuestionFragmentArgs
import com.skybox.seven.survey.views.createRadioButton


class BooleanFragment : Fragment() {
    val viewModel: SurveyViewModel by activityViewModels()
    lateinit var args: QuestionFragmentArgs
    private lateinit var binding: FragmentBooleanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = QuestionFragmentArgs(requireArguments())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentBooleanBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner
        binding.radioGroup.apply {
            removeAllViews()
            addView(createRadioButton("yes", "yes", context))
            addView(createRadioButton("no", "no", context))
        }
        return binding.root
    }

    fun goNext() {
        if (binding.radioGroup.checkedRadioButtonId == -1) {
            // error
        } else {
            viewModel.goToNext.value = true
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String, subTitle: String) = BooleanFragment().apply {
            arguments = QuestionFragmentArgs.putGenerics(title, subTitle)
        }
    }
}