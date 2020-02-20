package com.wangguan.transitions

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import kotlinx.android.synthetic.main.activity_spring.*

class SpringActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spring)

        val iconForce = SpringForce(0f).apply {
            dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            stiffness = SpringForce.STIFFNESS_LOW
        }
        val iconAnimation = SpringAnimation(
            image_icon,
            DynamicAnimation.TRANSLATION_X
        ).setSpring(iconForce)

        val xFollowForce = SpringForce().apply {
            dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            stiffness = SpringForce.STIFFNESS_LOW
        }

        val yFollowForce = SpringForce().apply {
            dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            stiffness = SpringForce.STIFFNESS_LOW
        }

        val (xAnimation, yAnimation) = image2.let {
            SpringAnimation(it, DynamicAnimation.TRANSLATION_X).setSpring(xFollowForce) to
                    SpringAnimation(it, DynamicAnimation.TRANSLATION_Y).setSpring(yFollowForce)
        }

        var iconLeftDiff = 0f

        image_icon.setOnTouchListener { v, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    iconLeftDiff = event.rawX - v.x
                    iconAnimation.cancel()
                }
                MotionEvent.ACTION_MOVE -> {
                    image_icon.x = event.rawX - iconLeftDiff
                }
                MotionEvent.ACTION_UP -> {
                    iconAnimation.start()
                }
            }

            true
        }

        var diffLeft = 0f
        var diffTop = 0f

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val sw = displayMetrics.widthPixels

        image1.setOnTouchListener { v, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    diffLeft = event.rawX - v.x
                    diffTop = event.rawY - v.y
                }
                MotionEvent.ACTION_MOVE -> {
                    var x = event.rawX - diffLeft
                    var y = event.rawY - diffTop
                    // Control the edge
                    if (x < 0) x = 0f else if (x > sw - v.width) x = sw - v.width.toFloat()
                    if (y < text_divider.bottom) y =
                        text_divider.bottom.toFloat() else if (y > group_damping.top - v.height) y =
                        (group_damping.top - v.height).toFloat()

                    image1.x = x
                    image1.y = y

                    xAnimation.animateToFinalPosition(x - v.left)
                    yAnimation.animateToFinalPosition(y - v.top)
                }
                MotionEvent.ACTION_UP -> {
                }
            }

            true
        }

        group_damping.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                damping_high.id -> {
                    xFollowForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
                    yFollowForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
                    iconForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
                }
                damping_medium.id -> {
                    xFollowForce.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
                    yFollowForce.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
                    iconForce.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
                }
                damping_low.id -> {
                    xFollowForce.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                    yFollowForce.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                    iconForce.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                }
                damping_no.id -> {
                    xFollowForce.dampingRatio = SpringForce.DAMPING_RATIO_NO_BOUNCY
                    yFollowForce.dampingRatio = SpringForce.DAMPING_RATIO_NO_BOUNCY
                    iconForce.dampingRatio = SpringForce.DAMPING_RATIO_NO_BOUNCY
                }
            }
        }

        group_stiffness.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                stiffness_high.id -> {
                    xFollowForce.stiffness = SpringForce.STIFFNESS_HIGH
                    yFollowForce.stiffness = SpringForce.STIFFNESS_HIGH
                    iconForce.stiffness = SpringForce.STIFFNESS_HIGH
                }
                stiffness_medium.id -> {
                    xFollowForce.stiffness = SpringForce.STIFFNESS_MEDIUM
                    yFollowForce.stiffness = SpringForce.STIFFNESS_MEDIUM
                    iconForce.stiffness = SpringForce.STIFFNESS_MEDIUM
                }
                stiffness_low.id -> {
                    xFollowForce.stiffness = SpringForce.STIFFNESS_LOW
                    yFollowForce.stiffness = SpringForce.STIFFNESS_LOW
                    iconForce.stiffness = SpringForce.STIFFNESS_LOW
                }
                stiffness_very_low.id -> {
                    xFollowForce.stiffness = SpringForce.STIFFNESS_VERY_LOW
                    yFollowForce.stiffness = SpringForce.STIFFNESS_VERY_LOW
                    iconForce.stiffness = SpringForce.STIFFNESS_VERY_LOW
                }
            }
        }

    }
}
