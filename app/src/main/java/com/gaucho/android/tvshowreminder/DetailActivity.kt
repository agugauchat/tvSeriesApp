package com.gaucho.android.tvshowreminder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gaucho.android.tvshowreminder.model.TVSerie
import com.gaucho.android.tvshowreminder.utils.setupTextView
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val tvSerie = intent.getSerializableExtra(EXTRA_PARAMS) as TVSerie
        show(tvSerie)
    }

    private fun show(serie: TVSerie) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detail_name.setupTextView(serie.name)
        detail_overview.setupTextView(serie.overview)
        var year : String? = null
        serie.firstAirDate?.let { date ->
            val cal: Calendar = Calendar.getInstance()
            cal.time = date
            year = cal.get(Calendar.YEAR).toString()
        }
        detail_release_year.setupTextView(year)
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