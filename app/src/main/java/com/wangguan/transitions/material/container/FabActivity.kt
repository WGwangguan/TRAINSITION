package com.wangguan.transitions.material.container

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Path
import android.graphics.PointF
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Explode
import android.transition.Fade
import android.transition.PathMotion
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.wangguan.transitions.R
import com.wangguan.transitions.load
import kotlinx.android.synthetic.main.activity_fab.*
import kotlinx.android.synthetic.main.content_fab.*

class FabActivity : AppCompatActivity() {
    private val url = "https://picsum.photos/100/70?image=188"

    private val bigUrl = "https://picsum.photos/1000/700?image=188"


    override fun onCreate(savedInstanceState: Bundle?) {

        window.sharedElementEnterTransition = ChangeBounds().apply {
            pathMotion = MyPathMotion()
        }

        window.enterTransition = Explode().apply { }
        window.returnTransition = Fade().apply { }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fab)
        setSupportActionBar(toolbar)

        image.load(url)

        image.setOnClickListener {
            Toast.makeText(this,"click",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ImageDetailActivity::class.java)
            intent.putExtra("url", url)
            intent.putExtra("bigUrl", bigUrl)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                it,
                it.transitionName
            )
            startActivity(intent, options.toBundle())
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

            return path
        }
    }

}
