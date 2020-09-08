package com.skybox.seven.survey

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.skybox.seven.survey.config.SurveyConfigs
import com.skybox.seven.survey.config.UtilityText
import com.skybox.seven.survey.databinding.LayoutSurveyViewBinding
import com.skybox.seven.survey.helper.StepAdapter
import com.skybox.seven.survey.helper.SurveyArgs
import com.skybox.seven.survey.helper.SurveyCallbacks
import com.skybox.seven.survey.model.Step


class SurveyView : Fragment() {
    private lateinit var binding: LayoutSurveyViewBinding;
    private lateinit var adapter: StepAdapter
    val viewModel: SurveyViewModel by activityViewModels()
    private lateinit var args: SurveyArgs
    private var listener: SurveyCallbacks? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = SurveyArgs(requireArguments())
        adapter = args.steps?.let { StepAdapter(this, it) }!!
        viewModel.total.value = args.steps?.size
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? SurveyCallbacks
        if (listener == null) {
            throw ClassCastException("$context must implement SurveyCallbacks")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = LayoutSurveyViewBinding.inflate(inflater, container, false)
        binding.view = this
        binding.lifecycleOwner = viewLifecycleOwner
        binding.pager.isUserInputEnabled = false
        setUp()
        return binding.root
    }

    private fun setUp() {
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.currentPage.value = position
                viewModel.previous.value = (position > 0)
            }
        })
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { _, _ -> }.attach()

        viewModel.next.observe(viewLifecycleOwner, {
            nextTab()
        })

        viewModel.finish.observe(viewLifecycleOwner, {
            listener?.finished(viewModel.answers.value)
        })
    }

    fun previousTab() {
        binding.pager.currentItem = binding.pager.currentItem - 1
    }

    fun nextTab() {
        binding.pager.currentItem = binding.pager.currentItem + 1
    }

    fun closeSurvey() {

    }

    companion object {
        @JvmStatic fun newInstance(steps: ArrayList<Step>, utility: UtilityText) = SurveyView().apply {
            arguments = SurveyArgs.putGenerics(steps, utility)
        }
    }
}
