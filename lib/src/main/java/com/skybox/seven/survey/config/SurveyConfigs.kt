package com.skybox.seven.survey.config

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.skybox.seven.survey.helper.SurveyCallbacks

data class SurveyConfigs(val fa: FragmentActivity,
                         val lifecycleOwner: LifecycleOwner,
                         val utilityText: UtilityText,
                         val callbacks: SurveyCallbacks)

data class UtilityText(val next: String, val cancel: String)