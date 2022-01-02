package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var submitButton: Button
    lateinit var refreshButton: FloatingActionButton
    lateinit var noteText: EditText

    lateinit var rvMain:RecyclerView
    lateinit var rvAdapter: RecyclerViewAdapter
    lateinit var notes: ArrayList<Notes>

    //hold instance of the Notes class , used for update and delete
    var selectedNote: Notes? = null

    val databaseHelper by lazy { DatabaseHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notes = arrayListOf()
        notes = databaseHelper.readData()

        submitButton = findViewById(R.id.submitButton)
        refreshButton = findViewById(R.id.refreshButton)


        noteText = findViewById(R.id.noteEditText)
        rvMain = findViewById(R.id.rvMain)

        rvMain.layoutManager = LinearLayoutManager(this)
        rvAdapter = RecyclerViewAdapter(this)


        submitButton.setOnClickListener {
            val note = noteText.text.toString()
            databaseHelper.saveData(note)
            Toast.makeText(this, "Note added", Toast.LENGTH_LONG).show()
            noteText.text = null
            notes = databaseHelper.readData()

            rvAdapter.updateData(notes)
            rvMain.adapter = rvAdapter
        }


        refreshButton.setOnClickListener {

            notes = databaseHelper.readData()
            rvAdapter.updateData(notes)
            rvMain.adapter = rvAdapter

        }
    }
    fun delete(pk: Int){
        databaseHelper.deleteData(pk)
        Toast.makeText(this, "Deleted successfully", Toast.LENGTH_LONG).show()
    }

    fun updateFields(){
        noteText.setText(selectedNote!!.note)
    }

}
