package com.gaucho.android.tvshowreminder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaucho.android.tvshowreminder.model.GenresResponse
import com.gaucho.android.tvshowreminder.model.TVSeriesResponse
import com.gaucho.android.tvshowreminder.networking.TVSeriesRepository

class TVSeriesViewModel : ViewModel() {
    private var mutableLiveData: MutableLiveData<TVSeriesResponse>? = null
    private var genresMutableLiveData: MutableLiveData<GenresResponse>? = null
    lateinit var tvSeriesRepository: TVSeriesRepository
    fun init() {
        if (mutableLiveData != null) {
            return
        }
        tvSeriesRepository = TVSeriesRepository.instance!!
        mutableLiveData = tvSeriesRepository.getTVSeries()
        genresMutableLiveData = tvSeriesRepository.getGenres()
    }

    fun getTVSeriesRepository(): LiveData<TVSeriesResponse>? {
        return mutableLiveData
    }

    fun getGenresRepository(): LiveData<GenresResponse>? {
        return genresMutableLiveData
    }
}