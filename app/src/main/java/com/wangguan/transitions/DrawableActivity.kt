package com.wangguan.transitions

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_vector_drawable.*

class DrawableActivity : AppCompatActivity() {

    private var play = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vector_drawable)

        val action = {

            if (!play) {
                imageNormal.setImageResource(R.drawable.play_pause)
                imageMix.setImageResource(R.drawable.mix_play_pause)
            } else {
                imageNormal.setImageResource(R.drawable.pause_play)
                imageMix.setImageResource(R.drawable.mix_pause_play)
            }
            play = !play
            (imageNormal.drawable as AnimatedVectorDrawable).start()
            (imageMix.drawable as AnimatedVectorDrawable).start()
        }

        imageNormal.setOnClickListener { action() }
        imageMix.setOnClickListener { action() }

        imageNormal.clearAnimation()

    }
}
