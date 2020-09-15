package com.skybox.seven.survey.config

import android.os.Parcelable
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.skybox.seven.survey.helper.SurveyCallbacks
import kotlinx.android.parcel.Parcelize

data class SurveyConfigs(val fa: FragmentActivity,
                         val lifecycleOwner: LifecycleOwner,
                         val utilityText: UtilityText,
                         val callbacks: SurveyCallbacks)

@Parcelize
data class UtilityText(val next: String, val cancel: String, val yes: String, val no: String) : Parcelable