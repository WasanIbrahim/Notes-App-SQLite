package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val pk = intent.getIntExtra("pk", 0)
        val note = intent.getStringExtra("note")

        val database = DatabaseHelper(applicationContext)

        val updateButton = findViewById<Button>(R.id.btUpdate)
        val updateText = findViewById<EditText>(R.id.tvUpdate)
        updateText.hint = note

        updateButton.setOnClickListener {
            if (updateText.text.isNotEmpty())
                database.updateData(pk, updateText.text.toString())
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
    }
}