package com.example.trailermovie.ui.instruction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trailermovie.R
import kotlinx.android.synthetic.main.activity_instruction.*


class InstructionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)

        var adapter = SlideAdapter(arrayListOf(), this)
        viewPager.adapter = adapter
        adapter.addIndicator(0)
        viewPager.addOnPageChangeListener(adapter.litener)
    }
}
