package com.gaucho.android.tvshowreminder.model

import com.google.gson.annotations.SerializedName

class TVSeriesResponse {
    @SerializedName("page")
    var page = 0

    @SerializedName("results")
    var series: List<TVSerie>? = null

    @SerializedName("total_results")
    var totalResults = 0

    @SerializedName("total_pages")
    var totalPages = 0

}