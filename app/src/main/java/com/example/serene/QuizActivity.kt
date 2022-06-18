package com.example.serene

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class QuizActivity : AppCompatActivity() {
    private lateinit var submit : Button
    private lateinit var a1 : EditText
    private lateinit var a2 : EditText
    private lateinit var a3 : EditText
    private lateinit var a4 : EditText
    private lateinit var a5 : EditText
    private lateinit var a6 : EditText
    private lateinit var a7 : EditText
    private lateinit var a8 : EditText
    private lateinit var a9 : EditText
    private lateinit var a10 : EditText
    private lateinit var a11 : EditText
    private lateinit var a12 : EditText
    private lateinit var a13 : EditText
    private lateinit var a14 : EditText
    private lateinit var a15 : EditText
    private lateinit var a16 : EditText
    private lateinit var a17 : EditText
    private lateinit var a18 : EditText
    private lateinit var a19 : EditText
    private lateinit var a20 : EditText
    private lateinit var product : RadioButton
    private lateinit var service : RadioButton
    private lateinit var yes : RadioButton
    private lateinit var no : RadioButton
    private lateinit var male : RadioButton
    private lateinit var female : RadioButton
    private lateinit var hamburgerImageButton: ImageButton
    private lateinit var button_click: Animation

    private lateinit var company : String
    private lateinit var wfh : String
    private lateinit var sex : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        button_click = AnimationUtils.loadAnimation(this, R.anim.button_click)

        hamburgerImageButton = findViewById(R.id.hamburgerImageButton)


        a5 = findViewById(R.id.ans5)
        a6 = findViewById(R.id.ans6)
        a7 = findViewById(R.id.ans7)
        a8 = findViewById(R.id.ans8)
        a9 = findViewById(R.id.ans9)

        product = findViewById(R.id.product)
        service = findViewById(R.id.service)
        no = findViewById(R.id.no_wfh)
        yes = findViewById(R.id.yes_wfh)
        male = findViewById(R.id.male)
        female = findViewById(R.id.female)
        hamburgerImageButton.setOnClickListener(View.OnClickListener {
            hamburgerImageButton.setAnimation(button_click)
            Handler().postDelayed({
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }, 200)
        })

        product.setOnClickListener {
            company = "0"
        }
        service.setOnClickListener {
            company = "1"
        }
        no.setOnClickListener {
            wfh = "0"
        }
        yes.setOnClickListener {
            wfh = "1"
        }
        male.setOnClickListener {
            sex="0"
        }
        female.setOnClickListener {
            sex="1"
        }

        submit = findViewById(R.id.submit)
        submit.setOnClickListener {
            val url = "https://aaaserene.herokuapp.com/?GENDER=0&COMPANY=1&WFH=0&DESIG=1&HOURS=4&TIRED=0.5&MONTH=9&DAY=30"
            val url1="https://aaaserene.herokuapp.com/?GENDER="+sex+"&COMPANY="+company+"&WFH="+wfh+"&DESIG="+a5.text+"&HOURS="+a6.text+"&TIRED="+a7.text+"&MONTH="+a8.text+"&DAY="+a9.text
            val queue = Volley.newRequestQueue(this)
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url1, null,
                { response ->
                    Log.e("Response:", response.getString("prediction"))
                    val s = response.getString("prediction")
                    val sub = s.substring(1,s.length-1)
                    Log.e("sub: ",sub)
                    val intent = Intent(this, WorkLifeActivity::class.java)
                    intent.putExtra("Response", sub)
                    startActivity(intent)
                    finish()
                },
                { error ->
                    Log.e("Response:", "Error ")

                }
            )
            queue.add(jsonObjectRequest)

// Access the RequestQueue through your singleton class.

        }
    }
}