package com.skybox.seven.survey.fragments

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.skybox.seven.survey.R
import com.skybox.seven.survey.SurveyViewModel
import com.skybox.seven.survey.answer.BooleanResult
import com.skybox.seven.survey.databinding.FragmentArrayBooleanBinding
import com.skybox.seven.survey.databinding.FragmentBooleanBinding
import com.skybox.seven.survey.helper.QuestionFragmentArgs
import com.skybox.seven.survey.util.ImprovedBulletSpan
import com.skybox.seven.survey.util.LiTagHandler
import com.skybox.seven.survey.views.createRadioButton
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log


private const val TAG = "ArrayBooleanFragment"
private const val LIST_OFF_QUESTIONS = "question_list"

class ArrayBooleanFragment : Fragment() {
    private val viewModel: SurveyViewModel by activityViewModels()
    lateinit var args: QuestionFragmentArgs
    private lateinit var binding: FragmentBooleanBinding
    private var answer = BooleanResult()
    private var listMore: ArrayList<String>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().apply {
            args = QuestionFragmentArgs(this)
            listMore = getStringArrayList(LIST_OFF_QUESTIONS)
        }
        answer.id = args.id
        answer.startDate = Date()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBooleanBinding.inflate(inflater, container, false)
        binding.args = args
        binding.lifecycleOwner = viewLifecycleOwner

        val html = createHtml()
        Log.e(TAG, "onCreateView: $html")

        val htmlSpannable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html, null, LiTagHandler())
        }

        val spannableBuilder = SpannableStringBuilder(htmlSpannable)
        val bulletSpans = spannableBuilder.getSpans(0, spannableBuilder.length, BulletSpan::class.java)
        bulletSpans.forEach {
            val start = spannableBuilder.getSpanStart(it)
            val end = spannableBuilder.getSpanEnd(it)
            spannableBuilder.removeSpan(it)
            spannableBuilder.setSpan(
                ImprovedBulletSpan(bulletRadius = dip(3), gapWidth = dip(8)),
                start,
                end,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
        }

        binding.answers.text = spannableBuilder

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
        Log.d(TAG, "onPause: ")
        answer.endDate = Date()
        viewModel.answers.value?.set(args.id, answer)
        super.onPause()
    }

    private fun createHtml() = buildString {
        this.append("<ul>")
        listMore?.forEach { item ->
            this.append("<li>")
            this.append(item)
            this.append("</li>")
        }
        this.append("</ul>")
    }

    override fun onResume() {
        super.onResume()
        viewModel.goToNext.value = (binding.radioGroup.checkedRadioButtonId != -1)
    }

    private fun dip(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics).toInt()
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int, title: String, subTitle: String, answers: ArrayList<String>) =
            ArrayBooleanFragment().apply {
                arguments = QuestionFragmentArgs.putGenerics(id, title, subTitle).apply {
                    putStringArrayList(LIST_OFF_QUESTIONS, answers)
                }
            }
    }
}