package com.gaucho.android.tvshowreminder.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class TVSerie : Serializable {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("first_air_date")
    var firstAirDate: Date? = null

    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null
}