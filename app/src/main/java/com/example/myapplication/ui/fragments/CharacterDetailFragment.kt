package com.example.myapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.example.myapplication.adapters.CharacterRecyclerAdapter.Companion.loadImage
import com.example.myapplication.databinding.FragmentCharacterDetailBinding




class CharacterDetailFragment : Fragment() {
    val args: CharacterDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        binding.character=args.charArg
        loadImage(binding.image,args.charArg.image)
        return binding.root
    }
}