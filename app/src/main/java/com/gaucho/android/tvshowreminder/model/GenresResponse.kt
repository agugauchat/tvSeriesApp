package com.gaucho.android.tvshowreminder.model

import com.google.gson.annotations.SerializedName

class GenresResponse {
    @SerializedName("genres")
    var genres: List<Genre>? = null
}