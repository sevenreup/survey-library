package com.skybox.seven.survey.model

import androidx.fragment.app.Fragment
import com.skybox.seven.survey.fragments.StartStepFragment
import java.util.*

class StartStep(override val id: Int = 0,
                val title: String,
                private val subTile: String,
                private val buttonText: String
) : Step {

    override fun getFragment(): Fragment {
        return StartStepFragment.newInstance(title, subTile, buttonText)
    }
}