package com.gaucho.android.tvshowreminder.networking

import com.gaucho.android.tvshowreminder.model.GenresResponse
import com.gaucho.android.tvshowreminder.model.TVSeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TVSeriesApi {

    companion object {
        // ToDo -> Change for api key https://developers.themoviedb.org/3/getting-started/introduction
        const val API_KEY = "1234"
    }

    @GET("/3/tv/popular")
    fun getTVSeriesList(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: String? = null
    ): Call<TVSeriesResponse>

    @GET("/3/genre/tv/list")
    fun getGenresList(
        @Query("api_key") apiKey: String = API_KEY
    ): Call<GenresResponse>
}