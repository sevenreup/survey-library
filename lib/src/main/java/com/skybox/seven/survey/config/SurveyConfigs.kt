package com.skybox.seven.survey.config

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner

data class SurveyConfigs(val fa: FragmentActivity,
                         val lifecycleOwner: LifecycleOwner,
                         val utilityText: UtilityText)

data class UtilityText(val next: String, val cancel: String)