package com.example.bime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

//        val db = DatabaseHandler(this.activity)
//        db.insertEntry(Entry(1,0, 5.8, LocalDate.now()))
//        db.insertEntry(Entry(1,0, 6.8, LocalDate.now()))
//        db.insertEntry(Entry(1,0, 4.8, LocalDate.now()))
//        db.insertEntry(Entry(2,0, 3.7, LocalDate.now()))
//        db.insertEntry(Entry(2,0, 1.5, LocalDate.now()))

//        db.insertEntry(Entry(1,0, 5.5, LocalDate.of(2023,1,16)))
//        db.insertEntry(Entry(2,0, 7.8, LocalDate.of(2023,1,16)))
//        db.insertEntry(Entry(1,0, 5.7, LocalDate.of(2023,1,17)))
//        db.insertEntry(Entry(2,0, 3.2, LocalDate.of(2023,1,17)))
//        db.insertEntry(Entry(1,0, 1.7, LocalDate.of(2023,1,18)))
//        db.insertEntry(Entry(2,0, 2.6, LocalDate.of(2023,1,18)))
//        db.insertEntry(Entry(1,0, 1.4, LocalDate.of(2023,1,19)))
//        db.insertEntry(Entry(2,0, 5.6, LocalDate.of(2023,1,19)))
//        db.insertEntry(Entry(1,0, 3.7, LocalDate.of(2023,1,20)))
//        db.insertEntry(Entry(2,0, 7.5, LocalDate.of(2023,1,20)))
//        db.insertEntry(Entry(1,0, 4.7, LocalDate.of(2023,1,21)))
//        db.insertEntry(Entry(2,0, 4.5, LocalDate.of(2023,1,21)))
//        db.insertEntry(Entry(1,0, 3.7, LocalDate.of(2023,1,22)))
//        db.insertEntry(Entry(2,0, 2.5, LocalDate.of(2023,1,22)))
    }
}