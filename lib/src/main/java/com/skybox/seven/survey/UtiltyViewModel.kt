package com.skybox.seven.survey

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UtiltyViewModel: ViewModel() {
    val previous: MutableLiveData<Boolean> = MutableLiveData()
}