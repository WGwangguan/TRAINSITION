package com.wangguan.transitions

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_vector_drawable.setOnClickListener {
            startActivity(Intent(this, DrawableActivity::class.java))
        }

        btn_visibility.setOnClickListener {
            startActivity(Intent(this, VisibilityActivity::class.java))
        }

        btn_layout.setOnClickListener {
            startActivity(Intent(this, LayoutActivity::class.java))
        }

        btn_activity.setOnClickListener {
            startActivity(
                Intent(this, TransitionActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(
                    this
//                    it, "image"
                ).toBundle()
//            ActivityOptions.makeScaleUpAnimation(it,0,0,it.width,it.height).toBundle()
            )
        }

    }
}
