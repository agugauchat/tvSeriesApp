package com.gaucho.android.tvshowreminder

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.gaucho.android.tvshowreminder.model.TVSerie
import com.gaucho.android.tvshowreminder.utils.setupTextView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in, R.anim.nothing)
        setContentView(R.layout.activity_detail)
        val tvSerie = intent.getSerializableExtra(EXTRA_PARAMS) as TVSerie
        show(tvSerie)
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.nothing, R.anim.slide_out)
    }

    private fun show(serie: TVSerie) {
        supportActionBar?.hide()
        detail_name.setupTextView(serie.name)
        detail_overview.setupTextView(serie.overview)

        setYear(serie.firstAirDate)
        setupBackgroundImage(serie.backdropPath)
        setupBackgroundTint(serie.posterPath)
        setupSubscriptionButton(serie.name, serie.id)

        Picasso.get().load("https://image.tmdb.org/t/p/w300${serie.posterPath}").into(detail_top_image)
    }

    private fun setupSubscriptionButton(name: String?, id: Int?) {
        val sharedPreference =  getSharedPreferences("TV_SERIES", Context.MODE_PRIVATE)
        var searchResult = sharedPreference.getInt(name, -1)
        if (searchResult == -1) setupSubscribeButton() else setupSubscribedButton()

        subscribe_button.setOnClickListener {
            val editor = sharedPreference.edit()
            if (searchResult == -1) {
                searchResult = id!!
                editor.putInt(name, id)
                setupSubscribedButton()
            }
            else {
                searchResult = -1
                editor.remove(name)
                setupSubscribeButton()
            }
            editor.commit()
        }
    }

    private fun setupSubscribeButton() {
        subscribe_button.setupTextView(getString(R.string.subscribe))
        subscribe_button.setBackgroundResource(R.drawable.button_background);
    }

    private fun setupSubscribedButton() {
        subscribe_button.setupTextView(getString(R.string.subscribed))
        subscribe_button.setBackgroundResource(R.drawable.disabled_button_background);
    }

    private fun setYear(date: Date?) {
        var year : String? = null
        date?.let {
            val cal: Calendar = Calendar.getInstance()
            cal.time = it
            year = cal.get(Calendar.YEAR).toString()
        }

        detail_release_year.setupTextView(year)
    }

    private fun setupBackgroundImage(backdropPath: String?) {
        Picasso.get().load("https://image.tmdb.org/t/p/w780${backdropPath}").into(detail_background_image)
        detail_background_image.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)})
        detail_background_image.alpha = 0.5F
    }

    private fun setupBackgroundTint(posterPath: String?) {
        Picasso.get().load("https://image.tmdb.org/t/p/w300${posterPath}").into(object : com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: LoadedFrom?) {
                bitmap?.let {
                    val palette: Palette = Palette.from(bitmap).generate()
                    val dominantColor = palette.getDominantColor(getColor(R.color.card_gradient_start))
                    background_transparency.setBackgroundColor(dominantColor)
                    background_transparency.alpha = 0.7F
                };
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
        })
    }

    companion object {
        const val EXTRA_PARAMS = "TV_SERIE"

        fun start(context: Context, tvSerie: TVSerie) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_PARAMS, tvSerie)
            context.startActivity(intent)
        }
    }
}