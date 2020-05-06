package com.wangguan.transitions.material.container

import android.graphics.Path
import android.graphics.PointF
import android.os.Bundle
import android.transition.*
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.transition.MaterialContainerTransform
import com.wangguan.transitions.R

import kotlinx.android.synthetic.main.activity_fab.*

class FabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        window.sharedElementEnterTransition = ChangeBounds().apply {
            pathMotion = MyPathMotion()
            duration = 2000L
        }

        window.enterTransition = Explode().apply { duration = 2000L }
        window.returnTransition = Fade().apply { duration = 2000L }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fab)
        setSupportActionBar(toolbar)

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
