package com.wangguan.transitions.material.custom

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.transition.Transition
import android.transition.TransitionValues
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by WG on 2020-04-30.
 * Email: wg5329@163.com
 * Github: https://github.com/WGwangguan
 * Desc:
 */
class TextColorTransition : Transition() {

    private val PROPNAME_TEXTCOLOR =
        "com.wangguan.transitions.material.custom.TextColorTransition:textColor"

    override fun captureStartValues(transitionValues: TransitionValues?) {
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues?) {
        captureValues(transitionValues)
    }

    private fun captureValues(transitionValues: TransitionValues?) {
        transitionValues ?: return
        val view: TextView = transitionValues.view as? TextView ?: return
        transitionValues.values[PROPNAME_TEXTCOLOR] = view.currentTextColor
    }

    override fun createAnimator(
        sceneRoot: ViewGroup?,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        startValues ?: return null
        endValues ?: return null

        val endView = endValues.view as? TextView ?: return null

        val startColor = startValues.values[PROPNAME_TEXTCOLOR] as? Int ?: return null
        val endColor = endValues.values[PROPNAME_TEXTCOLOR] as? Int ?: return null

        if (startColor != endColor) {

            return ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor).apply {
                addUpdateListener {
                    val color = it.animatedValue as? Int ?: return@addUpdateListener
                    endView.setTextColor(color)
                }
            }
        }


        return null
    }
}