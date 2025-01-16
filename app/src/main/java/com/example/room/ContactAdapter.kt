package com.example.room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter (private val context: Context, private val listener:NoteClickListener):
    RecyclerView.Adapter<ContactAdapter.NoteViewHolder>() {

    val notes = ArrayList<Note>()

    fun updateList(newList: List<Note>){
        notes.clear()
        notes.addAll(newList)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View):

        RecyclerView.ViewHolder(itemView){
        private val lastNameTV: TextView = itemView.findViewById(R.id.lastNameTV)
        private val numberTV: TextView = itemView.findViewById(R.id.numberTV)
        private val itemTimesTampTV: TextView = itemView.findViewById(R.id.itemTimesTampTV)
        val itemIconDeleteIV: ImageView = itemView.findViewById(R.id.itemIconDeleteIV)

        fun bind(note: Note){
            lastNameTV.text = note.lastname
            numberTV.text = note.number
            itemTimesTampTV.text = note.timesTamp

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
        viewHolder.itemIconDeleteIV.setOnClickListener{
            listener.onItemClicked(notes[viewHolder.adapterPosition])
        }
        return  viewHolder
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.bind(currentNote)

    }

    interface  NoteClickListener{
        fun onItemClicked(note: Note)
    }
}