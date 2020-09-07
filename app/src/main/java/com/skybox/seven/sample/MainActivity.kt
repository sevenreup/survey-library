package com.skybox.seven.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.skybox.seven.sample.databinding.ActivityMainBinding
import com.skybox.seven.survey.SurveyViewModel
import com.skybox.seven.survey.config.SurveyConfigs
import com.skybox.seven.survey.config.UtilityText
import com.skybox.seven.survey.model.BooleanStep
import com.skybox.seven.survey.model.EndStep
import com.skybox.seven.survey.model.MultiSelectionStep
import com.skybox.seven.survey.model.StartStep

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val surveyViewModel: SurveyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val steps = listOf(
            StartStep(
                "R.string.self_test_intro_title",
                "R.string.self_test_intro_sub_title",
                "R.string.self_test_intro_sub_title"
            ),
            BooleanStep(
                "R.string.self_test_intro_sub_title",
                "R.string.self_test_intro_sub_title"
            ),
            MultiSelectionStep(
                "R.string.self_test_intro_sub_title",
                "R.string.self_test_intro_sub_title",
                arrayListOf(
                    "one",
                    "two",
                    "three",
                    "four",
                    "five"
                )
            ),
            EndStep(
                "R.string.self_test_intro_sub_title",
                "R.string.self_test_intro_sub_title",
                "R.string.self_test_intro_sub_title"
            )
        )
        val config = SurveyConfigs(this, this,
            UtilityText("Next", "Cancel"))
        binding.surveyView.start(steps, config, surveyViewModel)
    }
}