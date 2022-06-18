package com.example.serene

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MenuActivity : AppCompatActivity() {
    private lateinit var home: TextView
    private lateinit var about: TextView
    private lateinit var facts: TextView
    private lateinit var video: TextView
    private lateinit var schedule: TextView
    private lateinit var profile: TextView
    private lateinit var logout: TextView
    lateinit var auth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference
    lateinit var database : FirebaseDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        mDatabase = database.reference.child("Names")
        val currentuser = auth.currentUser

        val userreference = mDatabase.child(currentuser?.uid!!)
        val dispTxt = findViewById<TextView>(R.id.hi_there)
        userreference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dispTxt.text = "Hello, "+snapshot.child("Name").value.toString()+" !!"

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        home = findViewById(R.id.home)
        home.setOnClickListener {
            startActivity(Intent(this, StartActivity::class.java))
            finish()
        }

        schedule = findViewById(R.id.schedule)
        schedule.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ScheduleActivity::class.java)
            startActivity(intent)
            finish()
        })
        video = findViewById(R.id.video)
        video.setOnClickListener {
            startActivity(Intent(this, ActivitiesActivity::class.java))
            finish()
        }
        facts = findViewById(R.id.facts)
        facts.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, FactsActivity::class.java)
            startActivity(intent)
            finish()
        })
        about = findViewById(R.id.about)
        about.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
            finish()
        })
        profile = findViewById(R.id.profile)
        profile.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        })
        logout = findViewById(R.id.signout)
        logout.setOnClickListener(View.OnClickListener {
            auth = FirebaseAuth.getInstance()
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}