package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.adapters.CharacterRecyclerAdapter
import com.example.myapplication.data.pojo.Character
import com.example.myapplication.databinding.FragmentCharacterListBinding
import com.example.myapplication.viewmodel.CharacterViewModel
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.db.CharacterDatabase
import com.example.myapplication.data.retrofit.RetrofitInstance
import com.example.myapplication.repository.CharacterRepository
import com.example.myapplication.viewmodel.CharacterViewModelFactory


class CharacterListFragment : Fragment(), CharacterRecyclerAdapter.ItemClick {
    private lateinit var binding: FragmentCharacterListBinding
    private lateinit var characterAdapter: CharacterRecyclerAdapter
    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterAdapter = CharacterRecyclerAdapter(this@CharacterListFragment)
        val database = CharacterDatabase.getDatabase(requireContext())
        Log.v("databaseNazmin", database.toString())
        val mainRepository = CharacterRepository(database, requireContext())
        characterViewModel = ViewModelProvider(
            this,
            CharacterViewModelFactory(mainRepository)
        )[CharacterViewModel::class.java]


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        observeProgressBar()
        observeCharacter()
        observeErrorMessage()
        observeNetwork()

    }

    private fun prepareRecyclerView() {
        binding.charactersRv.apply {
            adapter = characterAdapter

        }
    }

    private fun observeCharacter() {
        characterViewModel.observeCharacters().observe(
            viewLifecycleOwner
        ) { value -> characterAdapter.setCategoryList(value) }
    }

    private fun observeErrorMessage() {
        characterViewModel.observeErrorMessage().observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeProgressBar() {
        characterViewModel.observeLoading().observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun observeNetwork() {
        characterViewModel.observeNetwork().observe(viewLifecycleOwner) {
            if (it)
                binding.animationView.visibility = View.GONE
            else
                binding.animationView.visibility = View.VISIBLE
        }
    }

    override fun onItemClick(character: Character) {
        val action =
            CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                character
            )
        findNavController().navigate(action)
    }
}