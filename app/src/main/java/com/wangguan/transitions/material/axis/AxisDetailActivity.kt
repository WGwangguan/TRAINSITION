package com.wangguan.transitions.material.axis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.transition.MaterialSharedAxis
import com.wangguan.transitions.R

class AxisDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val enter = MaterialSharedAxis.create(MaterialSharedAxis.X, true).apply {
            addTarget(R.id.container)
            duration = 1000L
        }
        window.enterTransition = enter

        val back = MaterialSharedAxis.create(MaterialSharedAxis.X, false).apply {

            // Only run the transition on the contents of this activity, excluding
            // system bars or app bars if provided by the app’s theme.
            addTarget(R.id.container)
            duration = 1000L
        }

        window.returnTransition = back

        // Allow Activity A’s exit transition to play at the same time as this Activity’s
        // enter transition instead of playing them sequentially.
        window.allowEnterTransitionOverlap = true

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_axis_detail)

    }
}
