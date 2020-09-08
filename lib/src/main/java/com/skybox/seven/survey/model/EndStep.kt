package com.skybox.seven.survey.model

import androidx.fragment.app.Fragment
import com.skybox.seven.survey.fragments.EndStepFragment
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class EndStep(override val id: Int = -1,
              val title: String,
              private val subtitle: String,
              private val buttonText: String
) : Step {

    override fun getFragment(): Fragment {
        return EndStepFragment.newInstance(title, subtitle, buttonText)
    }
}