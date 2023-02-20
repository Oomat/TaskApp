package com.example.taskapp.ui.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentOnBoardBinding
import com.example.taskapp.databinding.NewTaskItemBinding


class onBoardFragment : Fragment() {
private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater,container,false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        val adapter = BoardingPageAdapter(childFragmentManager)
        binding.vpBoard.adapter=adapter
    }

}