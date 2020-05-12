package com.wangguan.transitions

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wangguan.transitions.material.custom.BackgroundColorTransition
import com.wangguan.transitions.material.custom.TextColorTransition
import kotlinx.android.synthetic.main.activity_transition_with_s.*

class LayoutWithSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_with_s)

//        val sceneFirst = Scene.getSceneForLayout(layout_container, R.layout.scene_first, this)
//        val sceneSecond = Scene.getSceneForLayout(layout_container, R.layout.scene_second, this)

        val firstView = layoutInflater.inflate(R.layout.scene_first, null)
        val secondView = layoutInflater.inflate(R.layout.scene_second, null)

        val sceneFirst = Scene(layout_container, firstView)
        val sceneSecond = Scene(layout_container, secondView)

        val transition = TransitionSet()
            .addTransition(ChangeBounds())
            .addTransition(BackgroundColorTransition())
            .setDuration(2000L)
        layout_container.addView(firstView)

        firstView.setOnClickListener {
            secondView.findViewById<TextView>(R.id.text_name).text = "从前置过来"
            TransitionManager.go(sceneSecond, transition)
        }

        secondView.setOnClickListener {
            firstView.findViewById<TextView>(R.id.text_name).text = "从后置返回"
            TransitionManager.go(sceneFirst, transition)
        }

        sceneFirst.setExitAction { Log.i("ddd", "first exit") }
        sceneSecond.setExitAction { Log.i("ddd", "second exit") }
    }
}
