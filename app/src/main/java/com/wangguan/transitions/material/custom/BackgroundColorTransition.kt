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
        captureValues(transitionValues)
        Log.i("ddd","captureStartValues ${transitionValues?.view}")
    }

    override fun captureEndValues(transitionValues: TransitionValues?) {
        captureValues(transitionValues)
        Log.i("ddd","captureEndValues ${transitionValues?.view}")

    }

    private fun captureValues(transitionValues: TransitionValues?) {
        // 将需要跟踪的属性塞到 values 里面
        transitionValues?.also {
            it.values[PROPNAME_BACKGROUND] = it.view.background
        }
    }

    override fun createAnimator(
        sceneRoot: ViewGroup?,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        Log.i("ddd","createAnimator ${startValues?.view}  ${endValues?.view}")
        startValues ?: return null
        endValues ?: return null

        val view: View = endValues.view

        // 根据属性 key 取出前后的背景
        val startBackground = startValues.values[PROPNAME_BACKGROUND] as Drawable?
        val endBackground = endValues.values[PROPNAME_BACKGROUND] as Drawable?


        // 格式判断
        if (startBackground is ColorDrawable && endBackground is ColorDrawable) {

            // 前后颜色发生改变才执行动画
            if (startBackground.color != endBackground.color) {
                val animator = ValueAnimator.ofObject(
                    ArgbEvaluator(),
                    startBackground.color, endBackground.color
                )
                animator.addUpdateListener { animation ->
                    val value = animation.animatedValue

                    if (null != value) {
                        // 不断更新背景以达到平滑过渡效果
                        view.setBackgroundColor(value as Int)
                    }
                }

                return animator
            }
        }

        return null
    }
}