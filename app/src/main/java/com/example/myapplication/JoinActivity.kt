package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class JoinActivity : AppCompatActivity() {
    lateinit var cvGym: CardView
    lateinit var cvAthlete: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        cvGym = findViewById(R.id.mCvGym)
        cvAthlete = findViewById(R.id.mCvAthlete)

        cvGym.setOnClickListener {
            var intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
        cvAthlete.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}