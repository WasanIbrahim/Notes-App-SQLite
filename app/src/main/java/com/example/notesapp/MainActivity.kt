package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var submitButton: Button
    lateinit var editText: EditText

    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton = findViewById(R.id.submitButton)
        editText = findViewById(R.id.editText)

        submitButton.setOnClickListener {
            val note = editText.text.toString()
            databaseHelper.saveData(note)
            Toast.makeText(this, "Note added", Toast.LENGTH_LONG).show()
            editText.text = null

        }
    }
}