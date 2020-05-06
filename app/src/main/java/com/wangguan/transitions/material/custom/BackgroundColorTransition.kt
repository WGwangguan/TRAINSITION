package com.wangguan.transitions.material.custom

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.transition.Transition
import android.transition.TransitionValues
import android.util.Log
import android.view.View
import android.view.ViewGroup


/**
 * Created by WG on 2020-04-30.
 * Email: wg5329@163.com
 * Github: https://github.com/WGwangguan
 * Desc:
 */
class BackgroundColorTransition : Transition() {
    private val PROPNAME_BACKGROUND =
        "com.wangguan.transitions.material.custom.BackgroundColorTransition:background"

    override fun captureStartValues(transitionValues: TransitionValues?) {
        Log.i("ddd","captureStartValues ")
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues?) {
        Log.i("ddd","captureEndValues ")
        captureValues(transitionValues)
    }

    private fun captureValues(transitionValues: TransitionValues?) {
        transitionValues ?: return
        val view = transitionValues.view
        transitionValues.values[PROPNAME_BACKGROUND] = view.background
    }

    override fun createAnimator(
        sceneRoot: ViewGroup?,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        if (null == startValues || null == endValues) {
            return null
        }

        val view: View = endValues.view

        val startBackground = startValues.values[PROPNAME_BACKGROUND] as Drawable?
        val endBackground = endValues.values[PROPNAME_BACKGROUND] as Drawable?

        Log.i("ddd","createAnimator $startBackground  $endBackground")

        if (startBackground is ColorDrawable && endBackground is ColorDrawable) {
            val startColor = startBackground
            val endColor = endBackground

            Log.i("ddd","createAnimator color ${startColor.color}  ${endColor.color}")


            if (startColor.color != endColor.color) {
                val animator = ValueAnimator.ofObject(
                    ArgbEvaluator(),
                    startColor.color, endColor.color
                )
                animator.addUpdateListener { animation ->
                    val value = animation.animatedValue

                    if (null != value) {
                        Log.i("ddd","set backgroud $value")
                        view.setBackgroundColor(value as Int)
                    }
                }

                return animator
            }
        }

        return null
    }
}