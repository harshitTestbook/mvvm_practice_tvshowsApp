package com.example.mvvmpractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmpractice.databinding.MoreTvShowsActivityBinding

class MoreTvShowsActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context, tvId: String) {
            val intent = Intent(context, MoreTvShowsActivity::class.java)
            val bundle = Bundle()
            bundle.putString("tvId", tvId)
            intent.putExtra("bundle", bundle)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val binding = MoreTvShowsActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val bundle = intent.getBundleExtra("bundle")

        initFragment(bundle!!)

    }

    private fun initFragment(bundle: Bundle) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag1, MoreTvShowsFragment.newInstance(bundle))
            .commitNow()
    }
}