package com.example.taskapp.ui.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskapp.databinding.FragmentOnBoardBinding


class OnBoardFragment : Fragment() {
private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater,container,false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        val adapter = BoardingPageAdapter(childFragmentManager,this::onScip,this::onNext)
        binding.vpBoard.adapter=adapter
        binding.springDotsIndicator.attachTo(binding.vpBoard)
    }
    private fun onScip(){
        binding.vpBoard.currentItem = 2
    }
    private fun onNext(){
        binding.vpBoard.currentItem +=1
    }

}