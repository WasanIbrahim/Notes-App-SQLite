package com.example.notesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.ItemRowBinding

class RecyclerViewAdapter:RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

     var notesList = emptyList<Notes>()

    class ItemViewHolder(val binding: ItemRowBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent ,false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val notes = notesList[position]


        holder.binding.apply {
            val note = "${notes.note}"
            noteText.text = note
        }
    }

    override fun getItemCount(): Int {
       return notesList.size
    }
    fun updateData(notes: ArrayList<Notes>){
        this.notesList = notes
        notifyDataSetChanged()
    }
}