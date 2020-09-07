package com.skybox.seven.survey.helper

import android.os.Bundle

private const val TITLE = "title"
private const val SUBTITLE = "subtitle"
private const val BUTTON_TITLE = "button_title"

class BasicFragmentArgs(val bundle: Bundle) {
    var title: String? = null
    var subTitle: String? = null
    var nextTitle: String? = null

    init {
        bundle.let {
            title = it.getString(TITLE)
            subTitle = it.getString(SUBTITLE)
            nextTitle = it.getString(BUTTON_TITLE)
        }
    }

    companion object {
        @JvmStatic
        fun putGenerics(title: String, subTitle: String, buttonText: String): Bundle = Bundle().apply {
            putString(TITLE, title)
            putString(SUBTITLE, subTitle)
            putString(BUTTON_TITLE, buttonText)
        }
    }
}