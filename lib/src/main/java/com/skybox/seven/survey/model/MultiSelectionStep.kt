package com.skybox.seven.survey.model

import androidx.fragment.app.Fragment
import com.skybox.seven.survey.fragments.MultiSelectionFragment
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
class MultiSelectionStep(override val id: Int,
                         val title: String,
                         private val subTile: String,
                         private val answers: ArrayList<String>
): Step {

    override fun getFragment(): Fragment {
        return MultiSelectionFragment.newInstance(id, title, subTile, answers)
    }
}