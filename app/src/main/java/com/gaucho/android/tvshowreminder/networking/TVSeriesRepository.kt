package com.gaucho.android.tvshowreminder.networking

import androidx.lifecycle.MutableLiveData
import com.gaucho.android.tvshowreminder.model.GenresResponse
import com.gaucho.android.tvshowreminder.model.TVSeriesResponse
import com.gaucho.android.tvshowreminder.networking.RetrofitService.createService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVSeriesRepository {
    private val tvSeriesApi: TVSeriesApi = createService(TVSeriesApi::class.java)
    fun getTVSeries(): MutableLiveData<TVSeriesResponse> {
        val tvSeriesData = MutableLiveData<TVSeriesResponse>()
        tvSeriesApi.getTVSeriesList().enqueue(object : Callback<TVSeriesResponse> {
            override fun onResponse(
                call: Call<TVSeriesResponse>,
                response: Response<TVSeriesResponse>
            ) {
                if (response.isSuccessful) {
                    tvSeriesData.value = response.body()
                }
            }

            override fun onFailure(
                call: Call<TVSeriesResponse>,
                t: Throwable
            ) {
                tvSeriesData.value = null
            }
        })
        return tvSeriesData
    }

    fun getGenres(): MutableLiveData<GenresResponse> {
        val genresData = MutableLiveData<GenresResponse>()
        tvSeriesApi.getGenresList().enqueue(object : Callback<GenresResponse> {
            override fun onResponse(
                call: Call<GenresResponse>,
                response: Response<GenresResponse>
            ) {
                if (response.isSuccessful) {
                    genresData.value = response.body()
                }
            }

            override fun onFailure(
                call: Call<GenresResponse>,
                t: Throwable
            ) {
                genresData.value = null
            }
        })
        return genresData
    }

    companion object {
        private var tvSeriesRepository: TVSeriesRepository? = null
        val instance: TVSeriesRepository?
            get() {
                if (tvSeriesRepository == null) {
                    tvSeriesRepository = TVSeriesRepository()
                }
                return tvSeriesRepository
            }
    }

}