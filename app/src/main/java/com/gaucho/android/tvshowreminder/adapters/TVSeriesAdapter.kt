package com.gaucho.android.tvshowreminder.adapters

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gaucho.android.tvshowreminder.DetailActivity
import com.gaucho.android.tvshowreminder.MainActivity.Companion.genresList
import com.gaucho.android.tvshowreminder.R
import com.gaucho.android.tvshowreminder.adapters.TVSeriesAdapter.TVSeriesViewHolder
import com.gaucho.android.tvshowreminder.model.Genre
import com.gaucho.android.tvshowreminder.model.TVSerie
import com.gaucho.android.tvshowreminder.utils.setupTextView
import com.squareup.picasso.Picasso
import java.util.*

class TVSeriesAdapter(var context: Context, var series: ArrayList<TVSerie>) : RecyclerView.Adapter<TVSeriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVSeriesViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_tv_serie, parent, false)
        return TVSeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TVSeriesViewHolder, position: Int) {
        val tvSerie = series[position]
        holder.nameTVSerie.setupTextView(tvSerie.name.toString())
        Picasso.get().load("https://image.tmdb.org/t/p/w300${tvSerie.backdropPath}").into(holder.imageTVSerie)
        holder.imageTVSerie.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)})

        holder.genresTVSerie.setupTextView(getGenreToShow(tvSerie.genreIds).toUpperCase(Locale.ROOT))
        holder.cardTVSerie.setOnClickListener {
            DetailActivity.start(it.context, tvSerie)
        }
    }

    private fun getGenreToShow(genreIds: List<Int>?) : String {

        val listOfGenres = genresList
        var genresTitle = ""
        if (!listOfGenres.isNullOrEmpty()) {
            var genre: Genre?
            // This is for show all the genres. Instead of this, it is better to visually show only the first
            /* tvSerie.genreIds?.forEach { item ->
                genre = listOfGenres.first { it.id == item}

                genre?.let {
                    genresTitle += "{it.name} "
                }
            }*/
            genreIds?.let { item ->
                genre = listOfGenres.first { it.id == item.firstOrNull()}
                genre?.let {
                    genresTitle += it.name
                }
            }
        }

        return genresTitle
    }

    override fun getItemCount(): Int {
        return series.size
    }

    class TVSeriesViewHolder(itemView: View) : ViewHolder(itemView) {
        var nameTVSerie: TextView
        var genresTVSerie: TextView
        var imageTVSerie: ImageView
        var cardTVSerie: CardView

        init {
            nameTVSerie = itemView.findViewById(R.id.item_name)
            genresTVSerie = itemView.findViewById(R.id.item_genre)
            imageTVSerie = itemView.findViewById(R.id.item_image)
            cardTVSerie = itemView.findViewById(R.id.card_item)
        }
    }
}