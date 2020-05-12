package com.wangguan.transitions

import android.graphics.drawable.Drawable
import android.transition.Transition
import android.transition.TransitionSet
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

/**
 * Created by WG on 2020-05-09.
 * Email: wg5329@163.com
 * Github: https://github.com/WGwangguan
 * Desc:
 */
fun ImageView.load(
    url: String,
    onlyRetrieveFromCache: Boolean = false,
    onLoadingFinished: () -> Unit = {}
) {
    val listener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished()
            return false
        }
    }
    // 变得替换图片的时候出现白屏
    val placeHolder =
        if (drawable != null) drawable else ContextCompat.getDrawable(context, R.mipmap.ic_launcher)
    val requestOptions = RequestOptions.placeholderOf(placeHolder)
        .onlyRetrieveFromCache(onlyRetrieveFromCache)

    Glide.with(this)
        .load(url)
        .apply(requestOptions)
        .listener(listener)
        .into(this)

}

fun TransitionSet.onEnd(callback: () -> Unit) {
    val listener = object : Transition.TransitionListener {
        override fun onTransitionEnd(transition: Transition?) {
            callback()
        }

        override fun onTransitionResume(transition: Transition?) {
        }

        override fun onTransitionPause(transition: Transition?) {
        }

        override fun onTransitionCancel(transition: Transition?) {
        }

        override fun onTransitionStart(transition: Transition?) {
        }

    }

    addListener(listener)


}