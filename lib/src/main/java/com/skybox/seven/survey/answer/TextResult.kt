package com.skybox.seven.survey.answer

import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class TextResult(
    override val id: Int = 0,
    override val startDate: Date = Date(),
    override var endDate: Date = Date(),
    override val type: Int = Result.TEXT,
    var text: String? = null,
    var answered: Boolean = false
): Result {
}