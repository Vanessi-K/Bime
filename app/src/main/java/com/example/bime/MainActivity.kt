package com.example.bime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val db = DatabaseHandler(this)

    }
}