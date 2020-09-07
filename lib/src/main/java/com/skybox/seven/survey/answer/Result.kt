package com.skybox.seven.survey.answer

import android.os.Parcelable
import java.util.*

interface Result: Parcelable {
    val id: Int
    val startDate: Date
    var endDate: Date
    val type: Int
    companion object {
        val BOOL = 0
        val MULTI = 1
    }
}