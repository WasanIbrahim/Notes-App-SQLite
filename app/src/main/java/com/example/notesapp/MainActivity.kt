package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var submitButton: Button
    lateinit var noteText: EditText


    lateinit var rvMain:RecyclerView
    lateinit var rvAdapter: RecyclerViewAdapter

    lateinit var notes: ArrayList<Notes>

    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notes = arrayListOf()

        submitButton = findViewById(R.id.submitButton)
        noteText = findViewById(R.id.noteEditText)
        rvMain = findViewById(R.id.rvMain)

        rvMain.layoutManager = LinearLayoutManager(this)
        rvAdapter = RecyclerViewAdapter()


        submitButton.setOnClickListener {
            val note = noteText.text.toString()
            databaseHelper.saveData(note)
            Toast.makeText(this, "Note added", Toast.LENGTH_LONG).show()
            noteText.text = null
        }


        notes = databaseHelper.readData()
        rvAdapter.updateData(notes)
        rvMain.adapter = rvAdapter

    }
}