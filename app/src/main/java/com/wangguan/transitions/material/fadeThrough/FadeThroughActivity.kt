package com.wangguan.transitions.material.fadeThrough

import android.os.Bundle
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.transition.MaterialFadeThrough
import com.wangguan.transitions.R
import kotlinx.android.synthetic.main.activity_fade_through.*

class FadeThroughActivity : AppCompatActivity() {

    private val fadeThrough = MaterialFadeThrough.create().apply {
        duration = 1000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fade_through)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fade_through, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.refresh) {
            refreshContent()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun refreshContent() {
        container.visibility = View.GONE
        TransitionManager.beginDelayedTransition(container, fadeThrough)
        container.visibility = View.VISIBLE
    }
}
