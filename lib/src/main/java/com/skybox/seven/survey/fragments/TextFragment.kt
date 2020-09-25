package com.skybox.seven.survey.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import com.skybox.seven.survey.R
import com.skybox.seven.survey.SurveyViewModel
import com.skybox.seven.survey.answer.TextResult
import com.skybox.seven.survey.databinding.FragmentTextBinding
import com.skybox.seven.survey.helper.QuestionFragmentArgs
import com.skybox.seven.survey.views.createRadioButton
import java.util.*

private const val BOOLEAN = "QUESTION_BOOL"
private const val HINT = "QUESTION_HINT"

class TextFragment : Fragment() {
    private lateinit var binding: FragmentTextBinding
    lateinit var args: QuestionFragmentArgs
    val answer = MutableLiveData(TextResult())
    lateinit var hint: String
    private val viewModel: SurveyViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            args = QuestionFragmentArgs(it)
            hint = it.getString(HINT, "Answer")
        }

        answer.value?.id = args.id
        answer.value?.startDate = Date()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTextBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner

        binding.radioGroup.apply {
            removeAllViews()
            addView(createRadioButton(viewModel.utilityText.value!!.yes , "yes", context).apply { id = R.id.yes })
            addView(createRadioButton(viewModel.utilityText.value!!.no, "no", context). apply { id = R.id.no })
            setOnCheckedChangeListener { _, _: Int ->
                answer.value?.answered = findViewById<RadioButton>(R.id.yes).isChecked
                viewModel.answers.value?.set(args.id, answer.value!!)
                viewModel.goToNext.value = (this.checkedRadioButtonId != -1)

                answer.value = answer.value
            }
        }

        return binding.root
    }

    override fun onPause() {
        answer.value?.endDate = Date()
        viewModel.answers.value?.set(args.id, answer.value!!)
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        viewModel.goToNext.value = (binding.radioGroup.checkedRadioButtonId != -1)
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int, title: String, subTitle: String, boolean: Boolean, hint: String) =
            TextFragment().apply {
                arguments = QuestionFragmentArgs.putGenerics(id, title, subTitle).apply {
                    putBoolean(BOOLEAN, boolean)
                    putString(HINT, hint)
                }
            }
    }
}