package com.skybox.seven.survey.model

import android.os.Parcelable
import androidx.fragment.app.Fragment

interface Step: Parcelable {
    val id: Int
    fun getFragment(): Fragment
}