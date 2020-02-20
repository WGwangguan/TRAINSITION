package com.wangguan.transitions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.frg_cross_fade.view.*
import kotlinx.android.synthetic.main.frg_reveal.view.*
import kotlin.math.hypot

class VisibilityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        layout_content.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate()
                .setStartDelay(500)
                .alpha(1f)
                .duration = 2000
        }

        progress_circular
            .animate()
            .alpha(0f)
            .setDuration(2000)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    progress_circular.visibility = View.GONE
                }
            })

        text_menu.setOnClickListener {
            // get the center for the clipping circle
            val cx = it.width / 2
            val cy = it.height / 2

            // get the final radius for the clipping circle
            val finalRadius = hypot((layout_items.width).toDouble(), (layout_items.height).toDouble()).toFloat()

            // create the animator for this view (the start radius is zero)
            val anim =
                ViewAnimationUtils.createCircularReveal(layout_items, cx, cy, 0f, finalRadius).apply { duration = 1000 }
            Log.i("ddd", "anim radius $finalRadius")
            // make the view visible and start the animation
            layout_items.visibility = View.VISIBLE
            anim.start()
        }

        text_close.setOnClickListener {
            // get the center for the clipping circle
            val cx = it.width / 2
            val cy = it.height / 2

            // get the final radius for the clipping circle
            val finalRadius = hypot((layout_items.width).toDouble(), (layout_items.height).toDouble()).toFloat()


            // create the animator for this view (the start radius is zero)
            val anim =
                ViewAnimationUtils.createCircularReveal(layout_items, cx, cy, finalRadius, 0f).apply { duration = 1000 }
            // make the view visible and start the animation
            anim.apply {
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        layout_items.visibility = View.INVISIBLE
                    }
                })
                start()
            }
        }

    }

}
