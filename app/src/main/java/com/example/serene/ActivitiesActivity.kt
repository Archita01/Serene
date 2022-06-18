package com.example.serene

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ActivitiesActivity : AppCompatActivity() {


    private lateinit var hamburgerImageButton: ImageButton
    private lateinit var button_click: Animation
    private lateinit var yoga: ImageButton
    private lateinit var ted: ImageButton

    private lateinit var mood: ImageButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        button_click = AnimationUtils.loadAnimation(this, R.anim.button_click)

        hamburgerImageButton = findViewById(R.id.hamburgerImageButton)

        yoga = findViewById(R.id.yoga)
        ted = findViewById(R.id.ted)
        mood = findViewById(R.id.mood)

        hamburgerImageButton.setOnClickListener(View.OnClickListener {
            hamburgerImageButton.setAnimation(button_click)
            Handler().postDelayed({
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }, 200)
        })


        yoga.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)
            finish()
        })

        ted.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, TedTalksActivity::class.java)
            startActivity(intent)
            finish()
        })

        mood.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MoodActivity::class.java)
            startActivity(intent)
            finish()
        })


    }


}