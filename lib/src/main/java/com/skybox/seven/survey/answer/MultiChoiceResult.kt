package com.skybox.seven.survey.answer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@Parcelize
data class MultiChoiceResult(override var id: Int = 0,
                             override var startDate: Date = Date(),
                             override var endDate: Date = Date(),
                             var answers: HashMap<Int, String> = HashMap(),
                             override val type: Int = Result.MULTI
): Result {
}

@Parcelize
data class ChoiceData(var pos: Int, var text: String) : Parcelable