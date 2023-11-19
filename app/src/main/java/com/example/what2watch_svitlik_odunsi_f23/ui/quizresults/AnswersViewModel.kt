package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnswersViewModel : ViewModel() {
    val answersList: MutableLiveData<AnswersData> = MutableLiveData()
}
