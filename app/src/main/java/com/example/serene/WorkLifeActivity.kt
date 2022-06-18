package com.example.serene

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView

class WorkLifeActivity : AppCompatActivity() {
    private lateinit var text : TextView
    private lateinit var score : TextView
    private lateinit var schedule : TextView
    private lateinit var hamburgerImageButton: ImageButton
    private lateinit var button_click: Animation
    private var pred:Double =0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_life)
        score = findViewById(R.id.balance)
        text = findViewById(R.id.message)
        schedule = findViewById(R.id.schedule)
        val response = intent.getStringExtra("Response")
        Log.e("Prediction",response.toString())

        button_click = AnimationUtils.loadAnimation(this, R.anim.button_click)

        hamburgerImageButton = findViewById(R.id.hamburgerImageButton)

        hamburgerImageButton.setOnClickListener(View.OnClickListener {
            hamburgerImageButton.setAnimation(button_click)
            Handler().postDelayed({
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }, 200)
        })
        pred = (response!!.toDouble()*100)
        score.text = pred.toString()

        if(pred >90.0){
            text.text="Oops!! You are very stressed out mentally and you need to improve!!"


        }
        else if(pred >= 81.0 && pred<90.0)
        {
            text.text= "You have moderate stress levels. Please work on it!"

        }
        else{
            text.text= "Yayyy ! You have a great work-life balance. Keep up the good spirit."
                schedule.visibility = View.INVISIBLE
        }

        schedule.setOnClickListener {
            startActivity(Intent(this, ScheduleActivity::class.java))
            finish()
        }
    }
}