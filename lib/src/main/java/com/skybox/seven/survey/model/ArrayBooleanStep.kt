package com.skybox.seven.survey.model

import androidx.fragment.app.Fragment
import com.skybox.seven.survey.fragments.ArrayBooleanFragment
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArrayBooleanStep(override val id: Int,
                            val title: String,
                            private val subTile: String,
                            private val list: ArrayList<String>
): Step {

    override fun getFragment(): Fragment {
        return ArrayBooleanFragment.newInstance(id, title, subTile, list)
    }
}