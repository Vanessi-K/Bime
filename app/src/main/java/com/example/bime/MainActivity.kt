package com.example.bime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bime.model.Entry
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = DatabaseHandler(this)


//        db.insertEntry(Entry(1,0, 5.8, LocalDate.now()))
//        db.insertEntry(Entry(1,0, 6.8, LocalDate.now()))
//        db.insertEntry(Entry(1,0, 4.8, LocalDate.now()))
//        db.insertEntry(Entry(2,0, 3.7, LocalDate.now()))
//        db.insertEntry(Entry(2,0, 1.5, LocalDate.now()))

    }
}