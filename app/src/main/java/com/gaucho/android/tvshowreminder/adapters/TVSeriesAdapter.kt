package com.gaucho.android.tvshowreminder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gaucho.android.tvshowreminder.R
import com.gaucho.android.tvshowreminder.adapters.TVSeriesAdapter.TVSeriesViewHolder
import com.gaucho.android.tvshowreminder.model.TVSerie
import com.squareup.picasso.Picasso
import java.util.*

class TVSeriesAdapter(
    var context: Context,
    var series: ArrayList<TVSerie>
) : RecyclerView.Adapter<TVSeriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVSeriesViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_tv_serie, parent, false)
        return TVSeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TVSeriesViewHolder, position: Int) {
        holder.nameTVSerie.text = series[position].name.toString()
        Picasso.get().load("https://image.tmdb.org/t/p/w300${series[position].posterPath}").into(holder.imageTVSerie)
    }

    override fun getItemCount(): Int {
        return series.size
    }

    class TVSeriesViewHolder(itemView: View) : ViewHolder(itemView) {
        var nameTVSerie: TextView
        var imageTVSerie: ImageView

        init {
            nameTVSerie = itemView.findViewById(R.id.item_name)
            imageTVSerie = itemView.findViewById(R.id.item_image)
        }
    }
}