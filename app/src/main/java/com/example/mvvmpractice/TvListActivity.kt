package com.example.mvvmpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class TvListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("YO", "MainActivity onCreate")

        initFragment()

    }


    private fun initFragment() {
        Log.d("YO", "initFragment")

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, TvListFragment.newInstance()).commitNow()
    }

}