package com.skybox.seven.survey

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.survey.answer.Result
import com.skybox.seven.survey.config.UtilityText

class SurveyViewModel : ViewModel() {
    val previous: MutableLiveData<Boolean> = MutableLiveData()
    val currentPage: MutableLiveData<Int> = MutableLiveData()
    val answers: MutableLiveData<Result> = MutableLiveData()

    val utilityText: MutableLiveData<UtilityText> = MutableLiveData()
    val goToNext: MutableLiveData<Boolean> = MutableLiveData()
}