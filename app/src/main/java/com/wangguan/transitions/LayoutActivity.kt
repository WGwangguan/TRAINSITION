package com.wangguan.transitions

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_layout.*

class LayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        text_with_scene.setOnClickListener { startActivity(Intent(this, LayoutWithSActivity::class.java)) }
        text_without_scene.setOnClickListener { startActivity(Intent(this, LayoutWithoutSActivity::class.java)) }


    }
}
