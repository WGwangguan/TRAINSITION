package com.wangguan.transitions.material

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wangguan.transitions.R
import com.wangguan.transitions.material.axis.AxisActivity
import com.wangguan.transitions.material.container.ContainerActivity
import com.wangguan.transitions.material.fade.FadeActivity
import com.wangguan.transitions.material.fadeThrough.FadeThroughActivity
import kotlinx.android.synthetic.main.activity_material.*

class MaterialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)

        text_container.setOnClickListener {
            startActivity(Intent(this, ContainerActivity::class.java))
        }

        text_shared_axis.setOnClickListener {
            startActivity(Intent(this, AxisActivity::class.java))
        }

        text_fade_through.setOnClickListener {
            startActivity(Intent(this, FadeThroughActivity::class.java))
        }

        text_fade.setOnClickListener {
            startActivity(Intent(this, FadeActivity::class.java))
        }

    }

}
