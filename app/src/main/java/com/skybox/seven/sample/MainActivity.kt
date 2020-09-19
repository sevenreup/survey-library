package com.skybox.seven.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.skybox.seven.sample.databinding.ActivityMainBinding
import com.skybox.seven.survey.SurveyView
import com.skybox.seven.survey.SurveyViewModel
import com.skybox.seven.survey.answer.BooleanResult
import com.skybox.seven.survey.answer.MultiChoiceResult
import com.skybox.seven.survey.answer.Result
import com.skybox.seven.survey.config.SurveyConfigs
import com.skybox.seven.survey.config.UtilityText
import com.skybox.seven.survey.helper.SurveyCallbacks
import com.skybox.seven.survey.model.*

class MainActivity : AppCompatActivity(), SurveyCallbacks {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val steps = arrayListOf(
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
            ArrayBooleanStep(3,
                "This is a listed response",
                "this ",
                arrayListOf("one",
                    "two",
                    "three",
                    "four",
                    "five")),
            TextStep(3, "This is a text Question",
                "Please choose and answer in the text box", true,
                "Answer Here"),
            EndStep(4,
                "Thank you for taking this journey",
                "Those were some of the features",
                "Finish"
            )
        )
        val config = UtilityText("Next", "Cancel", "Yes", "No");
        val fragment = SurveyView.newInstance(steps, config)
        supportFragmentManager.beginTransaction().add(R.id.survey_view, fragment).commit()
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

    override fun surveyClosed() {
        supportFragmentManager.beginTransaction().replace(R.id.survey_view, FinishedFragment()).commit()
    }
}