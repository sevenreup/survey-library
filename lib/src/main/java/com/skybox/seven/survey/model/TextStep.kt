package com.skybox.seven.survey.model

import androidx.fragment.app.Fragment
import com.skybox.seven.survey.fragments.TextFragment
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TextStep(override val id: Int,
                    val title: String,
                    private val subTile: String,
                    private val boolean: Boolean = false,
                    private val hint: String = "Hint"): Step {
    override fun getFragment(): Fragment {
        return TextFragment.newInstance(id, title, subTile, true, hint)
    }
}