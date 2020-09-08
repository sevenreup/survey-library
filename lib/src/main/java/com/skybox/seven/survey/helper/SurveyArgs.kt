package com.skybox.seven.survey.helper

import android.os.Bundle
import com.skybox.seven.survey.config.UtilityText
import com.skybox.seven.survey.model.Step

private const val STEPS = "steps"
private const val UTILITY = "utility"
class SurveyArgs(val bundle: Bundle) {
    var steps: ArrayList<Step>? = null
    var utilityText: UtilityText? = null

    init {
        bundle.let {
            steps = it.getParcelableArrayList(STEPS)
            utilityText = it.getParcelable(UTILITY)
        }
    }

    companion object {
        @JvmStatic
        fun putGenerics(steps: ArrayList<Step>, utilityText: UtilityText): Bundle = Bundle().apply {
            putParcelableArrayList(STEPS, steps)
            putParcelable(UTILITY, utilityText)
        }
    }
}