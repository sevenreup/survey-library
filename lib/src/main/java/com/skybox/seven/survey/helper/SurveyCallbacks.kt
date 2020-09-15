package com.skybox.seven.survey.helper

import com.skybox.seven.survey.answer.Result

interface SurveyCallbacks {
    fun finished(answers: HashMap<Int, Result>?)
    fun surveyClosed()
}