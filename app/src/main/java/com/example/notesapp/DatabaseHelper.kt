package com.example.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//increase version if more column/features added to db
class DatabaseHelper(context: Context): SQLiteOpenHelper(context,"details.db",null,2) {
    private val sqLiteDatabase: SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL("create table notes (Note text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(db)
    }

    fun saveData(note: String){
        val contentValues = ContentValues()
        contentValues.put("Note", note)
        sqLiteDatabase.insert("notes",null,contentValues)
    }

    fun readData(): ArrayList<Notes>{
        val notes = arrayListOf<Notes>()

        //read all data using cursor
        val cursor: Cursor= sqLiteDatabase.rawQuery("SELECT * FROM notes", null)

        if (cursor.count < 1){
            println("No Data Found")
        }
        else{
            while (cursor.moveToNext()){ //iterate through table and populate notes array list
                val note = cursor.getString(0) // integer value refers to the column
                notes.add(Notes(note))
            }
        }
        return notes
    }
}