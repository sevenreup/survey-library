package com.skybox.seven.survey.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.checkbox.MaterialCheckBox
import com.skybox.seven.survey.SurveyViewModel
import com.skybox.seven.survey.answer.MultiChoiceResult
import com.skybox.seven.survey.databinding.FragmentBooleanBinding
import com.skybox.seven.survey.databinding.FragmentMultiSelectionBinding
import com.skybox.seven.survey.helper.QuestionFragmentArgs
import java.util.*
import kotlin.collections.ArrayList

private const val LIST_OFF_QUESTIONS = "question_list"

class MultiSelectionFragment : Fragment() {
    private val viewModel: SurveyViewModel by activityViewModels()
    private lateinit var binding: FragmentBooleanBinding
    private val answer = MultiChoiceResult()

    lateinit var args: QuestionFragmentArgs
    var answers: ArrayList<String>? = null
    private val checkBoxes: ArrayList<MaterialCheckBox> = ArrayList()
    private var oneChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = QuestionFragmentArgs(requireArguments())
        answers = requireArguments().getStringArrayList(LIST_OFF_QUESTIONS)
        answer.startDate = Date()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBooleanBinding.inflate(inflater, container, false)
        binding.args = args
        binding.lifecycleOwner = viewLifecycleOwner

        binding.owner.removeView(binding.radioGroup)
        answers?.forEach {
            val check = MaterialCheckBox(context).apply {
                text = it
            }
            check.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->
                validate()
            }
            binding.holder.addView(check)
            checkBoxes.add(check)
        }

        return binding.root
    }

    private fun validate() {
        checkBoxes.forEachIndexed { index, it ->
            if (it.isChecked) {
                oneChecked = true
                answer.answers[index] = it.text.toString()
            } else {
                answer.answers.remove(index)
            }
        }
        viewModel.answers.value?.set(args.id, answer)
        viewModel.goToNext.value = oneChecked
    }

    override fun onResume() {
        super.onResume()
        validate()
    }

    override fun onPause() {
        validate()
        super.onPause()
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int, title: String, subTitle: String, questions: ArrayList<String>) =
            MultiSelectionFragment().apply {
                arguments = QuestionFragmentArgs.putGenerics(id, title, subTitle).apply {
                    putStringArrayList(LIST_OFF_QUESTIONS, questions)
                }
            }
    }
}