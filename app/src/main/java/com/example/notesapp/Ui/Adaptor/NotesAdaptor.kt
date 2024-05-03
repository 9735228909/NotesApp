package com.example.notesapp.Ui.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.EntityModel.Notes
import com.example.notesapp.R
import com.example.notesapp.Ui.Fragments.HomeFragmentDirections
import com.example.notesapp.Ui.Onclick
import com.example.notesapp.databinding.ItemNotesBinding

class NotesAdaptor( val requireContext: Context, var noteslist: List<Notes>,val onclick: Onclick) :
    RecyclerView.Adapter<NotesAdaptor.notesViewHolder>() {

    fun filtaring(newFilterlist: ArrayList<Notes>) {
        noteslist=newFilterlist
        notifyDataSetChanged()

    }
    class notesViewHolder(val binding:ItemNotesBinding,val onclick: Onclick) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.delete.setOnClickListener {
                onclick.deleteonclick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false),onclick)
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {


        val data =noteslist[position]
        holder.binding.title.text=data.title
        holder.binding.subtitle.text=data.subtitle

        holder.binding.date.text=data.date


        when(data.priority){
            "1"->{
                holder.binding.pred.setBackgroundResource(R.drawable.red_dot_box)
            }
            "2"->{
                holder.binding.pred.setBackgroundResource(R.drawable.green_dot_box)
            }
            "3"->{
              holder.binding.pred.setBackgroundResource(R.drawable.yellow_dot_box)
            }
        }
        holder.binding.root.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(
                title = data.title,
                sub = data.subtitle,
                note = data.notes,
                pred = data.priority)

            Navigation.findNavController(it).navigate(action)
        }



    }

    override fun getItemCount(): Int {
        return noteslist.size

    }
}