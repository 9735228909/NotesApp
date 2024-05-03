package com.example.notesapp.Ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesapp.EntityModel.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentCreateNotesBinding
import java.text.SimpleDateFormat
import java.util.*


class CreateNotesFragment : Fragment() {


    lateinit var binding: FragmentCreateNotesBinding
    var priority:String="1"

    val viewModel:NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCreateNotesBinding.inflate(layoutInflater,container,false)

        binding.pred.setImageResource(R.drawable.ic_baseline_done_24)

        binding.pred.setOnClickListener {
            priority="1"
            binding.pred.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pgreen.setImageResource(0)
            binding.pyellow.setImageResource(0)
        }

        binding.pgreen.setOnClickListener {
            priority="2"
            binding.pgreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pred.setImageResource(0)
            binding.pyellow.setImageResource(0)
        }

        binding.pyellow.setOnClickListener {
            priority="3"
            binding.pyellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pred.setImageResource(0)
            binding.pgreen.setImageResource(0)
        }

        binding.btnSaveNote.setOnClickListener {
            careatNotes(it)
        }



        return binding.root
    }

    private fun careatNotes(it: View?) {

        val title=binding.createTitle.text.toString()
        val subtitle=binding.createSubtitle.text.toString()
        val notes=binding.createNotes.text.toString()

        //date
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
       // Log.e("@@@"," C DATE is  "+currentDate)



        val data = Notes(null,
        title=title,subtitle=subtitle,notes=notes, date = currentDate, priority)
        viewModel.addNotes(data)

        Toast.makeText(requireContext(), "Ok your Notes Add... ", Toast.LENGTH_SHORT).show()


        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)


    }

}