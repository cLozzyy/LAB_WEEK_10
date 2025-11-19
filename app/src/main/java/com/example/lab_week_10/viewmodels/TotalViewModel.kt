package com.example.lab_week_10.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TotalViewModel: ViewModel() {

    // LiveData yang dapat dimodifikasi (hanya di ViewModel)
    private val _total = MutableLiveData<Int>()
    // LiveData publik (hanya dapat dibaca/diobservasi)
    val total: LiveData<Int> = _total

    init {
        // Inisialisasi nilai awal
        _total.postValue(0)
    }

    fun incrementTotal() {
        // Perbarui nilai LiveData
        _total.postValue(_total.value?.plus(1))
    }
}