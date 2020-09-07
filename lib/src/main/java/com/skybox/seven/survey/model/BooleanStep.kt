package com.skybox.seven.survey.model

import androidx.fragment.app.Fragment
import com.skybox.seven.survey.fragments.BooleanFragment
import java.util.*

class BooleanStep( override val id: Int,
                   val title: String,
                  private val subTile: String
): Step {

    override fun getFragment(): Fragment {
        return BooleanFragment.newInstance(id, title, subTile)
    }
}