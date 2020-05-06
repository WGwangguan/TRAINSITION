package com.wangguan.transitions.material.axis

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.transition.MaterialSharedAxis
import com.wangguan.transitions.R
import com.wangguan.transitions.material.custom.BackgroundColorTransition
import kotlinx.android.synthetic.main.activity_axis.*

class AxisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val exit = MaterialSharedAxis.create(MaterialSharedAxis.X, true).apply {

            // Only run the transition on the contents of this activity, excluding
            // system bars or app bars if provided by the app’s theme.
            addTarget(R.id.container)
            duration = 1000L
        }
        window.exitTransition = exit

        val reenter = MaterialSharedAxis.create(MaterialSharedAxis.X, false).apply {

            // Only run the transition on the contents of this activity, excluding
            // system bars or app bars if provided by the app’s theme.
            addTarget(R.id.container)
            duration = 1000L
        }

        window.reenterTransition = reenter

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_axis)

        // Set up a new MaterialSharedAxis in the specified ais and direction.
        val sharedAxis = MaterialSharedAxis.create(MaterialSharedAxis.Y, true).apply {
            duration = 1000
        }

        val set = TransitionSet().addTransition(sharedAxis).addTransition(BackgroundColorTransition().apply { duration = 1000L })

        btn_first.setOnClickListener {

            // Begin watching for changes in the View hierarchy.
            TransitionManager.beginDelayedTransition(container, set)

            // Make any changes to the hierarchy to be animated by the shared axis transition.
            text_first.visibility = View.VISIBLE
            text_second.visibility = View.GONE
        }

        btn_second.setOnClickListener {
            // Begin watching for changes in the View hierarchy.
            TransitionManager.beginDelayedTransition(container, set)

            // Make any changes to the hierarchy to be animated by the shared axis transition.
            text_first.visibility = View.GONE
            text_second.visibility = View.VISIBLE
        }

        btn_next.setOnClickListener {
            //            startActivity(Intent(this, AxisDetailActivity::class.java))
            val intent = Intent(this, AxisDetailActivity::class.java)

            val options = ActivityOptions.makeSceneTransitionAnimation(this)
            startActivity(intent, options.toBundle())
        }

    }
}
