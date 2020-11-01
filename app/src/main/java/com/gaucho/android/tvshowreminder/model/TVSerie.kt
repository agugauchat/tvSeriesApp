package com.gaucho.android.tvshowreminder.model

import com.google.gson.annotations.SerializedName

class TVSerie {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("poster_path")
    var posterPath: String? = null
}