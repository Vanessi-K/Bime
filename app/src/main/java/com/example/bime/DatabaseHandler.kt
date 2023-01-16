package com.example.bime

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.time.LocalDate

class DatabaseHandler(context: Context?): SQLiteOpenHelper(context, dbName, null, 1) {

    companion object DataConfig {
        private const val dbName = "bime"
        private const val category_table = "category"
        private const val category_id = "category_id"
        private const val category_name = "presentTitle"
        private const val category_colour = "presentLink"
        private const val entry_table = "entry"
        private const val entry_id = "entry_id"
        private const val entry_day = "entry_day"
        private const val entry_time_h = "entry_time_h"
    }

    fun dateToSqlDate(date: LocalDate): String {
        return date.toString()
    }

    fun sqlDateToDate(date: String): LocalDate {
        return LocalDate.parse(date)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS $category_table(" +
                "$category_id INTEGER PRIMARY KEY, " +
                "$category_name VARCHAR(255), " +
                "$category_colour VARCHAR(7));")

        db?.execSQL("CREATE TABLE IF NOT EXISTS $entry_table(" +
                "$entry_id INTEGER PRIMARY KEY, " +
                "$category_id INTEGER, " +
                "$entry_day DATETIME, " +
                "$entry_time_h DOUBLE);")

        insertCategory(Category(1, "Busy time", "#84D1FC"),db)
        insertCategory(Category(2, "Free time", "#FF8C00"),db)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE $category_table");
        db?.execSQL("DROP TABLE $entry_table");
        onCreate(db)
    }

    //region category

    fun insertCategory(name: String, colour: String, db: SQLiteDatabase? = null) {

        val db = db ?: this.writableDatabase

        val ctv = ContentValues()
        ctv.put(category_name, name)
        ctv.put(category_colour, colour)
        db.insert(category_table, null, ctv)
    }

    fun insertCategory(category: Category, db: SQLiteDatabase?) {
        insertCategory(category.name, category.colour, db)
    }

    fun getAllCategories(): MutableList<Category> {
        val db = this.writableDatabase

        val cursor = db.rawQuery("SELECT * FROM $category_table", null)
        val allCategories: MutableList<Category> = mutableListOf()

        while(cursor.moveToNext()) {
            val indexId = cursor.getColumnIndex(category_id)
            val indexName = cursor.getColumnIndex(category_name)
            val indexColour = cursor.getColumnIndex(category_colour)

            val category = Category(
                cursor.getInt(indexId),
                cursor.getString(indexName),
                cursor.getString(indexColour)
            )
            allCategories.add(category)
        }
        cursor.close()
        return allCategories
    }

    fun getCategoryById(id: Int): Category? {
        val db = this.writableDatabase

        val cursor = db.rawQuery("SELECT * FROM $category_table WHERE $category_id = $id", null)
        val allCategories: MutableList<Category> = mutableListOf()

        while(cursor.moveToNext()) {
            val indexId = cursor.getColumnIndex(category_id)
            val indexName = cursor.getColumnIndex(category_name)
            val indexColour = cursor.getColumnIndex(category_colour)

            val category = Category(
                cursor.getInt(indexId),
                cursor.getString(indexName),
                cursor.getString(indexColour)
            )
            allCategories.add(category)
        }
        cursor.close()
        return allCategories.firstOrNull()
    }

    fun deleteCategory(id: Int) {
        val db = this.writableDatabase
        db.delete(category_table, "$category_id = $id", null)
    }

    fun deleteCategory(category: Category) {
        deleteCategory(category.id)
    }

    fun updateCategory(id: Int, name: String, colour: String) {
        val db = this.writableDatabase

        val ctv = ContentValues()
        ctv.put(category_name, name)
        ctv.put(category_colour, colour)
        db.update(category_table, ctv, "$category_id = $id", null)
    }

    fun updateCategory(category: Category) {
        updateCategory(category.id, category.name, category.colour)
    }

    //endregion

    //region entry

    fun insertEntry(category: Int, day: LocalDate?, time: Double) {
        val db = this.writableDatabase

        val ctv = ContentValues()
        ctv.put(category_id, category)
        ctv.put(entry_day, day?.let { dateToSqlDate(it) })
        ctv.put(entry_time_h, time)
        db.insert(entry_table, null, ctv)
    }

    fun insertEntry(entry: Entry) {
        entry.category?.let { insertEntry(it, entry.day, entry.time) }
    }

    fun getAllEntries(): MutableList<Entry> {
        val db = this.writableDatabase

        val cursor = db.rawQuery("SELECT * FROM $entry_table", null)
        val allEntries: MutableList<Entry> = mutableListOf()

        while(cursor.moveToNext()) {
            val indexId = cursor.getColumnIndex(entry_id)
            val indexCategory = cursor.getColumnIndex(category_id)
            val indexDay = cursor.getColumnIndex(entry_day)
            val indexTime = cursor.getColumnIndex(entry_time_h)

            val entry = Entry(
                cursor.getInt(indexId),
                cursor.getInt(indexCategory),
                cursor.getDouble(indexTime),
                sqlDateToDate(cursor.getString(indexDay))
            )
            allEntries.add(entry)
        }
        cursor.close()
        return allEntries
    }

    fun getEntryById(id: Int): Entry? {
        val db = this.writableDatabase

        val cursor = db.rawQuery("SELECT * FROM $entry_table WHERE $entry_id = $id", null)
        val allEntries: MutableList<Entry> = mutableListOf()

        while(cursor.moveToNext()) {
            val indexId = cursor.getColumnIndex(entry_id)
            val indexCategory = cursor.getColumnIndex(category_id)
            val indexDay = cursor.getColumnIndex(entry_day)
            val indexTime = cursor.getColumnIndex(entry_time_h)

            val entry = Entry(
                cursor.getInt(indexId),
                cursor.getInt(indexCategory),
                cursor.getDouble(indexTime),
                sqlDateToDate(cursor.getString(indexDay))
            )
            allEntries.add(entry)
        }
        cursor.close()
        return allEntries.firstOrNull()
    }

    fun getEntryById(entry: Entry) {
        entry.id?.let { getEntryById(it) }
    }

    fun getEntriesByDate(day: LocalDate): MutableList<Entry> {
        val db = this.writableDatabase

        val cursor = db.rawQuery("SELECT * FROM $entry_table WHERE $entry_day = ${dateToSqlDate(day)}", null)
        val allEntries: MutableList<Entry> = mutableListOf()

        while(cursor.moveToNext()) {
            val indexId = cursor.getColumnIndex(entry_id)
            val indexCategory = cursor.getColumnIndex(category_id)
            val indexDay = cursor.getColumnIndex(entry_day)
            val indexTime = cursor.getColumnIndex(entry_time_h)

            val entry = Entry(
                cursor.getInt(indexId),
                cursor.getInt(indexCategory),
                cursor.getDouble(indexTime),
                sqlDateToDate(cursor.getString(indexDay))
            )
            allEntries.add(entry)
        }
        cursor.close()
        return allEntries
    }

    fun getEntriesByTimerange(startDate: LocalDate, endDate: LocalDate): MutableList<Entry> {
        val db = this.writableDatabase

        val cursor = db.rawQuery("SELECT * FROM $entry_table WHERE $entry_day BETWEEN ${dateToSqlDate(startDate)} AND ${dateToSqlDate(endDate)}", null)
        val allEntries: MutableList<Entry> = mutableListOf()

        while(cursor.moveToNext()) {
            val indexId = cursor.getColumnIndex(entry_id)
            val indexCategory = cursor.getColumnIndex(category_id)
            val indexDay = cursor.getColumnIndex(entry_day)
            val indexTime = cursor.getColumnIndex(entry_time_h)

            val entry = Entry(
                cursor.getInt(indexId),
                cursor.getInt(indexCategory),
                cursor.getDouble(indexTime),
                sqlDateToDate(cursor.getString(indexDay))
            )
            allEntries.add(entry)
        }
        cursor.close()
        return allEntries
    }

    fun getEntriesByCategory(category: Int): MutableList<Entry> {
        val db = this.writableDatabase

        val cursor = db.rawQuery("SELECT * FROM $entry_table WHERE $category_id = $category", null)
        val allEntries: MutableList<Entry> = mutableListOf()

        while(cursor.moveToNext()) {
            val indexId = cursor.getColumnIndex(entry_id)
            val indexCategory = cursor.getColumnIndex(category_id)
            val indexDay = cursor.getColumnIndex(entry_day)
            val indexTime = cursor.getColumnIndex(entry_time_h)

            val entry = Entry(
                cursor.getInt(indexId),
                cursor.getInt(indexCategory),
                cursor.getDouble(indexTime),
                sqlDateToDate(cursor.getString(indexDay))
            )
            allEntries.add(entry)
        }
        cursor.close()
        return allEntries
    }

    fun deleteEntry(id: Int) {
        val db = this.writableDatabase
        db.delete(entry_table, "$entry_id = $id", null)
    }

    fun deleteEntry(entry: Entry) {
        entry.id?.let { deleteEntry(it) }
    }

    fun updateEntry(id: Int, category: Int, day: LocalDate, time: Double) {
        val db = this.writableDatabase

        val ctv = ContentValues()
        ctv.put(category_id, category)
        ctv.put(entry_day, dateToSqlDate(day))
        ctv.put(entry_time_h, time)
        db.update(entry_table, ctv, "$entry_id = $id", null)
    }

    fun updateEntry(entry: Entry) {
        entry.category?.let { entry.id?.let { it1 -> updateEntry(it1, it, entry.day, entry.time) } }
    }

    //endregion

}