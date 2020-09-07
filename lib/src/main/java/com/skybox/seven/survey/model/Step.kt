package com.skybox.seven.survey.model

import androidx.fragment.app.Fragment

interface Step {
    val id: Int
    fun getFragment(): Fragment
}