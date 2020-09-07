package com.skybox.seven.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.skybox.seven.sample.databinding.ActivityMainBinding
import com.skybox.seven.survey.SurveyViewModel
import com.skybox.seven.survey.answer.BooleanResult
import com.skybox.seven.survey.answer.MultiChoiceResult
import com.skybox.seven.survey.answer.Result
import com.skybox.seven.survey.config.SurveyConfigs
import com.skybox.seven.survey.config.UtilityText
import com.skybox.seven.survey.helper.SurveyCallbacks
import com.skybox.seven.survey.model.BooleanStep
import com.skybox.seven.survey.model.EndStep
import com.skybox.seven.survey.model.MultiSelectionStep
import com.skybox.seven.survey.model.StartStep

class MainActivity : AppCompatActivity(), SurveyCallbacks {
    private lateinit var binding: ActivityMainBinding
    private val surveyViewModel: SurveyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val steps = listOf(
            StartStep(0,
                "Welcome to our Amazing survey",
                "Get ready to get your socks blown off",
                "Start"
            ),
            BooleanStep(1,
                "This is a boolean question",
                "The user has to answer one choice"
            ),
            MultiSelectionStep(2,
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
            EndStep(3,
                "Thank you for taking this journey",
                "Those were some of the features",
                "Finish"
            )
        )
        val config = SurveyConfigs(this, this,
            UtilityText("Next", "Cancel"), this)
        binding.surveyView.start(steps, config, surveyViewModel)
    }

    override fun finished(answers: HashMap<Int, Result>?) {
        answers?.forEach {

            when(it.value.type) {
                Result.MULTI -> {
                    val stuff = it.value as MultiChoiceResult
                    Log.e("TAG", "key: ${it.key} answers: ${stuff.answers} start: ${stuff.startDate} end: ${stuff.endDate}")
                }
                else -> {
                    val stuff = it.value as BooleanResult
                    Log.e("TAG", "key: ${it.key} answers: ${stuff.answer} start: ${stuff.startDate} end: ${stuff.endDate}")
                }
            }
        }
    }
}