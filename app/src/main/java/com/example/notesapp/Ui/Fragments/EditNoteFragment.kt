package com.example.notesapp.Ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.EntityModel.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentEditNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*


class EditNoteFragment : Fragment() {

    lateinit var binding: FragmentEditNoteBinding

    val Not by navArgs<EditNoteFragmentArgs>()
    var  priority:String="1"
    val viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentEditNoteBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)


        binding.edtTitle.setText(Not.title)
        binding.edtSubtitle.setText(Not.sub)
        binding.edtNotes.setText(Not.note)


        when(Not.pred){
            "1"->{
                priority="1"
                binding.pred.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pgreen.setImageResource(0)
                binding.pyello.setImageResource(0)

            }
            "2"->{
                priority="2"
                binding.pgreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pred.setImageResource(0)
                binding.pyello.setImageResource(0)

            }
            "3"->{
                priority="3"
                binding.pyello.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pred.setImageResource(0)
                binding.pgreen.setImageResource(0)

            }
        }

        binding.pred.setOnClickListener {
            priority="1"
            binding.pred.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pgreen.setImageResource(0)
            binding.pyello.setImageResource(0)
        }

        binding.pgreen.setOnClickListener {
            priority="2"
            binding.pgreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pred.setImageResource(0)
            binding.pyello.setImageResource(0)
        }

        binding.pyello.setOnClickListener {
            priority="3"
            binding.pyello.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pred.setImageResource(0)
            binding.pgreen.setImageResource(0)
        }
        
        binding.btnEdtNote.setOnClickListener { 
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {

        val title=binding.edtTitle.text.toString()
        val subtitle=binding.edtSubtitle.text.toString()
        val notes=binding.edtNotes.text.toString()

        //date
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        // Log.e("@@@"," C DATE is  "+currentDate)


                    //Error no solved id
        val data = Notes(4,
            title=title,subtitle=subtitle,notes=notes, date = currentDate, priority)
        // Log.e("vasu","updateNotes:title : $title subtitle: $subtitle notes : $notes")
        viewModel.updateNotes(data)

        Toast.makeText(requireContext(), "Ok Your Notes Updates... ", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)


    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.delete_menu,menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId==R.id.delete_menu){
//            val buttonSheet:BottomSheetDialog= BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
//            buttonSheet.setContentView(R.layout.delete_box)
//
//            val notext=buttonSheet.findViewById<TextView>(R.id.delete_no)
//            val yestext=buttonSheet.findViewById<TextView>(R.id.delete_yes)
//
//
//            notext?.setOnClickListener {
//                buttonSheet.dismiss()
//
//            }
//            yestext?.setOnClickListener {
////                viewModel.deleteNotes(Not,1)
//                buttonSheet.dismiss()
//            }
//            buttonSheet.show()
//        }
//        return super.onOptionsItemSelected(item)
//    }

}