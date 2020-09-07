package com.skybox.seven.survey.answer

import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class BooleanResult(override var id: Int = 0,
                         override var startDate: Date = Date(),
                         override var endDate: Date = Date(),
                         var answer: Boolean = false,
                         override val type: Int = Result.BOOL
): Result