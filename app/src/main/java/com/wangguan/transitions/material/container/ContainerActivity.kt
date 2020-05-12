package com.wangguan.transitions.material.container

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.graphics.Path
import android.graphics.PointF
import android.os.Bundle
import android.transition.*
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback
import com.wangguan.transitions.R
import kotlinx.android.synthetic.main.activity_container.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)


        card.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)

            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                it,
                "shared_element_container"// The transition name to be matched in Activity B.
            )
            startActivity(intent, options.toBundle())
        }

        fab_left.setOnClickListener {
            val intent = Intent(this, FabActivity::class.java)

            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                android.util.Pair<View, String>(
                    it,
                    "fab"
                )// The transition name to be matched in Activity B.
            )
            startActivity(intent, options.toBundle())
        }

        val transformExpand = MaterialContainerTransform().apply {
            // Manually tell the container transform which Views to transform between.
            startView = fab
            endView = content

            // Optionally add a curved path to the transform
            pathMotion = MyPathMotion()

            // Since View to View transforms often are not transforming into full screens,
            // remove the transition's scrim.
            scrimColor = Color.TRANSPARENT
            duration = 1000L
        }

        val transformCollase = MaterialContainerTransform().apply {
            // Manually tell the container transform which Views to transform between.
            startView = content
            endView = fab

            // Optionally add a curved path to the transform
            pathMotion = MaterialArcMotion()

            // Since View to View transforms often are not transforming into full screens,
            // remove the transition's scrim.
            scrimColor = Color.TRANSPARENT
            duration = 1000L


        }

        fab.setOnClickListener {
            TransitionManager.beginDelayedTransition(container, transformExpand)
            fab.visibility = View.GONE
            content.visibility = View.VISIBLE
        }

        content.setOnClickListener {
            TransitionManager.beginDelayedTransition(container, transformCollase)
            fab.visibility = View.VISIBLE
            content.visibility = View.GONE
        }


        val change = TransitionSet().apply {
            addTransition(ChangeBounds())
//            addTransition(BackgroundColorTransition())
        }.apply { duration = 2000L }

        val aa = MaterialContainerTransform().apply {
            // Manually tell the container transform which Views to transform between.
            startView = text_top
            endView = text_bottom

            // Optionally add a curved path to the transform
            pathMotion = MaterialArcMotion()

            // Since View to View transforms often are not transforming into full screens,
            // remove the transition's scrim.
            scrimColor = Color.TRANSPARENT
            duration = 1000L


        }

        fab_bottom.setOnClickListener {
            TransitionManager.beginDelayedTransition(layout_text_container, Explode())
            if (text_top.visibility == View.VISIBLE) {
                text_top.visibility = View.GONE
                text_bottom.visibility = View.VISIBLE
            } else {
                text_top.visibility = View.VISIBLE
                text_bottom.visibility = View.GONE
            }
        }

    }

    class MyPathMotion : PathMotion() {

        override fun getPath(startX: Float, startY: Float, endX: Float, endY: Float): Path {
            val path = Path()
            path.moveTo(startX, startY)

            val controlPoint1 = PointF((startX - endX) / 2f + endX, startY)
            val temp = PointF(controlPoint1.x, (startY - endY) / 2f + endY)
            val controlPoint2 = PointF(temp.x, endY)
            path.quadTo(controlPoint1.x, controlPoint1.y, temp.x, temp.y)
            path.quadTo(controlPoint2.x, controlPoint2.y, endX, endY)

            Log.i(
                "ddd",
                "$startX  $startY | $endX  $endY | ${controlPoint1.x}  ${controlPoint1.y} | ${temp.x}  ${temp.y} | ${controlPoint2.x}  ${controlPoint2.y}"
            )
            return path
        }
    }
}
