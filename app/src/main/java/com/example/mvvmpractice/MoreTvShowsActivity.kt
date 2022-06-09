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
            intent.putExtra("tvId", tvId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MoreTvShowsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val tvIdString = intent.getStringExtra("tvId")
//        binding.root.tvMainActivity2.text=tvIdString

        initFragment(intent)

    }

    private fun initFragment(intent: Intent) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag1, MoreTvShowsFragment.newInstance(intent))
            .commitNow()
    }
}