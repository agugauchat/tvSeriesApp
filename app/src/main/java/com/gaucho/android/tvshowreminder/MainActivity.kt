package com.gaucho.android.tvshowreminder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gaucho.android.tvshowreminder.adapters.TVSeriesAdapter
import com.gaucho.android.tvshowreminder.model.TVSerie
import com.gaucho.android.tvshowreminder.model.TVSeriesResponse
import com.gaucho.android.tvshowreminder.viewmodels.TVSeriesViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSeriesArrayList = ArrayList<TVSerie>()
    private var tvSeriesAdapter: TVSeriesAdapter? = null
    private var rvHeadline: RecyclerView? = null
    private var tvSeriesViewModel: TVSeriesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvHeadline = findViewById(R.id.rvTVSeries)
        tvSeriesViewModel =
            ViewModelProviders.of(this).get(TVSeriesViewModel::class.java)
        tvSeriesViewModel!!.init()
        tvSeriesViewModel!!.getTVSeriesRepository()?.observe(this, Observer { tvSeriesResponse: TVSeriesResponse ->
                val tvSeries = tvSeriesResponse.series.orEmpty()
                tvSeriesArrayList.addAll(tvSeries)
                tvSeriesAdapter!!.notifyDataSetChanged()
            })
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        if (tvSeriesAdapter == null) {
            tvSeriesAdapter = TVSeriesAdapter(this@MainActivity, tvSeriesArrayList)
            rvHeadline!!.layoutManager = LinearLayoutManager(this)
            rvHeadline!!.adapter = tvSeriesAdapter
            rvHeadline!!.itemAnimator = DefaultItemAnimator()
            rvHeadline!!.isNestedScrollingEnabled = true
        } else {
            tvSeriesAdapter!!.notifyDataSetChanged()
        }
    }
}