package com.gaucho.android.tvshowreminder.networking

import androidx.lifecycle.MutableLiveData
import com.gaucho.android.tvshowreminder.model.TVSeriesResponse
import com.gaucho.android.tvshowreminder.networking.RetrofitService.createService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVSeriesRepository {
    private val tvSeriesApi: TVSeriesApi = createService(TVSeriesApi::class.java)
    fun getTVSeries(key: String?): MutableLiveData<TVSeriesResponse> {
        val tvSeriesData = MutableLiveData<TVSeriesResponse>()
        tvSeriesApi.getTVSeriesList(key).enqueue(object : Callback<TVSeriesResponse> {
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