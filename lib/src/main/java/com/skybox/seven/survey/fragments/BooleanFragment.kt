package com.skybox.seven.survey.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.skybox.seven.survey.SurveyViewModel
import com.skybox.seven.survey.answer.BooleanResult
import com.skybox.seven.survey.databinding.FragmentBooleanBinding
import com.skybox.seven.survey.helper.QuestionFragmentArgs
import com.skybox.seven.survey.views.createRadioButton
import java.util.*

private const val TAG = "BooleanFragment"
class BooleanFragment : Fragment() {
    private val viewModel: SurveyViewModel by activityViewModels()
    lateinit var args: QuestionFragmentArgs
    private lateinit var binding: FragmentBooleanBinding
    private var answer = BooleanResult()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = QuestionFragmentArgs(requireArguments())
        answer.startDate = Date()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentBooleanBinding.inflate(inflater, container, false)
        binding.args = args
        binding.lifecycleOwner = viewLifecycleOwner
        binding.owner.removeView(binding.holder)
        binding.radioGroup.apply {
            removeAllViews()
            addView(createRadioButton(viewModel.utilityText.value!!.yes , "yes", context))
            addView(createRadioButton(viewModel.utilityText.value!!.no, "no", context))
            setOnCheckedChangeListener { _, _: Int ->
                answer.answer = (this.checkedRadioButtonId != -1)
                viewModel.answers.value?.set(args.id, answer)
                viewModel.goToNext.value = (this.checkedRadioButtonId != -1)
            }
        }
        return binding.root
    }

    override fun onPause() {
        Log.e(TAG, "onPause: ")
        answer.endDate = Date()
        viewModel.answers.value?.set(args.id, answer)
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        viewModel.goToNext.value = (binding.radioGroup.checkedRadioButtonId != -1)
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int, title: String, subTitle: String) = BooleanFragment().apply {
            arguments = QuestionFragmentArgs.putGenerics(id, title, subTitle)
        }
    }
}