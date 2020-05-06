package com.wangguan.transitions.material.fade

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wangguan.transitions.R
import kotlinx.android.synthetic.main.activity_fade.*

class FadeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fade)

        btn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                DatePickerDialog(this).show()
            }
        }
    }
}
