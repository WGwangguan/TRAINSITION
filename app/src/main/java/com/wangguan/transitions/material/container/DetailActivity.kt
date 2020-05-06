package com.wangguan.transitions.material.container

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialContainerTransform.FADE_MODE_CROSS
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback
import com.wangguan.transitions.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        findViewById<View>(android.R.id.content).transitionName = "shared_element_container"

        // Attach a callback used to receive the shared elements from Activity A to be
        // used by the container transform transition.
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

        // Set this Activity’s enter and return transition to a MaterialContainerTransform
        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            fadeMode = FADE_MODE_CROSS

            // Since View to View transforms often are not transforming into full screens,
            // remove the transition's scrim.
//            scrimColor = Color.TRANSPARENT
            duration = 1000L
        }
        window.sharedElementsUseOverlay = true
        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            fadeMode = FADE_MODE_CROSS
            duration = 1000L


            // Since View to View transforms often are not transforming into full screens,
            // remove the transition's scrim.
//            scrimColor = Color.TRANSPARENT
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

    }
}
