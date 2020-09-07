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
                "Welcome to our Amazing survey",
                "Get ready to get your socks blown off",
                "Start"
            ),
            BooleanStep(
                "This is a boolean question",
                "The user has to answer one choice"
            ),
            MultiSelectionStep(
                "This is a multiple selection Question",
                "The user has multiple options",
                arrayListOf(
                    "one",
                    "two",
                    "three",
                    "four",
                    "five"
                )
            ),
            EndStep(
                "Thank you for taking this journey",
                "Those were some of the features",
                "Finish"
            )
        )
        val config = SurveyConfigs(this, this,
            UtilityText("Next", "Cancel"))
        binding.surveyView.start(steps, config, surveyViewModel)
    }
}