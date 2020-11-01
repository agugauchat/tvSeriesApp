package com.gaucho.android.tvshowreminder.networking

import com.gaucho.android.tvshowreminder.model.TVSeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TVSeriesApi {
    @GET("/3/tv/popular")
    fun getTVSeriesList(
        @Query("api_key") apiKey: String?,
        @Query("page") page: String? = null
    ): Call<TVSeriesResponse>
}