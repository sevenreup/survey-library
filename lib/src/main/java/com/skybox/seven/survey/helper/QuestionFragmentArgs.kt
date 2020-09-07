package com.skybox.seven.survey.helper

import android.os.Bundle

private const val ID = "id_main"
private const val TITLE = "title"
private const val SUBTITLE = "subtitle"

class QuestionFragmentArgs(val bundle: Bundle) {
    var title: String? = null
    var subTitle: String? = null
    var id: Int = 0

    init {
        bundle.let {
            title = it.getString(TITLE)
            subTitle = it.getString(SUBTITLE)
            id = it.getInt(ID)
        }
    }

    companion object {
        @JvmStatic
        fun putGenerics(id: Int, title: String, subTitle: String): Bundle = Bundle().apply {
            putString(TITLE, title)
            putString(SUBTITLE, subTitle)
            putInt(ID, id)
        }
    }
}