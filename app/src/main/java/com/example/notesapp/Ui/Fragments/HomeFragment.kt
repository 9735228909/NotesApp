package com.example.notesapp.Ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.EntityModel.Notes
import com.example.notesapp.R
import com.example.notesapp.Ui.Adaptor.NotesAdaptor
import com.example.notesapp.Ui.Onclick
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment(),Onclick {
lateinit var bindin:FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldnotes= arrayListOf<Notes>()
    lateinit var adaptor:NotesAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindin=FragmentHomeBinding.inflate(layoutInflater,container,false )
        setHasOptionsMenu(true)


        val staggeredGridLayoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        bindin.recyclerView.layoutManager=staggeredGridLayoutManager


        viewModel.getNotes().observe(viewLifecycleOwner,{ noteslist->
            oldnotes=noteslist as ArrayList<Notes>
            adaptor=NotesAdaptor(requireContext(),noteslist,this)
            bindin.recyclerView.adapter=adaptor

        })

        bindin.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner,{ noteslist->
                oldnotes=noteslist as ArrayList<Notes>
                adaptor=NotesAdaptor(requireContext(),noteslist,this)
                bindin.recyclerView.adapter= adaptor
            })
        }

        bindin.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner,{ noteslist->
                oldnotes=noteslist as ArrayList<Notes>
                adaptor=NotesAdaptor(requireContext(),noteslist,this)
                bindin.recyclerView.adapter= adaptor
            })
        }

        bindin.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner,{ noteslist->
                oldnotes=noteslist as ArrayList<Notes>
                adaptor=NotesAdaptor(requireContext(),noteslist,this)
                bindin.recyclerView.adapter= adaptor
            })
        }

        bindin.filter.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner,{ noteslist->
                oldnotes=noteslist as ArrayList<Notes>
                adaptor= NotesAdaptor(requireContext(),noteslist,this)
                bindin.recyclerView.adapter=adaptor
            })
        }


        bindin.buttonAdd.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return bindin.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item=menu.findItem(R.id.app_bar_search)
        val menuAction=item.actionView as SearchView

       menuAction.queryHint=" Enter Notes Here... "
        menuAction.setOnQueryTextListener(object :SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                notesFiltter(newText)
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun notesFiltter(newText: String?) {
       // Log.e("vasu","$newText")

        val newFilterlist = arrayListOf<Notes>()

        for (i in oldnotes){
            if (i.title.contains(newText!!) || i.subtitle.contains(newText)){
                newFilterlist.add(i)
            }
        }
        adaptor.filtaring(newFilterlist)


    }

    override fun deleteonclick(position: Int) {

        val buttonSheet = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
        buttonSheet.setContentView(R.layout.delete_box)

        val notext=buttonSheet.findViewById<TextView>(R.id.delete_no)
        val yestext=buttonSheet.findViewById<TextView>(R.id.delete_yes)


        notext?.setOnClickListener {
            buttonSheet.dismiss()
        }
        yestext?.setOnClickListener {
            if (position in 0 until oldnotes.size) {
                val noteToDelete = oldnotes[position]

                // Delete note from ViewModel
                noteToDelete.id?.let { viewModel.deleteNotes(it) }

                oldnotes.removeAt(position)
                adaptor.notifyItemRemoved(position)
            } else {
                Log.e("HomeFragment", "Invalid position for deleteOnClick")
            }
            buttonSheet.dismiss()
        }
        buttonSheet.show()

//        Toast.makeText(requireContext(),"${oldnotes[position]}",Toast.LENGTH_LONG).show()


    }


}