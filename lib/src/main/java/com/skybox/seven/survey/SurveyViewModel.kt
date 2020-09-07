package com.skybox.seven.survey

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.survey.answer.Result
import com.skybox.seven.survey.config.UtilityText

class SurveyViewModel : ViewModel() {
    val previous: MutableLiveData<Boolean> = MutableLiveData()
    val next: MutableLiveData<Boolean> = MutableLiveData()
    val currentPage: MutableLiveData<Int> = MutableLiveData()
    val answers: MutableLiveData<HashMap<Int, Result>> = MutableLiveData(HashMap())

    val finish: MutableLiveData<Boolean> = MutableLiveData()
    val goToNext: MutableLiveData<Boolean> = MutableLiveData()
    val total: MutableLiveData<Int> = MutableLiveData()
}