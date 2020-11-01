package com.gaucho.android.tvshowreminder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaucho.android.tvshowreminder.model.TVSeriesResponse
import com.gaucho.android.tvshowreminder.networking.TVSeriesRepository

class TVSeriesViewModel : ViewModel() {
    private var mutableLiveData: MutableLiveData<TVSeriesResponse>? = null
    lateinit var tvSeriesRepository: TVSeriesRepository
    fun init() {
        if (mutableLiveData != null) {
            return
        }
        tvSeriesRepository = TVSeriesRepository.instance!!
        // ToDo -> Change for api key
        mutableLiveData = tvSeriesRepository.getTVSeries("1234")
    }

    fun getTVSeriesRepository(): LiveData<TVSeriesResponse>? {
        return mutableLiveData
    }
}