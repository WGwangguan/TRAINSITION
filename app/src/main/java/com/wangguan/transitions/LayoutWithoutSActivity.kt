package com.wangguan.transitions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.transition.Fade
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_transiton_without_s.*

class LayoutWithoutSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transiton_without_s)

        val inflateTransition = TransitionInflater.from(this).inflateTransition(R.transition.layout_transform)

        btn_add.setOnClickListener {
            val text = TextView(this).apply {
                text = "I am new"
            }

            TransitionManager.beginDelayedTransition(layout_items_container, Fade())
            layout_items_container.addView(text)
        }

        btn_remove.setOnClickListener {
            TransitionManager.beginDelayedTransition(layout_items_container, inflateTransition)
            if (layout_items_container.childCount == 0) return@setOnClickListener
            layout_items_container.removeViewAt(0)
        }
    }
}
