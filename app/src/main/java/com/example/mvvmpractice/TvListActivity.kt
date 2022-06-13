package com.example.mvvmpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TvListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initFragment()

    }


    private fun initFragment() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, TvListFragment.newInstance()).commitNow()
    }

}