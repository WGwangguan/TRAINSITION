package com.wangguan.transitions.material.container

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.Fade
import android.transition.TransitionSet
import androidx.appcompat.app.AppCompatActivity
import com.wangguan.transitions.R
import com.wangguan.transitions.load
import com.wangguan.transitions.onEnd
import kotlinx.android.synthetic.main.content_fab.*

class ImageDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra("url")
        val bigUrl = intent.getStringExtra("bigUrl")

        window.sharedElementEnterTransition = TransitionSet()
            .addTransition(ChangeImageTransform())
            .addTransition(ChangeBounds())
            .apply {
                onEnd { image.load(bigUrl) }
            }

        window.enterTransition = Fade()

        setContentView(R.layout.activity_image_detail)
        postponeEnterTransition()
        image.load(url, true) {
            startPostponedEnterTransition()
        }

    }
}
